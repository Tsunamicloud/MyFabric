package com.tsunamicloud.tsunami.block.entity;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<SaualpiteBlasterBlockEntity> SAUALPITE_BLASTER;
    public static BlockEntityType<AnimatedBlockEntity> ANIMATED_BLOCK_ENTITY;


    public static void registerAllBlockEntities() {
        SAUALPITE_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Main.MOD_ID, "mythril_blaster"),
                FabricBlockEntityTypeBuilder.create(SaualpiteBlasterBlockEntity::new,
                        ModBlocks.SAUALPITE_BLASTER).build(null));

        ANIMATED_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Main.MOD_ID, "animated_block_entity"),
                FabricBlockEntityTypeBuilder.create(AnimatedBlockEntity::new,
                        ModBlocks.ANIMATED_BLOCK).build(null));
    }
}
