package com.tsunamicloud.tsunami.mixin;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin {

    @Inject(at = @At("HEAD"),method = "registerDefaults")
    //注意这里重写时不能用public，只能由private
    private static void registerDefaults(CallbackInfo ci){
        //用粗制药水+板栗生成strong healing药水
        BrewingMixin.registerPotionRecipe(Potions.AWKWARD, Main.CHINESE_CHESTNUT,Potions.STRONG_HEALING);
        //用钻石酿造自定义药水：解毒药水
        BrewingMixin.registerPotionRecipe(Potions.AWKWARD, Items.DIAMOND, Main.ANTIDOTE);
    }
    @Invoker("registerPotionRecipe")
    public static void registerPotionRecipe(Potion input, Item item, Potion output){

    }
}
