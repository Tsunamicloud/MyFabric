package com.tsunamicloud.tsunami.villager;

import com.google.common.collect.ImmutableSet;
import com.tsunamicloud.tsunami.Main;
import com.tsunamicloud.tsunami.block.ModBlocks;
import com.tsunamicloud.tsunami.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final PointOfInterestType JUMP_POI = registerPOI("jumpy_poi", ModBlocks.JUMPY_BLOCK);
    public static final VillagerProfession JUMP_MASTER = registerProfession("jump_master", JUMP_POI);

    public static final PointOfInterestType BLASTER_POI = registerPOI("blaster_poi", ModBlocks.SAUALPITE_BLASTER);
    public static final VillagerProfession BLAST_MASTER = registerProfession("blast_master", BLASTER_POI);



    //注册：村民的职业
    public static VillagerProfession registerProfession(String name, PointOfInterestType type){
        return Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Main.MOD_ID, name),
                VillagerProfessionBuilder.create().id(new Identifier(Main.MOD_ID, name)).workstation(type)
                        .workSound(SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER).build());
    }
    //注册：村民找工作
    public static PointOfInterestType registerPOI(String name, Block block){
        return PointOfInterestHelper.register(new Identifier(Main.MOD_ID, name),
                1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
    }
    //注册模组的村民
    public static void registerModVillagers(){
        Main.LOGGER.debug("Registering Villagers for" + Main.MOD_ID);
    }



    //注册村民拥有的交易
    public static void registerTrades(){
        TradeOfferHelper.registerVillagerOffers(JUMP_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.MINT, 5),
                            6, 2, 0.02f
                    ));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER,1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(ModItems.SAUALPITE_PICKAXE, 1),
                            12,7,0.08f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 12),
                            new ItemStack(ModItems.SAUALPITE_BOOTS, 1),
                            12,7,0.08f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 26),
                            new ItemStack(ModItems.SAUALPITE_CHESTPLATE, 1),
                            12,7,0.08f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER,2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 64),
                            new ItemStack(ModItems.SAUALPITE_HOE, 1),
                            12,7,0.08f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(ModItems.SAUALPITE, 1),
                            12,7,0.08f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 50),
                            new ItemStack(ModItems.TSUNAMI_BOW, 1),
                            12,7,0.08f));
                });
    }
}
