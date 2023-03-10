package com.tsunamicloud.tsunami.entity.client;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.entity.custom.RaccoonEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RaccoonModel extends AnimatedGeoModel<RaccoonEntity> {
    @Override
    public Identifier getModelLocation(RaccoonEntity object) {
        return new Identifier(Main.MOD_ID, "geo/raccoon.geo.json");
    }

    @Override
    public Identifier getTextureLocation(RaccoonEntity object) {
        //return new Identifier(Main.MOD_ID, "textures/entity/raccoon/raccoon.png");
        return RaccoonRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationFileLocation(RaccoonEntity animatable) {
        return new Identifier(Main.MOD_ID, "animations/raccoon.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(RaccoonEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}