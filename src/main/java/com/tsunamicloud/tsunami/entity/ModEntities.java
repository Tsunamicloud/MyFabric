package com.tsunamicloud.tsunami.entity;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.entity.custom.RaccoonEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<RaccoonEntity> RACCOON = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "raccoon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RaccoonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.3f)).build());
}

