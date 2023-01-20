package com.tsunamicloud.tsunami.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SaualpiteLampBlock extends Block {
    //新建一个property
    public static final BooleanProperty LIT = BooleanProperty.of("lit");//property有enum，int，boolean
    //数据结构为什么不能直接boolean而是要用BooleanProperty：boolean会改变所有该种方块的状态，后者只会改变当前这个方块

    public SaualpiteLampBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //条件判断：在server端，且主手
        if (!world.isClient() && hand == Hand.MAIN_HAND){
            world.setBlockState(pos, state.cycle(LIT));
            //world.setBlockState(pos, state.with(LIT, !currentState), Block.NOTIFY_ALL)
        }

        return ActionResult.SUCCESS;
    }

    //新建的property需要append
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
