package com.tsunamicloud.tsunami.tools;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

//实现接口，需要重写里面所有的抽象方法
public class CrimsonCrystalToolMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 10000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 100;
    }

    @Override
    public float getAttackDamage() {
        return -1;
    }

    @Override
    //range:1~4
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Main.CRIMSON_CRYSTAL);
    }
}
