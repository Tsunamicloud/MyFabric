package com.tsunamicloud.tsunami.blocks;

import com.tsunamicloud.tsunami.blocks.entities.BoxEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BoxBlock extends BlockWithEntity {
    public BoxBlock(Settings settings) {
        super(settings);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoxEntity(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        //右键打开界面，这个判断要做，否则bug：if方块是这个box entity就打开，否则不打开
        if (world.isClient){
            return ActionResult.SUCCESS;
        }
        BlockEntity e = world.getBlockEntity(pos);
        if (e instanceof BoxEntity){
            player.openHandledScreen((BoxEntity) e);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }


    @Override
    //如果方块状态更新了会怎么样
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())){
            return;
        }
        BlockEntity e = world.getBlockEntity(pos);
        if (e instanceof BoxEntity){
            ItemScatterer.spawn(world, pos, (Inventory) ((Object) e ));
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }
}
