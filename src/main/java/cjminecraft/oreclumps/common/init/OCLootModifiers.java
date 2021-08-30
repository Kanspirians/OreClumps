package cjminecraft.oreclumps.common.init;

import cjminecraft.oreclumps.common.OreClumps;
import cjminecraft.oreclumps.common.loot.RawOreModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OCLootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, OreClumps.MODID);

    public static final RegistryObject<GlobalLootModifierSerializer<RawOreModifier>> RAW_ORE_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("raw_ore", RawOreModifier.Serializer::new);

}
