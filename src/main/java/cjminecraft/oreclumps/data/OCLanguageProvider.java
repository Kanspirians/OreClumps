package cjminecraft.oreclumps.data;

import cjminecraft.oreclumps.common.OreClumps;
import cjminecraft.oreclumps.common.init.OCBlocks;
import cjminecraft.oreclumps.common.init.OCItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class OCLanguageProvider extends LanguageProvider {

    public OCLanguageProvider(DataGenerator gen, String locale) {
        super(gen, OreClumps.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        final String locale = this.getName().substring("Languages: ".length());

        switch (locale) {
            case "en_us":
                addItem(OCItems.RAW_IRON, "Raw Iron");
                addItem(OCItems.RAW_GOLD, "Raw Gold");
                addItem(OCItems.RAW_COPPER, "Raw Copper");
                addItem(OCItems.RAW_LEAD, "Raw Lead");
                addItem(OCItems.RAW_NICKEL, "Raw Nickel");
                addItem(OCItems.RAW_SILVER, "Raw Silver");
                addItem(OCItems.RAW_TIN, "Raw Tin");
                addItem(OCItems.RAW_URANIUM, "Raw Uranium");
                addItem(OCItems.RAW_OSMIUM, "Raw Osmium");
                addItem(OCItems.RAW_ZINC, "Raw Zinc");

                addItem(OCItems.COPPER_INGOT, "Copper Ingot");
                addItem(OCItems.LEAD_INGOT, "Lead Ingot");
                addItem(OCItems.NICKEL_INGOT, "Nickel Ingot");
                addItem(OCItems.SILVER_INGOT, "Silver Ingot");
                addItem(OCItems.TIN_INGOT, "Tin Ingot");
                addItem(OCItems.URANIUM_INGOT, "Uranium Ingot");
                addItem(OCItems.OSMIUM_INGOT, "Osmium Ingot");
                addItem(OCItems.ZINC_INGOT, "Zinc Ingot");

                addBlock(OCBlocks.RAW_IRON_BLOCK, "Block of Raw Iron");
                addBlock(OCBlocks.RAW_GOLD_BLOCK, "Block of Raw Gold");
                addBlock(OCBlocks.RAW_COPPER_BLOCK, "Block of Raw Copper");
                addBlock(OCBlocks.RAW_LEAD_BLOCK, "Block of Raw Lead");
                addBlock(OCBlocks.RAW_NICKEL_BLOCK, "Block of Raw Nickel");
                addBlock(OCBlocks.RAW_SILVER_BLOCK, "Block of Raw Silver");
                addBlock(OCBlocks.RAW_TIN_BLOCK, "Block of Raw Tin");
                addBlock(OCBlocks.RAW_URANIUM_BLOCK, "Block of Raw Uranium");
                addBlock(OCBlocks.RAW_OSMIUM_BLOCK, "Block of Raw Osmium");
                addBlock(OCBlocks.RAW_ZINC_BLOCK, "Block of Raw Zinc");
                break;
        }
    }
}
