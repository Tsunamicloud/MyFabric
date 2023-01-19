package com.tsunamicloud.tsunami.block.custom;

import net.minecraft.block.PressurePlateBlock;

public class ModPressurePlateBlock extends PressurePlateBlock {
    //将protected改成public
    public ModPressurePlateBlock(ActivationRule type, Settings settings) {
        super(type, settings);
    }
}
