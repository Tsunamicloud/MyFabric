package com.tsunamicloud.tsunami.blocks.entities;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BreakEntity extends BlockEntity {
    /*public BreakEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);*/
    public BreakEntity(BlockPos pos, BlockState state) {
            super(Main.BREAK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos){
       //使方块上方的非空气方块变成掉落物
        BlockPos up = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
        if(!world.getBlockState(up).isAir()){
            Item item = world.getBlockState(up).getBlock().asItem();
            world.setBlockState(up, Blocks.AIR.getDefaultState());
            ItemEntity itemEntity = new ItemEntity(world, up.getX(), up.getY(), up.getZ(),new ItemStack(item, 1 ));
            world.spawnEntity(itemEntity);
        }
    }

}
