package com.tsunami.recipes;

import com.tsunami.Main;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SilkBagRecipe extends SpecialCraftingRecipe {
    public SilkBagRecipe(Identifier id) {
        super(id);
    }
    //实现功能：可存入东西至锦囊中，右键则将东西取出来
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        boolean hasLeather = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                count++;
                if (stack.getItem() == Items.LEATHER){
                    hasLeather = true;
                }
                if (stack.hasNbt()){
                    //本例忽略有nbt的物品
                    return false;
                }
            }
        }
        return count>=2 && hasLeather;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        boolean hasLeather = false;
        ArrayList<Integer> slots = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                count++;
                if (stack.hasNbt()){
                    //本例忽略有nbt的物品
                    return ItemStack.EMPTY;
                }
                if (stack.getItem() == Items.LEATHER && hasLeather){
                    slots.add(i);
                    continue;
                }
                if (stack.getItem() == Items.LEATHER){
                    hasLeather = true;
                }
                slots.add(i);
            }
        }
        if (count>=2 && hasLeather){
            ItemStack stack = new ItemStack(Main.SILK_BAG);
            NbtCompound baseNbt = new NbtCompound();
            NbtList list = new NbtList();
            for (int slot: slots){
                int count1 = inventory.getStack(slot).getCount();
                String id = Registry.ITEM.getId(inventory.getStack(slot).getItem()).toString();
                NbtCompound nbt = new NbtCompound();
                nbt.putString("id", id);
                nbt.putInt("count", count1);
                list.add(nbt);
            }
            baseNbt.put("items", list);
            stack.setNbt(baseNbt);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Main.SILK_BAG_RECIPE;
    }

    @Override
    //清空工作台的每一格
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            inventory.setStack(i, ItemStack.EMPTY);
        }
        return super.getRemainder(inventory);
    }
}
