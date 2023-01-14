package com.tsunami.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NumberBlock extends Block {

    //int类型的方块状态
    public static final IntProperty NUMBER = IntProperty.of("number",1,3);

    public NumberBlock(Settings settings) {
        super(settings);
        //默认值
        setDefaultState(this.getStateManager().getDefaultState().with(NUMBER, 1));
    }





    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NUMBER);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        switch (state.get(NUMBER)){
            case 1 :
                world.setBlockState(pos, state.with(NUMBER, 2));
                return ActionResult.SUCCESS;
            case 2 :
                world.setBlockState(pos, state.with(NUMBER, 3));
                return ActionResult.SUCCESS;
            case 3 :
                world.setBlockState(pos, state.with(NUMBER, 1));
                return ActionResult.SUCCESS;
            default:
                return ActionResult.PASS;
        }
    }
}
