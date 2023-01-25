package com.tsunamicloud.tsunami.entity.client.armor;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.item.custom.SaualpiteArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SauapiteArmorModel extends AnimatedGeoModel<SaualpiteArmorItem> {
    @Override
    public Identifier getModelLocation(SaualpiteArmorItem object) {
        return new Identifier(Main.MOD_ID, "geo/saualpite_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SaualpiteArmorItem object) {
        return new Identifier(Main.MOD_ID, "textures/models/armor/saualpite_armor_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SaualpiteArmorItem animatable) {
        return new Identifier(Main.MOD_ID, "animations/armor_animation.json");
    }
}

