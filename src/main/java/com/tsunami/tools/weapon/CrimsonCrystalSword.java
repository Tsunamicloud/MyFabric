package com.tsunami.tools.weapon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class CrimsonCrystalSword extends SwordItem {
    public CrimsonCrystalSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    //在创造模式下可以破坏方块
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return true;
    }

    @Override
    //挖掘完后给user一颗钻石
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        //传入miner为LivingEntity，对miner进行强转
        miner = (PlayerEntity)miner;
        ((PlayerEntity) miner).giveItemStack(Items.DIAMOND.getDefaultStack());
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    //攻击生物后产生爆炸
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        //传入的参数中没有world，需要自己创建
        World world = attacker.getWorld();
        world.createExplosion(attacker,attacker.getX(),attacker.getY(),attacker.getZ(),10,true, Explosion.DestructionType.DESTROY);
        return super.postHit(stack, target, attacker);
    }
}
