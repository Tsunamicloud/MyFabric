package com.tsunamicloud.tsunami.tools;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrimsonCrystalAxe extends AxeItem {
    public CrimsonCrystalAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    //挖掘完后破坏三个方块,y,y+1,y-1
    //小知识：有掉落物的时候才会掉落
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        world.breakBlock(getPos(pos,1),true);
        world.breakBlock(getPos(pos,-1),true);
        return super.postMine(stack, world, state, pos, miner);
    }

    //写一个获取方块坐标的方法
    private BlockPos getPos(BlockPos pos,int y){
        pos = new BlockPos(pos.getX(),pos.getY()+y,pos.getZ());
        return pos;
    }
}

