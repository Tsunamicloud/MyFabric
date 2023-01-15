package com.tsunamicloud.tsunami.recipes;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Objects;

public class BowlOfWaterRecipe extends SpecialCraftingRecipe {
    //水瓶的槽位
    private int waterBottleSlot = 0;
    public BowlOfWaterRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                if (stack.getItem() == Items.BOWL){
                    list.add(stack);
                }else if (Objects.equals(stack.getNbt(), Items.POTION.getDefaultStack().getNbt())){
                    list.add(stack);
                }
            }
        }
        return list.size() == 2;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasBowl = false;
        boolean hasWaterBottle = false;
        int count = 0;
        for (int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()){
                count++;
                if (stack.getItem() == Items.BOWL){
                    hasBowl = true;
                }else if (Objects.equals(stack.getNbt(), Items.POTION.getDefaultStack().getNbt())){
                    hasWaterBottle = true;
                    waterBottleSlot = i;
                }
            }
        }
        if (hasBowl && hasWaterBottle && count == 2){
            return Main.BOWL_OF_WATER.getDefaultStack();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        //用defaultedlist来模拟工作台的状态
        DefaultedList<ItemStack> list = DefaultedList.ofSize(9, ItemStack.EMPTY);
        list.set(waterBottleSlot, new ItemStack(Items.GLASS_BOTTLE, 1));
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Main.BOWL_OF_WATER_RECIPE;
    }
}
