package com.tsunamicloud.tsunami.item.client;

import com.tsunamicloud.tsunami.item.custom.AnimatedItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AnimatedItemRenderer extends GeoItemRenderer<AnimatedItem> {
    public AnimatedItemRenderer() {
        super(new AnimatedItemModel());
    }
}
