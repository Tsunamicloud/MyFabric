package com.tsunamicloud.tsunami.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChangeBlock extends Block {

    private static BooleanProperty COLOR = BooleanProperty.of("color");

    public ChangeBlock(Settings settings) {
        super(settings);
        //设置默认状态
        setDefaultState(this.getStateManager().getDefaultState().with(COLOR,true));
    }

    @Override
    //对状态进行注册
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(COLOR);
    }

    @Override
    //右键使用时改变状态
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        Item item = player.getMainHandStack().getItem();

        if (!state.get(COLOR) && item == Items.DIAMOND){
            if (!player.isCreative()){
                player.getStackInHand(Hand.MAIN_HAND).decrement(1);
            }
            world.setBlockState(pos,state.get(COLOR) ? state.with(COLOR,false) : state.with(COLOR, true));
            return ActionResult.SUCCESS;
        }

        if (state.get(COLOR) && item == Items.GOLD_INGOT){
            if (!player.isCreative()){
                player.getStackInHand(Hand.MAIN_HAND).decrement(1);
            }
            world.setBlockState(pos,state.get(COLOR) ? state.with(COLOR,false) : state.with(COLOR, true));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
