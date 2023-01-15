package com.tsunamicloud.tsunami;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class Trades {


    //提供一个公共函数,用于Main函数
   public static void addTrade(){
       sellSilkBag();
   }

    //出售物品：锦囊
    private static void sellSilkBag(){
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            ItemStack stack = new ItemStack(Main.SILK_BAG);
            NbtCompound baseNbt = new NbtCompound();
            NbtList list = new NbtList();
            NbtCompound items = new NbtCompound();
            items.putString("id", "minecraft:diamond");
            items.putInt("count", 9);
            list.add(items);
            baseNbt.put("items", list);
            stack.setNbt(baseNbt);
            //核心代码
            factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 5), stack, 1, 3, 0.5f));
        });
    }

}
