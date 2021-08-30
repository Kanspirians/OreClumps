package cjminecraft.oreclumps.data;

import cjminecraft.oreclumps.common.OreClumps;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OCItemModelProvider extends ItemModelProvider {

    public OCItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OreClumps.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generated("raw_iron");
        generated("raw_gold");
        generated("raw_copper");
        generated("raw_lead");
        generated("raw_nickel");
        generated("raw_silver");
        generated("raw_tin");
        generated("raw_uranium");
        generated("raw_osmium");
        generated("raw_zinc");

        block("raw_iron_block");
        block("raw_gold_block");
        block("raw_copper_block");
        block("raw_lead_block");
        block("raw_nickel_block");
        block("raw_silver_block");
        block("raw_tin_block");
        block("raw_uranium_block");
        block("raw_osmium_block");
        block("raw_zinc_block");

        generated("copper_ingot");
        generated("lead_ingot");
        generated("nickel_ingot");
        generated("silver_ingot");
        generated("tin_ingot");
        generated("uranium_ingot");
        generated("osmium_ingot");
        generated("zinc_ingot");
    }

    private void block(String name) {
        withExistingParent(name, "oreclumps:block/" + name);
    }

    private void generated(String name) {
        withExistingParent(name, "item/generated").texture("layer0", "oreclumps:item/" + name);
    }
}
