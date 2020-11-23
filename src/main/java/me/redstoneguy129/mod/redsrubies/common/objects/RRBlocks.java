package me.redstoneguy129.mod.redsrubies.common.objects;

import me.redstoneguy129.mod.redsrubies.RedsRubies;
import me.redstoneguy129.mod.redsrubies.common.objects.blocks.RubyOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RRBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, RedsRubies.MOD_ID);

    public static final Block RUBY_ORE = register("ruby_ore",
            new RubyOre(),
            new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
    public static final Block NETHER_RUBY_ORE = register("nether_ruby_ore",
            new RubyOre(),
            new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
    public static final Block RUBY_BLOCK = register("ruby_block",
            new Block(Block.Properties.create(Material.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(2)),
            new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));

    private static Block register(String name, Block supplier, Item.Properties properties) {
        REGISTER.register(name, () -> supplier);
        RRItems.REGISTER.register(name, () -> new BlockItem(supplier, properties));
        return supplier;
    }
}
