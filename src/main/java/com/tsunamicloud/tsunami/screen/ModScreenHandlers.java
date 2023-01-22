package com.tsunamicloud.tsunami.screen;

import com.tsunamicloud.tsunami.Main;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<SaualpiteBlasterScreenHandler> SAUALPITE_BLASTER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        SAUALPITE_BLASTER_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(new Identifier(Main.MOD_ID, "saualpite_blaster"),
                        SaualpiteBlasterScreenHandler::new);
    }
}