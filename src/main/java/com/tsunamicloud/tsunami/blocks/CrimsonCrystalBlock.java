package com.tsunamicloud.tsunami.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CrimsonCrystalBlock extends Block {
    public CrimsonCrystalBlock(Settings settings) {
        super(settings);
    }

    //16 2 16对应你自定义模型的尺寸
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0,0.0,16.0,2.0,16.0);

    @Override
    //重写获得碰撞箱的方法
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
