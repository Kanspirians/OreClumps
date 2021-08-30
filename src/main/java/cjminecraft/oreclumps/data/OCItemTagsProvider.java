package cjminecraft.oreclumps.data;

import cjminecraft.oreclumps.common.OreClumps;
import cjminecraft.oreclumps.common.init.OCItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class OCItemTagsProvider extends ItemTagsProvider {
    public OCItemTagsProvider(DataGenerator generator, BlockTagsProvider provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, OreClumps.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.INGOTS).add(
                OCItems.COPPER_INGOT.get(),
                OCItems.LEAD_INGOT.get(),
                OCItems.NICKEL_INGOT.get(),
                OCItems.SILVER_INGOT.get(),
                OCItems.TIN_INGOT.get(),
                OCItems.OSMIUM_INGOT.get(),
                OCItems.URANIUM_INGOT.get(),
                OCItems.ZINC_INGOT.get()
        );

        tag(Tags.Items.ORES).add(
                OCItems.RAW_IRON.get(),
                OCItems.RAW_GOLD.get(),
                OCItems.RAW_COPPER.get(),
                OCItems.RAW_LEAD.get(),
                OCItems.RAW_NICKEL.get(),
                OCItems.RAW_SILVER.get(),
                OCItems.RAW_TIN.get(),
                OCItems.RAW_OSMIUM.get(),
                OCItems.RAW_URANIUM.get(),
                OCItems.RAW_ZINC.get()
        );

        tag(Tags.Items.ORES_IRON).add(OCItems.RAW_IRON.get());
        tag(Tags.Items.ORES_GOLD).add(OCItems.RAW_GOLD.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/copper")))
                .add(OCItems.RAW_COPPER.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/lead")))
                .add(OCItems.RAW_LEAD.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/nickel")))
                .add(OCItems.RAW_NICKEL.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/silver")))
                .add(OCItems.RAW_SILVER.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/tin")))
                .add(OCItems.RAW_TIN.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/osmium")))
                .add(OCItems.RAW_OSMIUM.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/uranium")))
                .add(OCItems.RAW_URANIUM.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ores/zinc")))
                .add(OCItems.RAW_ZINC.get());

        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/copper")))
                .add(OCItems.COPPER_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/lead")))
                .add(OCItems.LEAD_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/nickel")))
                .add(OCItems.NICKEL_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/silver")))
                .add(OCItems.SILVER_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/tin")))
                .add(OCItems.TIN_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/osmium")))
                .add(OCItems.OSMIUM_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/uranium")))
                .add(OCItems.URANIUM_INGOT.get());
        tag(ItemTags.createOptional(new ResourceLocation("forge", "ingots/zinc")))
                .add(OCItems.ZINC_INGOT.get());
    }
}
