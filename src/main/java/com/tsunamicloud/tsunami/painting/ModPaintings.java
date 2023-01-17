package com.tsunamicloud.tsunami.painting;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {

    //注册单个图画，宽和高的单位：pixel
    public static final PaintingMotive MARATHON = registerPainting("marathon", new PaintingMotive(16, 16));
    public static final PaintingMotive FAMILY = registerPainting("family", new PaintingMotive(16, 32));

    //注册单个图画的方法
    private static PaintingMotive registerPainting(String name, PaintingMotive paintingMotive){
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(Main.MOD_ID, name), paintingMotive);
    }

    //注册模组的图画的方法
    public static void registerModPaintings(){
        Main.LOGGER.debug("Registering Paintings for" + Main.MOD_ID);
    }
}
