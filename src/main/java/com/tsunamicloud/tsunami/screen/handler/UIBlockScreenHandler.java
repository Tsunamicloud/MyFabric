package com.tsunamicloud.tsunami.screen.handler;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class UIBlockScreenHandler extends ScreenHandler {
    /*protected UIBlockScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }*/

    public  Inventory inventory;
    //提供构造方法
    /*public UIBlockScreenHandler(int synId, PlayerInventory inventory){
        this(synId, inventory, new SimpleInventory(2));
    }*/
    private final PropertyDelegate propertyDelegate;//作用：调用UIBlockEntity中的tick来绘图
    public UIBlockScreenHandler(int synId, PlayerInventory inventory){
        this(synId, inventory, new SimpleInventory(2),new ArrayPropertyDelegate(1));
    }


    public UIBlockScreenHandler(int synId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate  propertyDelegate){
        super(Main.UI_BLOCK_SCREEN_HANDLER, synId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkSize(inventory, 2);
        checkDataCount(propertyDelegate, 1);
        //给UI添加物品栏
        //第一个物品栏：都可以放（return true）
        //0号槽边界距离：56，17
        //只能放煤炭
        this.addSlot(new Slot(this.inventory, 0, 56, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.COAL;
            }
        });
        //第二个物品栏：只能放书return stack.getItem() == Items.BOOK;
        //1号槽边界距离：128，17
        //玩家不能放东西return false
        this.addSlot(new Slot(this.inventory, 1, 128, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        //绘制玩家物品栏
        int i;
        for (i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlot(new Slot(playerInventory, j+i*9+9, 8+j*18, 84+i*18));
            }
        }
        for (i = 0; i < 9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8+i*18, 142));
        }
    }

    public int getTick(){
        return propertyDelegate.get(0);
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasStack()){
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            ItemStack itemStack3 = this.inventory.getStack(0);
            ItemStack itemStack4 = this.inventory.getStack(1);
            if (index == 2){
                if (!this.insertItem(itemStack2, 3, 39, true)){
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(itemStack2, itemStack);
                //下面的判断条件可能有误？
            }else if (index == 0 || index == 1 ? !this.insertItem(itemStack2,3 ,39,false):(itemStack3.isEmpty() || itemStack4.isEmpty() ? !this.insertItem(itemStack3,3 ,39,false) : !this.insertItem(itemStack4,3 ,39,false))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            }else {
                slot.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }
}
