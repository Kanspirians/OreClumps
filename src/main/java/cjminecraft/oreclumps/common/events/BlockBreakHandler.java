package cjminecraft.oreclumps.common.events;

import cjminecraft.oreclumps.common.config.OCConfig;
import cjminecraft.oreclumps.common.init.OCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

//@Mod.EventBusSubscriber(modid = OreClumps.MODID)
public class BlockBreakHandler {

    private static boolean processOre(final ResourceLocation registryName, List<String> ores, Item raw, PlayerEntity player, BlockState state, World level, BlockPos pos) {
        if (ores.contains(registryName.toString())) {
            ItemStack hand = player.getMainHandItem();
            ItemStack copy = hand.copy();
            boolean canHarvest = state.canHarvestBlock(level, pos, player); // previously player.hasCorrectToolForDrops(blockstate)
            hand.mineBlock(level, state, pos, player);
            if (hand.isEmpty() && !copy.isEmpty())
                ForgeEventFactory.onPlayerDestroyItem(player, copy, Hand.MAIN_HAND);
            boolean removed = state.removedByPlayer(level, pos, player, canHarvest, level.getFluidState(pos));

            int bonusLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, player.getMainHandItem());
            int silklevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getMainHandItem());

            if (removed && canHarvest) {
                if (silklevel >= 1) {
                    return false;
                } else {
                    player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
                    player.causeFoodExhaustion(0.005F);
                    int count = level.random.nextInt(bonusLevel + 2) - 1;
                    if (count < 0)
                        count = 0;
                    ItemStack stack = raw.getDefaultInstance();
                    stack.setCount(count + 1);
                    Block.popResource(level, pos, stack);
                }
                state.getBlock().playerDestroy(level, player, pos, state, level.getBlockEntity(pos), copy);
            }
            int exp = state.getExpDrop(level, pos, bonusLevel, silklevel);

            if (removed && exp > 0 && level instanceof ServerWorld)
                state.getBlock().popExperience((ServerWorld) level, pos, exp);

            return true;
        }
        return false;
    }

//    @SubscribeEvent
    public static void onBreakBlock(final BlockEvent.BreakEvent event) {
        ResourceLocation location = event.getState().getBlock().getRegistryName();
        if (location != null)
            event.setCanceled(
                    processOre(location, OCConfig.SERVER.ironOres.get(), OCItems.RAW_IRON.get(), event.getPlayer(), event.getState(), event.getPlayer().level, event.getPos())
            );
    }

}
