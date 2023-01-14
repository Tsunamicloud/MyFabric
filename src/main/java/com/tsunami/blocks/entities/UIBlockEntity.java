package com.tsunami.blocks.entities;

import com.tsunami.Main;
import com.tsunami.blocks.UIBlock;
import com.tsunami.screen.handler.UIBlockScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class UIBlockEntity extends LootableContainerBlockEntity {

    //先写一个虚拟的物品栏
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(2, ItemStack.EMPTY);
    public UIBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Main.UI_BLOCK_ENTITY, blockPos, blockState);
    }

    //gui动画：实现关于时间效果的函数
    private int tick = 0;
    //private int a = 0;
    //private int b = 0;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        //将需要实现的抽象方法实例化
        @Override
        public int get(int index) {
            return index == 0 ? tick : 0;
            /*return switch (index) {
                case 0 -> tick;
                case 1 -> a;
                case 2 -> b;
                default -> 0;
            };*/
        }

        @Override
        public void set(int index, int value) {
            if (index == 0){
                tick = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public static void tick(UIBlockEntity entity){
        //如果0号位不是空的且二号位（输出槽）没有满，则运行以下代码
        if (!entity.inv.get(0).isEmpty() && entity.inv.get(1).getCount() < entity.inv.get(1).getMaxCount()){
            entity.tick++;
            //7秒钟转化一颗钻石
            if (entity.tick == 20*7){
                entity.tick = 0;
                entity.inv.get(0).decrement(1);
                if (entity.inv.get(1).isEmpty()){
                    entity.inv.set(1, new ItemStack(Items.DIAMOND));
                }else {
                    entity.inv.get(1).increment(1);
                }
            }
        }
    }


    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("UI Block");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new UIBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int size() {
        return 2;
    }

    //做一个保存函数
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tick", tick);
        Inventories.writeNbt(nbt, inv);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tick = nbt.getInt("tick");
        Inventories.readNbt(nbt, inv);
    }

}
