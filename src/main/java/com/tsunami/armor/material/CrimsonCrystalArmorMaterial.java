package com.tsunami.armor.material;

import com.tsunami.Main;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CrimsonCrystalArmorMaterial implements ArmorMaterial {

    //创建一个数组，只在这个类中可以被访问，同时不提供sub get方法
    //静态初始化数组，对应头盔护腿胸甲靴子
    private static final int[] PROTECTION = {4,7,8,4};

    //重写方法
    @Override
    public int getDurability(EquipmentSlot slot) {
        return 10000;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Main.CRIMSON_CRYSTAL);
    }

    @Override
    public String getName() {
        return "crimsoncrystal";
    }

    @Override
    //盔甲韧性
    public float getToughness() {
        return 1.2f;
    }

    @Override
    public float getKnockbackResistance() {
        return 1f;
    }
}
