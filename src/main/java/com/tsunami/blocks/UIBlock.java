package com.tsunami.blocks;

import com.tsunami.Main;
import com.tsunami.blocks.entities.BoxEntity;
import com.tsunami.blocks.entities.ContainerEntity;
import com.tsunami.blocks.entities.UIBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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

public class UIBlock extends BlockWithEntity {
    public UIBlock(Settings settings) {
        super(settings);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UIBlockEntity(pos, state) {
        };
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //右键打开界面，这个判断要做，否则bug：if方块是这个box entity就打开，否则不打开
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        BlockEntity e = world.getBlockEntity(pos);
        if (e instanceof UIBlockEntity) {
            player.openHandledScreen((UIBlockEntity) e);
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
        if (e instanceof UIBlockEntity){
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

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, Main.UI_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> UIBlockEntity.tick(blockEntity));
    }
}



