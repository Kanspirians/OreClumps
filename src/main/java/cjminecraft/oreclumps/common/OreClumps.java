package cjminecraft.oreclumps.common;

import cjminecraft.oreclumps.common.config.OCConfig;
import cjminecraft.oreclumps.common.init.OCBlocks;
import cjminecraft.oreclumps.common.init.OCItems;
import cjminecraft.oreclumps.data.OCBlockStateProvider;
import cjminecraft.oreclumps.data.OCItemModelProvider;
import cjminecraft.oreclumps.data.OCLanguageProvider;
import com.google.common.collect.Lists;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreClumps.MODID)
public class OreClumps {

    public static final String MODID = "oreclumps";
    public static final Logger LOGGER = LogManager.getFormatterLogger(MODID);

    public OreClumps() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, OCConfig.SERVER_SPEC);

        IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();

        mod.addListener(this::gatherData);
        mod.addListener(this::setup);

        OCItems.ITEMS.register(mod);
        OCBlocks.BLOCKS.register(mod);
        OCBlocks.ITEMS.register(mod);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // imc

    }

    private void gatherData(final GatherDataEvent event) {
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();

        if (event.includeClient()) {
            event.getGenerator().addProvider(new OCBlockStateProvider(generator, existingFileHelper));
            event.getGenerator().addProvider(new OCItemModelProvider(generator, existingFileHelper));
            Lists.newArrayList("en_us").forEach(l -> event.getGenerator().addProvider(new OCLanguageProvider(generator, l)));
        }
    }

    // todo IMC to add ores to appropriate lists
    // todo recipes for copper and others
}
