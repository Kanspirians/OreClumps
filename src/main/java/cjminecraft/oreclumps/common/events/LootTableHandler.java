package cjminecraft.oreclumps.common.events;

import cjminecraft.oreclumps.common.OreClumps;
import cjminecraft.oreclumps.common.config.OCConfig;
import cjminecraft.oreclumps.common.init.OCItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;

//@Mod.EventBusSubscriber(modid = OreClumps.MODID)
public class LootTableHandler {

    private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));

    private static void replaceLootPool(LootTable table, IItemProvider raw, IItemProvider ore) {
        table.removePool("main");
        table.addPool(LootPool.lootPool().name("main")
                .setRolls(ConstantRange.exactly(1))
                .bonusRolls(0, 0)
                .add(
                        AlternativesLootEntry.alternatives(
                                ItemLootEntry.lootTableItem(ore).when(HAS_SILK_TOUCH),
                                ItemLootEntry.lootTableItem(raw)
                                        .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                        .apply(ExplosionDecay.explosionDecay())
                        )
                )
                .build());
    }

    private static void replaceRecipe(List<String> ores, LootTableManager manager, IItemProvider raw) {
        ores.forEach(oreTableName -> {
            LootTable table = manager.get(new ResourceLocation(oreTableName));
            List<LootEntry> entries = ObfuscationReflectionHelper.getPrivateValue(LootPool.class, table.getPool("main"), "field_186453_a");
            if (entries != null && entries.get(0) instanceof ItemLootEntry) {
                ItemLootEntry entry = (ItemLootEntry) entries.get(0);
                Item ore = ObfuscationReflectionHelper.getPrivateValue(ItemLootEntry.class, entry, "field_186368_a");
                if (ore != null) {
                    replaceLootPool(table, raw, ore);
                    OreClumps.LOGGER.debug("Replaced recipe for " + table.getLootTableId());
                    return;
                }
            }
            OreClumps.LOGGER.debug("Failed to replace recipe for " + table.getLootTableId());
        });
    }


//    @SubscribeEvent
    public static void onLootTableLoad(final AddReloadListenerEvent event) {
        final LootTableManager manager = event.getDataPackRegistries().getLootTables();
        replaceRecipe(OCConfig.SERVER.ironOres.get(), manager, OCItems.RAW_IRON::get);
        replaceRecipe(OCConfig.SERVER.goldOres.get(), manager, OCItems.RAW_GOLD::get);
        replaceRecipe(OCConfig.SERVER.copperOres.get(), manager, OCItems.RAW_COPPER::get);
        replaceRecipe(OCConfig.SERVER.leadOres.get(), manager, OCItems.RAW_LEAD::get);
        replaceRecipe(OCConfig.SERVER.nickelOres.get(), manager, OCItems.RAW_NICKEL::get);
        replaceRecipe(OCConfig.SERVER.silverOres.get(), manager, OCItems.RAW_SILVER::get);
        replaceRecipe(OCConfig.SERVER.tinOres.get(), manager, OCItems.RAW_TIN::get);
    }

}