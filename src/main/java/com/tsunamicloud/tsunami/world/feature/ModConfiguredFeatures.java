package com.tsunamicloud.tsunami.world.feature;


import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.*;


import java.util.List;

public class ModConfiguredFeatures {
    //主世界
    public static final List<OreFeatureConfig.Target> OVERWORLD_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAUALPITE_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAUALPITE_ORE.getDefaultState())
    );
    //下界
    public static final List<OreFeatureConfig.Target> NETHER_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, ModBlocks.NETHERRACK_SAUALPITE_ORE.getDefaultState())
    );
    //末地
    public static final List<OreFeatureConfig.Target> END_SAUALPITE_ORES = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.ENDSTONE_SAUALPITE_ORE.getDefaultState())
    );


    //public static final RegistryEntryAddedCallback<ConfiguredFeature<OreFeatureConfig, ?>> SAUALPITE_ORE =
            //ConfiguredFeatures.register("saualpite_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_SAUALPITE_ORES, 9));


    public static void registerConfiguredFeatures(){
        Main.LOGGER.debug("Registering the ModConfiguredFeatures for" + Main.MOD_ID);
    }
}
