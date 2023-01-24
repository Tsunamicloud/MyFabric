package com.tsunamicloud.tsunami.world.gen;

import com.tsunamicloud.tsunami.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class ModEntitySpawn {
    public static void addEntitySpawn() {
        //weight:权重？
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.PLAINS),
                SpawnGroup.CREATURE, ModEntities.RACCOON, 25, 2, 5);

        SpawnRestrictionAccessor.callRegister(ModEntities.RACCOON, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}