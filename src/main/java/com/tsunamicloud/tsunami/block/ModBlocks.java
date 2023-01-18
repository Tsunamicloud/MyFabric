package com.tsunamicloud.tsunami.block;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.custom.JumpyBlock;
import com.tsunamicloud.tsunami.block.custom.MintCropBlock;
import com.tsunamicloud.tsunami.block.custom.SaualpiteLampBlock;
import com.tsunamicloud.tsunami.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {
    public static final Block SAUALPITE_BLOCK = registerBlock("saualpite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),
            ModItemGroup.SAUALPITE, "tooltip.tsunami.saualpite_block");
    public static final Block SAUALPITE_ORE = registerBlock("saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);
    public static final Block DEEPSLATE_SAUALPITE_ORE = registerBlock("deepslate_saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);
    public static final Block NETHERRACK_SAUALPITE_ORE = registerBlock("netherrack_saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);
    public static final Block ENDSTONE_SAUALPITE_ORE = registerBlock("endstone_saualpite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.SAUALPITE);

    public static final Block JUMPY_BLOCK = registerBlock("jumpy_block",
            new JumpyBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITE_LAMP_BLOCK = registerBlock("saualpite_lamp_block",
            new SaualpiteLampBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()
                    .luminance(state -> state.get(SaualpiteLampBlock.LIT) ? 15 : 0)), ModItemGroup.SAUALPITE);
    public static final Block MINT_CROP = registerBlockWithoutItem("mint_crop",
            new MintCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));


    //注册单个方块,且without item的方法
    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }

    //注册单个方块的方法：含tooltip和不含tooltip
    private static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, ItemGroup tab, String tooltipKey){
        registerBlockItem(name, block, tab, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }


    //注册单个方块物品的方法：含tooltip和不含tooltip
    private static Item registerBlockItem(String name, Block block, ItemGroup tab){
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)) {
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(new TranslatableText(tooltipKey));
                    }
                });
    }

    //总的注册方法(注册所有模组方块)
    public static void registerModBlocks(){
        Main.LOGGER.debug("Registering Mod Blocks for" + Main.MOD_ID);
    }
}
