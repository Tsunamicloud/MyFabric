package com.tsunamicloud.tsunami.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CopyItem extends Item {
    public CopyItem(Settings settings) {
        super(settings);
    }

    @Override
    //带有指定的NBT，则发出光泽
    public boolean hasGlint(ItemStack stack) {
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            //amount：复制的倍数
            return stack.getNbt().get("amount") != null; //nbt类似键值对：”amount“：1
        }
        return false;
    }
}
