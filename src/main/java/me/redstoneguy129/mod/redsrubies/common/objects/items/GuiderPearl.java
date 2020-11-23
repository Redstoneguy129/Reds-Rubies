package me.redstoneguy129.mod.redsrubies.common.objects.items;

import me.redstoneguy129.mod.redsrubies.common.objects.entities.GuiderPearlEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GuiderPearl extends EnderEyeItem {
    public GuiderPearl() {
        super(new Item.Properties().group(ItemGroup.MATERIALS));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) { return ActionResultType.PASS; }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        if (worldIn instanceof ServerWorld) {
            BlockPos blockpos = ((ServerWorld)worldIn).getChunkProvider().getChunkGenerator().findNearestStructure(worldIn, "Fortress", new BlockPos(playerIn), 100, false);
            if (blockpos != null) {
                GuiderPearlEntity guiderPearlEntity = new GuiderPearlEntity(worldIn, playerIn.getPosX(), playerIn.getPosYHeight(0.5D), playerIn.getPosZ());
                guiderPearlEntity.func_213863_b(itemstack);
                guiderPearlEntity.moveTowards(blockpos);
                worldIn.addEntity(guiderPearlEntity);
                if (playerIn instanceof ServerPlayerEntity) {
                    CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayerEntity)playerIn, blockpos);
                }
                worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                worldIn.playEvent(null, 1003, new BlockPos(playerIn), 0);
                if (!playerIn.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
                playerIn.addStat(Stats.ITEM_USED.get(this));
                playerIn.swing(handIn, true);
                return ActionResult.resultSuccess(itemstack);
            }
        }
        return ActionResult.resultConsume(itemstack);
    }
}
