package com.tsunamicloud.tsunami.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    //自定义树木
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register("jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));



    //自定义花（Source：FlowerSwamp）
    public static final RegistryEntry<PlacedFeature> LILAC_PLACED = PlacedFeatures.register("lilac_placed",
            ModConfiguredFeatures.LILAC_FLOWER, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());



    //自定义矿物
    //主世界
    public static final RegistryEntry<PlacedFeature> SAUALPITE_ORE_PLACED = PlacedFeatures.register("saualpite_ore_placed",
            ModConfiguredFeatures.SAUALPITE_ORE, ModOreFeatures.modifiersWithCount(7,//veins per chunk
                    //distribution of the ore：fixed和above bottom有啥区别？？？
                    HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
    //下界
    public static final RegistryEntry<PlacedFeature> NETHER_SAUALPITE_ORE_PLACED = PlacedFeatures.register("nether_saualpite_ore_placed",
            ModConfiguredFeatures.NETHER_SAUALPITE_ORE, ModOreFeatures.modifiersWithCount(10,
                    //trapezoid经测试可以正常生成
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
    //末地
    public static final RegistryEntry<PlacedFeature> END_SAUALPITE_ORE_PLACED = PlacedFeatures.register("end_saualpite_ore_placed",
            ModConfiguredFeatures.END_SAUALPITE_ORE, ModOreFeatures.modifiersWithCount(10,
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

}
