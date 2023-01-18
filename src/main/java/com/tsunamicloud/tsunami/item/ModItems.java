package com.tsunamicloud.tsunami.item;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.ModBlocks;
import com.tsunamicloud.tsunami.item.custom.DowsingRodItem;
import com.tsunamicloud.tsunami.item.custom.EightBallItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //分别注册mod中新添加的item
    public static final Item RAW_SAUALPITE = registerItem("raw_saualpite",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)));//ItemGroup.MISC
    public static final Item SAUALPITE = registerItem("saualpite",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)));
    public static final Item EIGHT_BALL = registerItem("eight_ball",
            new EightBallItem(new FabricItemSettings().group(ModItemGroup.SAUALPITE).maxCount(1)));//最大堆叠数为1
    //注册薄荷种子
    public static final Item MINT_SEEDS = registerItem("mint_seeds",
            new AliasedBlockItem(ModBlocks.MINT_CROP, new FabricItemSettings().group(ModItemGroup.SAUALPITE)));
    //注册薄荷果实
    public static final Item MINT = registerItem("mint",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)
                    .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.SAUALPITE).maxDamage(16)));//maxDamage会同时更改最大堆叠数，因为有maxDamage的物品不能堆叠
    public static final Item LILAC_FLOWER_BULB = registerItem("lilac_flower_bulb",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE)));
    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ModItemGroup.SAUALPITE).food(ModFoodComponents.GRAPE)));



    //注册单个物品的方法
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }
    //总的items注册
    public static void registerModItems(){
        Main.LOGGER.debug("Registering Mod Items for" + Main.MOD_ID);
    }
}
