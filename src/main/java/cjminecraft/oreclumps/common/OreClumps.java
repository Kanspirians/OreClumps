package cjminecraft.oreclumps.common;

import cjminecraft.oreclumps.common.init.OCBlocks;
import cjminecraft.oreclumps.common.init.OCItems;
import cjminecraft.oreclumps.common.init.OCLootModifiers;
import cjminecraft.oreclumps.data.*;
import com.google.common.collect.Lists;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreClumps.MODID)
public class OreClumps {

    public static final String MODID = "oreclumps";
    public static final Logger LOGGER = LogManager.getFormatterLogger(MODID);

    public OreClumps() {
        IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();

        mod.addListener(this::gatherData);

        OCItems.ITEMS.register(mod);
        OCBlocks.BLOCKS.register(mod);
        OCBlocks.ITEMS.register(mod);
        OCLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(mod);
    }

    private void gatherData(final GatherDataEvent event) {
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        final DataGenerator generator = event.getGenerator();

        if (event.includeClient()) {
            generator.addProvider(new OCBlockStateProvider(generator, existingFileHelper));
            generator.addProvider(new OCItemModelProvider(generator, existingFileHelper));
            Lists.newArrayList("en_us", "en_gb").forEach(l -> event.getGenerator().addProvider(new OCLanguageProvider(generator, l)));
        }
        if (event.includeServer()) {
            generator.addProvider(new OCGlobalLootModifierProvider(generator));
            generator.addProvider(new OCRecipeProvider(generator));
            generator.addProvider(new OCLootTableProvider(generator));
            final OCBlockTagsProvider blockTagsProvider = new OCBlockTagsProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new OCItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        }
    }
}