package com.tsunamicloud.tsunami.item;

import com.tsunamicloud.tsunami.Main;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup SAUALPITE = FabricItemGroupBuilder.build(
            new Identifier(Main.MOD_ID, "saualpite"), () -> new ItemStack(ModItems.SAUALPITE));
    public static final ItemGroup SAUALPITE2 = FabricItemGroupBuilder.build(
            new Identifier(Main.MOD_ID, "saualpite2"), () -> new ItemStack(ModItems.RAW_SAUALPITE));
}
