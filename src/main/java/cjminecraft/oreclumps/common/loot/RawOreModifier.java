package cjminecraft.oreclumps.common.loot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootSerializers;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class RawOreModifier extends LootModifier {

    private static final Gson GSON_INSTANCE = LootSerializers.createFunctionSerializer().create();

    private final ILootFunction[] functions;
    private final Item replacement;
    private final String toReplaceTagString;
    private ITag<Block> toReplace = null;
    private final Block blockToReplace;

    public RawOreModifier(ILootCondition[] conditionsIn, ILootFunction[] functions, String toReplace, Item replacement) {
        super(conditionsIn);
        this.functions = functions;
        this.toReplaceTagString = toReplace;
        this.blockToReplace = null;
        this.replacement = replacement;
    }

    public RawOreModifier(ILootCondition[] conditionsIn, ILootFunction[] functions, ITag<Block> toReplace, Item replacement) {
        super(conditionsIn);
        this.functions = functions;
        this.toReplace = toReplace;
        this.toReplaceTagString = null;
        this.blockToReplace = null;
        this.replacement = replacement;
    }


    public RawOreModifier(ILootCondition[] conditionsIn, ILootFunction[] functions, Block toReplace, Item replacement) {
        super(conditionsIn);
        this.functions = functions;
        this.toReplaceTagString = null;
        this.blockToReplace = toReplace;
        this.replacement = replacement;
    }

    private ITag<Block> getTag() {
        // used to delay fetching the tag since tags are cleared when reading the loot modifier
        if (this.toReplaceTagString != null && this.toReplace == null)
            this.toReplace = BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation(this.toReplaceTagString));
        return this.toReplace;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);
        if (state != null) {
            // ensure that this is a block drop
            if ((this.getTag() != null && state.getBlock().is(this.getTag()))
                    || (this.blockToReplace != null && state.getBlock().is(this.blockToReplace))) {
                // if we should replace the drop for this block
                generatedLoot.clear();
                ItemStack stack = this.replacement.getDefaultInstance();
                for (ILootFunction function : this.functions)
                    stack = function.apply(stack, context);
                generatedLoot.add(stack);
            }
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<RawOreModifier> {
        @Override
        public RawOreModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            ILootFunction[] functions = object.has("functions") ? GSON_INSTANCE.fromJson(object.get("functions"), ILootFunction[].class) : new ILootFunction[0];
            Item replacement = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "replacement")));
            String toReplace = JSONUtils.getAsString(object, "replace");
            if (toReplace.startsWith("#"))
                return new RawOreModifier(conditions, functions, toReplace.substring(1), replacement);
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(toReplace));
            return new RawOreModifier(conditions, functions, block, replacement);
        }

        @Override
        public JsonObject write(RawOreModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            if (!ArrayUtils.isEmpty(instance.functions))
                json.add("functions", GSON_INSTANCE.toJsonTree(instance.functions));
            if (instance.toReplace != null)
                json.addProperty("replace", "#" + BlockTags.getAllTags().getIdOrThrow(instance.toReplace));
            else if (instance.toReplaceTagString != null)
                json.addProperty("replace", "#" + instance.toReplaceTagString);
            else if (instance.blockToReplace != null)
                json.addProperty("replace", Objects.requireNonNull(instance.blockToReplace.getRegistryName()).toString());

            json.addProperty("replacement", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(instance.replacement)).toString());

            return json;
        }
    }
}
