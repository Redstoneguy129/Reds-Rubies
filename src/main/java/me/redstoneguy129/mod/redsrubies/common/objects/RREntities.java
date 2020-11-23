package me.redstoneguy129.mod.redsrubies.common.objects;

import me.redstoneguy129.mod.redsrubies.RedsRubies;
import me.redstoneguy129.mod.redsrubies.common.objects.entities.GuiderPearlEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RREntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, RedsRubies.MOD_ID);

    public static final RegistryObject<EntityType<GuiderPearlEntity>> GUIDER_PEARL = REGISTER.register("guider_pearl", () -> EntityType.Builder.<GuiderPearlEntity>create(GuiderPearlEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(RedsRubies.MOD_ID+":guider_pearl"));
}
