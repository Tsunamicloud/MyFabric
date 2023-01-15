package com.tsunamicloud.tsunami.block;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block SAUALPITE_BLOCK = registerBlock("saualpite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITE_ORE = registerBlock("saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);
    public static final Block DEEPSLATE_SAUALPITE_ORE = registerBlock("deepslate_saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);



    //注册单个方块的方法
    private static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }
    //注册单个方块物品的方法
    private static Item registerBlockItem(String name, Block block, ItemGroup tab){
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    //总的注册方法(注册所有模组方块)
    public static void registerModBlocks(){
        Main.LOGGER.debug("Registering Mod Blocks for" + Main.MOD_ID);
    }
}