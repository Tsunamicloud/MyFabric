package com.tsunamicloud.tsunami.recipes;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CopyItemRecipe extends SpecialCraftingRecipe {
    public CopyItemRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        //用纸和钻石来合成，检查物品是否匹配
        boolean hasPaper = false;
        boolean hasDiamond = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.getItem() == Items.PAPER){
                    hasPaper = true;
                }else if (stack.getItem() == Items.DIAMOND){
                    hasDiamond = true;
                }else {
                    return false;
                }
            }
        }
        return hasDiamond && hasPaper;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasPaper = false;
        boolean hasDiamond = false;
        int count  = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.getItem() != Items.PAPER && stack.getItem() != Items.DIAMOND){
                    return ItemStack.EMPTY;
                }
                if (stack.getItem() == Items.PAPER && hasPaper){
                    return ItemStack.EMPTY;
                }
                if (stack.getItem() == Items.PAPER){
                    hasPaper = true;
                }
                if (stack.getItem() == Items.DIAMOND){
                    hasDiamond = true;
                    count ++;
                }
            }
        }
        if (hasDiamond && hasPaper){
            ItemStack stack = new ItemStack(Main.COPY_ITEM);
            //开始写nbt
            NbtCompound nbt = new NbtCompound();//相当于json的{},相当于一个对象
            nbt.putInt("amount", count);//放入键值对amount，count
            NbtString string = NbtString.of(NbtString.escape("Number of copies: "+ count)); //显示可以复制的数量，此处相当于json文本中对象从内往外开始写
            NbtList list = new NbtList(); //相当于一个数组，相当于[]
            list.add(string);
            //再创建一个新的nbt
            NbtCompound nbt1 = new NbtCompound();
            nbt1.put("Lore", list);
            nbt.put("display", nbt1);
            stack.setNbt(nbt);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    //在Main中进行序列化
    public RecipeSerializer<?> getSerializer() {
        return Main.COPY_ITEM_RECIPE;
    }
}
