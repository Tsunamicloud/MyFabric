package com.tsunamicloud.tsunami;

import com.tsunamicloud.tsunami.block.ModBlocks;
import com.tsunamicloud.tsunami.block.entity.ModBlockEntities;
import com.tsunamicloud.tsunami.block.entity.client.AnimatedBlockRenderer;
import com.tsunamicloud.tsunami.commands.NbtCommand;
import com.tsunamicloud.tsunami.entity.ModEntities;
import com.tsunamicloud.tsunami.entity.client.RaccoonRenderer;
import com.tsunamicloud.tsunami.entity.client.armor.SaualpiteArmorRenderer;
import com.tsunamicloud.tsunami.fluid.ModFluids;
import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.item.client.AnimatedBlockItemRenderer;
import com.tsunamicloud.tsunami.item.client.AnimatedItemRenderer;
import com.tsunamicloud.tsunami.particle.ModParticles;
import com.tsunamicloud.tsunami.particle.custom.CitrineParticle;
import com.tsunamicloud.tsunami.screen.ModScreenHandlers;
import com.tsunamicloud.tsunami.screen.SaualpiteBlasterScreen;
import com.tsunamicloud.tsunami.screen.UIBlockScreen;
import com.tsunamicloud.tsunami.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        //自定义non-block的渲染（opaque）
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TSUNAMI_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TSUNAMI_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LILAC_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILAC_FLOWER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MINT_CROP, RenderLayer.getCutout());//自定义作物

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_WINDOW, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SAUALPITE_BLASTER, RenderLayer.getCutout());



        //自定义UI
        ScreenRegistry.register(Main.UI_BLOCK_SCREEN_HANDLER, UIBlockScreen::new);

        ScreenRegistry.register(ModScreenHandlers.SAUALPITE_BLASTER_SCREEN_HANDLER, SaualpiteBlasterScreen::new);


        //自定义命令
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, dedicated) -> NbtCommand.register(dispatcher)
        );

        //自定义粒子效果
        ClientLifecycleEvents.CLIENT_STARTED.register(
                client -> {
                    ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
                        registry.register(new Identifier("tsunami", "particle/red_star"));
                    });
                    ParticleFactoryRegistry.getInstance().register(Particles.RED_STAR, FlameParticle.Factory::new);
                }
        );

        ParticleFactoryRegistry.getInstance().register(ModParticles.CITRINE_PARTICLE, CitrineParticle.Factory::new);



        //自定义弓
        ModModelPredicateProvider.registerModModels();




        //自定义液体
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_STILL,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));//液体的颜色
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_FLOWING,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));//液体的颜色


        //自定义实体
        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);

        //自定义3d盔甲模型
        GeoArmorRenderer.registerArmorRenderer(new SaualpiteArmorRenderer(), ModItems.SAUALPITE_BOOTS,
                ModItems.SAUALPITE_LEGGINGS, ModItems.SAUALPITE_CHESTPLATE, ModItems.SAUALPITE_HELMET);

        //自定义带动画的物品
        GeoItemRenderer.registerItemRenderer(ModItems.ANIMATED_ITEM, new AnimatedItemRenderer());

        //自定义带动画的方块
        GeoItemRenderer.registerItemRenderer(ModItems.ANIMATED_BLOCK_ITEM, new AnimatedBlockItemRenderer());
        BlockEntityRendererRegistry.register(ModBlockEntities.ANIMATED_BLOCK_ENTITY, AnimatedBlockRenderer::new);

    }
}
