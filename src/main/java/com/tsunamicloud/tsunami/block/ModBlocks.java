package com.tsunamicloud.tsunami.block;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.custom.*;
import com.tsunamicloud.tsunami.item.ModItemGroup;
import com.tsunamicloud.tsunami.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
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
                    .luminance(state -> state.get(SaualpiteLampBlock.LIT) ? 15 : 0)
                    .sounds(ModSounds.SAUALPITE_SOUNDS)), ModItemGroup.SAUALPITE);
    public static final Block MINT_CROP = registerBlockWithoutItem("mint_crop",
            new MintCropBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()));//无需注册item，此block不会被玩家放置

    public static final Block SAUALPITEE_BUTTON = registerBlock("saualpite_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4f).requiresTool().noCollision()), ModItemGroup.SAUALPITE);//button没有碰撞体积
    public static final Block SAUALPITEE_PRESSURE_PLATE = registerBlock("saualpite_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING ,FabricBlockSettings.of(Material.METAL)
                    .strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITEE_FENCE = registerBlock("saualpite_fence",
            new FenceBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITEE_FENCE_GATE = registerBlock("saualpite_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITEE_WALL = registerBlock("saualpite_wall",
            new WallBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);

    public static final Block SAUALPITEE_SLAB = registerBlock("saualpite_slab",
            new SlabBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block SAUALPITEE_STAIRS = registerBlock("saualpite_stairs",
            new ModStairsBlock(ModBlocks.SAUALPITE_BLOCK.getDefaultState() ,
                    FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.SAUALPITE);
    public static final Block TSUNAMI_DOOR = registerBlock("tsunami_door",
            new ModDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4f).requiresTool().nonOpaque()), ModItemGroup.SAUALPITE);//metal材料只能用红石信号打开
    public static final Block TSUNAMI_TRAPDOOR = registerBlock("tsunami_trapdoor",
            new ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4f).requiresTool().nonOpaque()), ModItemGroup.SAUALPITE);

    public static final Block LILAC_FLOWER = registerBlock("lilac_flower",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 12,
                    FabricBlockSettings.copy(Blocks.DANDELION).strength(4f).nonOpaque()), ModItemGroup.SAUALPITE);//蒲公英
    //带花的花盆不会出现在inventory中，故注册时无需将此block关联block item
    public static final Block POTTED_LILAC_FLOWER = registerBlockWithoutBlockItem("potted_lilac_flower",
            new FlowerPotBlock(ModBlocks.LILAC_FLOWER,
                    FabricBlockSettings.copy(Blocks.POTTED_ALLIUM).nonOpaque()), ModItemGroup.SAUALPITE);//allium:葱球

    public static final Block WINTER_WINDOW = registerBlock("winter_window",
            new GlassBlock(FabricBlockSettings.copy(Blocks.GLASS).strength(3.0f).nonOpaque()), ModItemGroup.SAUALPITE);



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
    //注册单个方块，但不关联block item
    private static Block registerBlockWithoutBlockItem(String name, Block block, ItemGroup tab){
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
