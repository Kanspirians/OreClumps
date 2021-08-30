package cjminecraft.oreclumps.data;

import cjminecraft.oreclumps.common.OreClumps;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class OCBlockTagsProvider extends BlockTagsProvider {
    public OCBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, OreClumps.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
