package com.tsunamicloud.tsunami.potion;

import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.effect.ModEffects;
import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {
    public static Potion FREEZE_POTION;

    public static Potion registerPotion(String name) {
        return Registry.register(Registry.POTION, new Identifier(Main.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.FREEZE, 200, 0)));
    }

    public static void registerPotions() {
        FREEZE_POTION = registerPotion("freeze_potion");

        registerPotionRecipes();
    }


    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.SAUALPITE,
                ModPotions.FREEZE_POTION);
    }


}
