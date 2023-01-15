package com.tsunamicloud.tsunami.blocks.entities;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ContainerEntity extends BlockEntity {

    //初始化，inv指inventory
    private DefaultedList<ItemStack> inv = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public ContainerEntity(BlockPos pos, BlockState state) {
        super(Main.CONTAINER_ENTITY, pos, state);
    }

    public void use(PlayerEntity player){
        //0代表容器第一格，1代表第二格
        if (inv.get(0).isEmpty()){
            inv.set(0, player.getMainHandStack().split(1));
        }else {
            ItemScatterer.spawn(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), inv.get(0));
            inv.set(0, ItemStack.EMPTY);
        }
        markDirty();
    }
    //保存函数，保存inv
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inv);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inv);
    }
}
