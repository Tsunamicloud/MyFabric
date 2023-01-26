package com.tsunamicloud.tsunami.item.client;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.item.custom.AnimatedItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimatedItemModel extends AnimatedGeoModel<AnimatedItem> {
    @Override
    public Identifier getModelLocation(AnimatedItem object) {
        return new Identifier(Main.MOD_ID, "geo/animated_item.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AnimatedItem object) {
        return new Identifier(Main.MOD_ID, "textures/item/animated_item_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AnimatedItem animatable) {
        return new Identifier(Main.MOD_ID, "animations/animated_item.animation.json");
    }
}
