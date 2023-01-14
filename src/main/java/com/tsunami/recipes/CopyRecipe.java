package com.tsunami.recipes;

import com.tsunami.Main;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CopyRecipe extends SpecialCraftingRecipe {
    public CopyRecipe(Identifier id) {
        super(id);
    }

    private int copyItemSlot;
    private int itemSlot;

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.getItem() == Main.COPY_ITEM){
                    if (stack.hasNbt()){
                        if (stack.getNbt().get("amount") != null){
                            count ++;
                            copyItemCount++;
                        }
                    }
                }else  {
                    count++ ;
                }
            }
        }
        return count == 2 && copyItemCount == 1;//用复制卷轴复制复制卷轴时，copyItemCount不是1，不成立
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                //防止故意刷物品:不能复制钻石，防止一直合成复制卷轴
                if (stack.getItem() == Items.DIAMOND || stack.getItem() == Items.DIAMOND_BLOCK){
                    return ItemStack.EMPTY;
                }
                if (stack.getItem() == Main.COPY_ITEM){
                    if (stack.hasNbt()){
                        assert stack.getNbt() != null;
                        if (stack.getNbt().get("amount") != null){
                            count ++;
                            copyItemCount++;
                            copyItemSlot = i;
                        }
                    }
                }else  {
                    count++ ;
                    itemSlot = i;
                }
            }
        }
        if (count == 2 && copyItemCount == 1){
            //获得复制卷轴的位置
            ItemStack stack = inventory.getStack(copyItemSlot);
            assert stack.getNbt() != null;//上面已经做过判断了
            int amount = stack.getNbt().getInt("amount");//键值对
            ItemStack stack1 = inventory.getStack(itemSlot);
            Item item = inventory.getStack(itemSlot).getItem();
            //如果物品有nbt，则原样复制
            if (stack1.hasNbt()){
                NbtCompound nbt = stack1.getNbt();
                ItemStack stack2 = new ItemStack(item, amount +1);//合成时会消耗掉一个原物品，复制完成时补一个
                stack2.setNbt(nbt);
                return stack2;
            }
            return new ItemStack(item, amount +1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Main.COPY_RECIPE;
    }
}
