package com.tsunamicloud.tsunami.entity.client.armor;

import com.tsunamicloud.tsunami.item.custom.SaualpiteArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SaualpiteArmorRenderer extends GeoArmorRenderer<SaualpiteArmorItem> {
    public SaualpiteArmorRenderer() {
        super(new SauapiteArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
