package me.redstoneguy129.mod.redsrubies.common.objects;

import me.redstoneguy129.mod.redsrubies.RedsRubies;
import me.redstoneguy129.mod.redsrubies.common.objects.items.GuiderPearl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RRItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, RedsRubies.MOD_ID);

    public static final Item RUBY = register("ruby", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final Item RUBY_DUST = register("ruby_dust", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final Item RUBY_NUGGET = register("ruby_nugget", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final Item RUBY_INGOT = register("ruby_ingot", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final Item RUBY_SHARD = register("ruby_shard", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final Item GUIDER_PEARL = register("guider_pearl", GuiderPearl::new);

    private static Item register(String name, Supplier<Item> supplier) {
        REGISTER.register(name, supplier);
        return supplier.get();
    }
}
