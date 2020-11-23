package me.redstoneguy129.mod.redsrubies.common.objects.entities;

import me.redstoneguy129.mod.redsrubies.common.objects.RREntities;
import me.redstoneguy129.mod.redsrubies.common.objects.RRItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.item.EyeOfEnderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = IRendersAsItem.class
)
public class GuiderPearlEntity extends EyeOfEnderEntity implements IRendersAsItem {
    private static final DataParameter<ItemStack> field_213864_b = EntityDataManager.createKey(GuiderPearlEntity.class, DataSerializers.ITEMSTACK);

    public GuiderPearlEntity(EntityType<? extends GuiderPearlEntity> entityType, World world) {
        super(RREntities.GUIDER_PEARL.get(), world);
    }

    public GuiderPearlEntity(World worldIn, double x, double y, double z) {
        this(RREntities.GUIDER_PEARL.get(), worldIn);
        this.despawnTimer = 0;
        this.setPosition(x, y, z);
        System.out.println("Spawned");
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemstack = this.func_213861_i();
        return itemstack.isEmpty() ? new ItemStack(RRItems.GUIDER_PEARL) : itemstack;
    }

    @Override
    public void func_213863_b(ItemStack p_213863_1_) {
        if (p_213863_1_.getItem() != RRItems.GUIDER_PEARL || p_213863_1_.hasTag()) {
            this.getDataManager().set(field_213864_b, Util.make(p_213863_1_.copy(), (p_213862_0_) -> {
                p_213862_0_.setCount(1);
            }));
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return new SSpawnObjectPacket(this);
    }

    private ItemStack func_213861_i() {
        return this.getDataManager().get(field_213864_b);
    }

    @Override
    protected void registerData() {
        this.getDataManager().register(field_213864_b, ItemStack.EMPTY);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        ItemStack itemstack = this.func_213861_i();
        if (!itemstack.isEmpty()) {
            compound.put("Item", itemstack.write(new CompoundNBT()));
        }

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        ItemStack itemstack = ItemStack.read(compound.getCompound("Item"));
        this.func_213863_b(itemstack);
    }
}
