package com.tsunamicloud.tsunami.recipe;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Main.MOD_ID, SaualpiteBlasterRecipe.Serializer.ID),
                SaualpiteBlasterRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Main.MOD_ID, SaualpiteBlasterRecipe.Type.ID),
                SaualpiteBlasterRecipe.Type.INSTANCE);
    }
}

