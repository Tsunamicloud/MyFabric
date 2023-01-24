package com.tsunamicloud.tsunami.entity.client;

import com.google.common.collect.Maps;
import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.entity.custom.RaccoonEntity;
import com.tsunamicloud.tsunami.entity.variant.RaccoonVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity> {

    public static final Map<RaccoonVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RaccoonVariant.class), (map) -> {
                map.put(RaccoonVariant.DEFAULT,
                        new Identifier(Main.MOD_ID, "textures/entity/raccoon/raccoon.png"));
                map.put(RaccoonVariant.DARK,
                        new Identifier(Main.MOD_ID, "textures/entity/raccoon/dark_raccoon.png"));
                map.put(RaccoonVariant.RED,
                        new Identifier(Main.MOD_ID, "textures/entity/raccoon/red_raccoon.png"));
            });





    public RaccoonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RaccoonModel());
    }

    @Override
    public Identifier getTextureLocation(RaccoonEntity instance) {
        //return new Identifier(Main.MOD_ID, "textures/entity/raccoon/raccoon.png");
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderLayer getRenderType(RaccoonEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.5f, 0.5f, 0.5f);
        } else {
            stack.scale(1f, 1f, 1f);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }

}
