package com.tsunamicloud.tsunami.block.entity;

import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.item.inventory.ImplementedInventory;
import com.tsunamicloud.tsunami.screen.SaualpiteBlasterScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SaualpiteBlasterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    //新建inventory来存储gui中的东西
    //4个slot：2个input，1个fuel，1个output
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);

    public SaualpiteBlasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SAUALPITE_BLASTER, pos, state);
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Override
    public Text getDisplayName() {
        return new LiteralText("Saualpite Blaster");
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SaualpiteBlasterScreenHandler(syncId, inv, this);
    }


    //读写nbt:进入和退出世界时，保存数据
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }



    //每个tick都会被调用
    public static void tick(World world, BlockPos pos, BlockState state, SaualpiteBlasterBlockEntity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    private static void craftItem(SaualpiteBlasterBlockEntity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);

        entity.setStack(3, new ItemStack(ModItems.SAUALPITE_PICKAXE,
                entity.getStack(3).getCount() + 1));
    }

    private static boolean hasRecipe(SaualpiteBlasterBlockEntity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.LILAC_FLOWER_BULB;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.GOLDEN_PICKAXE;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == ModItems.SAUALPITE;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
    }

    private static boolean hasNotReachedStackLimit(SaualpiteBlasterBlockEntity entity) {
        //slot从0开始计数
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }


}
