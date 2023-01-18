package com.tsunamicloud.tsunami.util;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
    }

    //供本类调用的private方法
    private static void registerFuels() {
        Main.LOGGER.info("Registering Fuels for " + Main.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.LILAC_FLOWER_BULB, 200);//单位:tick
    }
}
