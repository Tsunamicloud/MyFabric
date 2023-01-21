package com.tsunamicloud.tsunami.util;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.command.ReturnHomeCommand;
import com.tsunamicloud.tsunami.command.SetHomeCommand;
import com.tsunamicloud.tsunami.event.ModPlayerEventCopyFrom;
import com.tsunamicloud.tsunami.item.ModItems;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
        registerCommands();
        registerEvents();
    }



    //供本类调用的private方法
    private static void registerFuels() {
        Main.LOGGER.info("Registering Fuels for " + Main.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.LILAC_FLOWER_BULB, 200);//单位:tick
    }
    private static void registerCommands(){
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }
    public static void registerEvents(){
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }

}
