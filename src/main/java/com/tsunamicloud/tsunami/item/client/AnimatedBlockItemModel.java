package com.tsunamicloud.tsunami.item.client;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.item.custom.AnimatedBlockItem;
import com.tsunamicloud.tsunami.item.custom.AnimatedItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedBlockItemModel extends AnimatedGeoModel<AnimatedBlockItem> {
    @Override
    public Identifier getModelLocation(AnimatedBlockItem object) {
        return new Identifier(Main.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AnimatedBlockItem object) {
        return new Identifier(Main.MOD_ID, "textures/machines/animated_block.png");
        //return new Identifier(Main.MOD_ID, "textures/item/animated_block_texture.png");

    }

    @Override
    public Identifier getAnimationFileLocation(AnimatedBlockItem animatable) {
        return new Identifier(Main.MOD_ID, "animations/animated_block.animation.json");
    }
}
