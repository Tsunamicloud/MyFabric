package com.tsunamicloud.tsunami.item;

import com.tsunamicloud.tsunami.Main;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //分别注册mod中新添加的item
    public static final Item RAW_SAUALPITE = registerItem("raw_saualpite",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)));//ItemGroup.MISC
    public static final Item SAUALPITE = registerItem("saualpite",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)));



    //注册物品的方法
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }
    //总的items注册
    public static void registerModItems(){
        Main.LOGGER.debug("Registering Mod Items for" + Main.MOD_ID);
    }
}
