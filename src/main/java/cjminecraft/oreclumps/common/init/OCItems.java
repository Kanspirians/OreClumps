package cjminecraft.oreclumps.common.init;

import cjminecraft.oreclumps.common.OreClumps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OCItems {

    private static final Item.Properties PROPERTIES = new Item.Properties().tab(ItemGroup.TAB_MATERIALS);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OreClumps.MODID);

    public static final RegistryObject<Item> RAW_IRON = ITEMS.register("raw_iron", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_GOLD = ITEMS.register("raw_gold", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_COPPER = ITEMS.register("raw_copper", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_OSMIUM = ITEMS.register("raw_osmium", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new Item(PROPERTIES));

    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> OSMIUM_INGOT = ITEMS.register("osmium_ingot", () -> new Item(PROPERTIES));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(PROPERTIES));

}
