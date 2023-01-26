package com.tsunamicloud.tsunami.item.client;

import com.tsunamicloud.tsunami.item.custom.AnimatedBlockItem;
import com.tsunamicloud.tsunami.item.custom.AnimatedItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {
    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }
}
