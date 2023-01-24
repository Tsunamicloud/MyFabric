package com.tsunamicloud.tsunami.world.feature;


import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


import java.util.List;

public class ModConfiguredFeatures {

    //从树苗长成树，TreeFeatureConfig与树的外观有关
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            ConfiguredFeatures.register("jacaranda_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),//Foliage：树叶
                    new TwoLayersFeatureSize(1, 0, 2))
                    //防止树木下的方块变成dirt
                    .dirtProvider(BlockStateProvider.of(ModBlocks.SAUALPITE_BLOCK)).build());

    //树苗的放置，能放置在其上的方块
    //ConfiguredFeature -> PlacedFeature -> CF -> PF
    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5f)),
                            JACARANDA_CHECKED));




    //花的生成
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LILAC_FLOWER =
            ConfiguredFeatures.register("lilac_flower", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LILAC_FLOWER)))));






    //矿物的生成
    //主世界
    public static final List<OreFeatureConfig.Target> OVERWORLD_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.SAUALPITE_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_SAUALPITE_ORE.getDefaultState()));
    //下界
    public static final List<OreFeatureConfig.Target> NETHER_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER,
                    ModBlocks.NETHERRACK_SAUALPITE_ORE.getDefaultState()));
    //末地
    public static final List<OreFeatureConfig.Target> END_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.ENDSTONE_SAUALPITE_ORE.getDefaultState()));

    //主世界
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> SAUALPITE_ORE =
            ConfiguredFeatures.register("saualpite_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_SAUALPITE_ORES, 9));//size:矿脉的大小
    //下界
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NETHER_SAUALPITE_ORE =
            ConfiguredFeatures.register("nether_saualpite_ore", Feature.ORE,
                    new OreFeatureConfig(NETHER_SAUALPITE_ORES, 12));
    //末地
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_SAUALPITE_ORE =
            ConfiguredFeatures.register("end_saualpite_ore", Feature.ORE,
                    new OreFeatureConfig(END_SAUALPITE_ORES, 12));






    public static void registerConfiguredFeatures(){
        Main.LOGGER.debug("Registering the ModConfiguredFeatures for" + Main.MOD_ID);
    }
}
