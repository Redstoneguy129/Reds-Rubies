package me.redstoneguy129.mod.redsrubies.common.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class RubyOre extends OreBlock {
    private final Random rand = new Random();

    public RubyOre() {
        super(Block.Properties.create(Material.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(2));
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(rand, 2, 5) : 0;
    }
}
