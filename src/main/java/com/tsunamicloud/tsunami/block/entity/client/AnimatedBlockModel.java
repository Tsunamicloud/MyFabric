package com.tsunamicloud.tsunami.block.entity.client;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.entity.AnimatedBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockModel extends AnimatedGeoModel<AnimatedBlockEntity> {
    @Override
    public Identifier getModelLocation(AnimatedBlockEntity object) {
        return new Identifier(Main.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AnimatedBlockEntity object) {
        return new Identifier(Main.MOD_ID, "textures/machines/animated_block.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AnimatedBlockEntity animatable) {
        return new Identifier(Main.MOD_ID, "animations/animated_block.animation.json");
    }
}
