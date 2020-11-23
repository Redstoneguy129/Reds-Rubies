package me.redstoneguy129.mod.redsrubies;

import me.redstoneguy129.mod.redsrubies.client.GuiderPearlRenderer;
import me.redstoneguy129.mod.redsrubies.common.objects.RRBlocks;
import me.redstoneguy129.mod.redsrubies.common.objects.RREntities;
import me.redstoneguy129.mod.redsrubies.common.objects.RRItems;
import me.redstoneguy129.mod.redsrubies.common.objects.entities.GuiderPearlEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("redsrubies")
public class RedsRubies {
    public static final String MOD_ID = "redsrubies";

    public RedsRubies() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        this.registerObjects(eventBus);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(this::doClientStuff));
        eventBus.addListener(this::loadComplete);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RREntities.GUIDER_PEARL.get(), manager -> new GuiderPearlRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
    }

    private void registerObjects(IEventBus eventBus) {
        RREntities.REGISTER.register(eventBus);
        RRBlocks.REGISTER.register(eventBus);
        RRItems.REGISTER.register(eventBus);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, RRBlocks.RUBY_ORE.getDefaultState(), 4))
                                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 8, 5, 18))));
            } else if(BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER)) {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
                                new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, RRBlocks.NETHER_RUBY_ORE.getDefaultState(), 4))
                                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 8, 5, 18))));
            }
        }
    }
}
