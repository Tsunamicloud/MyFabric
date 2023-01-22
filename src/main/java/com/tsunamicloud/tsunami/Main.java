package com.tsunamicloud.tsunami;
import com.tsunamicloud.tsunami.armor.CrimsonCrystalArmorItem;
import com.tsunamicloud.tsunami.armor.material.CrimsonCrystalArmorMaterial;
import com.tsunamicloud.tsunami.block.ModBlocks;
import com.tsunamicloud.tsunami.block.entity.ModBlockEntities;
import com.tsunamicloud.tsunami.blocks.*;
import com.tsunamicloud.tsunami.blocks.entities.BoxEntity;
import com.tsunamicloud.tsunami.blocks.entities.BreakEntity;
import com.tsunamicloud.tsunami.blocks.entities.ContainerEntity;
import com.tsunamicloud.tsunami.blocks.entities.UIBlockEntity;
import com.tsunamicloud.tsunami.effect.ModEffects;
import com.tsunamicloud.tsunami.effects.AntidoteEffect;
import com.tsunamicloud.tsunami.effects.GeneRecombinationEffect;
import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.items.*;
import com.tsunamicloud.tsunami.painting.ModPaintings;
import com.tsunamicloud.tsunami.particle.ModParticles;
import com.tsunamicloud.tsunami.potion.ModPotions;
import com.tsunamicloud.tsunami.recipe.ModRecipes;
import com.tsunamicloud.tsunami.recipes.BowlOfWaterRecipe;
import com.tsunamicloud.tsunami.recipes.CopyItemRecipe;
import com.tsunamicloud.tsunami.recipes.CopyRecipe;
import com.tsunamicloud.tsunami.recipes.SilkBagRecipe;
import com.tsunamicloud.tsunami.screen.ModScreenHandlers;
import com.tsunamicloud.tsunami.screen.handler.UIBlockScreenHandler;
import com.tsunamicloud.tsunami.tools.CrimsonCrystalAxe;
import com.tsunamicloud.tsunami.tools.CrimsonCrystalToolMaterial;
import com.tsunamicloud.tsunami.tools.weapon.CrimsonCrystalSword;
import com.tsunamicloud.tsunami.util.ModLootTableModifiers;
import com.tsunamicloud.tsunami.util.ModRegistries;
import com.tsunamicloud.tsunami.world.feature.ModConfiguredFeatures;
import com.tsunamicloud.tsunami.villager.ModVillagers;
import com.tsunamicloud.tsunami.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;

//import java.rmi.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


/**
 * nbt命令：提示unknown or incomplete command, see below for error，输数字的时候，聊天框上方会有[<slot>]
 * 唱片名字与en_us中定义的不一样，会显示开始播放xxx但没有声音
 */



public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tsunami";//mod id 会被用到很多次
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	//习惯写一个空参构造器
	public Main (){}
	//主类中加个main方法不然有些东西报错
	public static void main(String[] args) {
	}


	//新建自定义基础物品：猩红水晶
	//final后面变量名全部大写;引用数据类型，new里面再new
	//.group()将自定义物品放入创造模式物品中，MISC为杂项，maxCOUNT为最大堆叠数
	/*public static final Item CRIMSON_CRYSTAL = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(16).fireproof());*/
	//为物品添加自定义功能，创建时将Item类变为自己写的CrimsonCrystal类
	public static final CrimsonCrystal CRIMSON_CRYSTAL = new CrimsonCrystal(new Item.Settings().group(ItemGroup.MISC).maxCount(16).fireproof());
	//新建自定义武器
	//注意toolmaterial覆写的方法getAttackDamage如果不是-1，则此处填8实际伤害为9
	//攻速为填写值+4
	public static final CrimsonCrystalSword CRIMSON_CRYSTAL_SWORD = new CrimsonCrystalSword(new CrimsonCrystalToolMaterial(),8,-0.8f,new Item.Settings());
	//新建自定义工具
	public static final CrimsonCrystalAxe CRIMSON_CRYSTAL_AXE = new CrimsonCrystalAxe(new CrimsonCrystalToolMaterial(),10,-1.4f,new Item.Settings());
	//新建自定义燃料：乙烯酮
	public static final Item KETENE = new Item(new Item.Settings());
	//新建自定义食物：板栗，hunger值为4指回复4点体力值（2个鸡腿）;saturation为饱食度，回复4点饱食度（2个鸡腿）
	public static final Item CHINESE_CHESTNUT = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(4).snack().saturationModifier(4f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.GLOWING,20*10,3),1.0f).build()));
	//新建装备；ArmorMaterial是一个接口，这里相当于做一个多态
	public static final ArmorMaterial CRIMSON_CRYSTAL_MATERIAL = new CrimsonCrystalArmorMaterial();
	public static final CrimsonCrystalArmorItem CRIMSON_CRYSTAL_HELMET = new CrimsonCrystalArmorItem(CRIMSON_CRYSTAL_MATERIAL, EquipmentSlot.HEAD,new Item.Settings());
	public static final CrimsonCrystalArmorItem CRIMSON_CRYSTAL_CHEST = new CrimsonCrystalArmorItem(CRIMSON_CRYSTAL_MATERIAL, EquipmentSlot.CHEST,new Item.Settings());
	public static final CrimsonCrystalArmorItem CRIMSON_CRYSTAL_LEGGINGS = new CrimsonCrystalArmorItem(CRIMSON_CRYSTAL_MATERIAL, EquipmentSlot.LEGS,new Item.Settings());
	public static final CrimsonCrystalArmorItem CRIMSON_CRYSTAL_BOOTS = new CrimsonCrystalArmorItem(CRIMSON_CRYSTAL_MATERIAL, EquipmentSlot.FEET,new Item.Settings());
	//新建示例方块，想要限制挖掘工具，requiresTool方法是必须的
	public static final Block EXAMPLE_BLOCK = new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool());
	//新建自定义方块:注意区别——方块在世界中才是BLOCK，在玩家背包中还是ITEM，故注册时要注册两个
	/*public static final Block CRIMSON_CRYSTAL_BLOCK = new Block(AbstractBlock.Settings.of(Material.STONE));*/
	//添加挖掘等级
	public static final CrimsonCrystalBlock CRIMSON_CRYSTAL_BLOCK = new CrimsonCrystalBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().nonOpaque());
	//新建自定义方块：方块状态
	public static final ChangeBlock CHANGE_BLOCK = new ChangeBlock(AbstractBlock.Settings.of(Material.STONE));
	//新建自定义方块：数字方块
	public static final NumberBlock NUMBER_BLOCK = new NumberBlock(AbstractBlock.Settings.of(Material.STONE));
	//新建自定义方块：BreakBlock方块实体
	public static final BreakBlock BREAK_BLOCK = new BreakBlock(AbstractBlock.Settings.of(Material.STONE));
	public static BlockEntityType<BreakEntity> BREAK_ENTITY;
	//新建自定义容器方块：Container 功能：可以右键存入一个item，再右键则取出
	public static final Container CONTAINER = new Container(AbstractBlock.Settings.of(Material.STONE));
	public static BlockEntityType<ContainerEntity> CONTAINER_ENTITY;
	//新建自定义容器方块：BoxBlock 功能：实现类似原版箱子功能
	public static final BoxBlock BOX_BLOCK = new BoxBlock(AbstractBlock.Settings.of(Material.STONE));
	public static BlockEntityType<BoxEntity> BOX_ENTITY;
	//GUI
	public static final UIBlock UI_BLOCK = new UIBlock(AbstractBlock.Settings.of(Material.STONE));
	public static BlockEntityType<UIBlockEntity> UI_BLOCK_ENTITY;
	public static final ScreenHandlerType<UIBlockScreenHandler> UI_BLOCK_SCREEN_HANDLER;
	//新建物品：装满水的碗
	public static final Item BOWL_OF_WATER = new Item(new Item.Settings());
	public static final RecipeSerializer<BowlOfWaterRecipe> BOWL_OF_WATER_RECIPE = RecipeSerializer.register("crafting_bowl_of_water", new SpecialRecipeSerializer<>(BowlOfWaterRecipe::new));
	//新建药水效果：基因重组药水
	public static final GeneRecombinationEffect GENE_RECOMBINATION_EFFECT = new GeneRecombinationEffect();
	//新建自定义药水：解毒药水,duration : tick
	public static final AntidoteEffect ANTIDOTE_EFFECT = new AntidoteEffect();
	public static final Potion ANTIDOTE = new Potion(new StatusEffectInstance(Main.ANTIDOTE_EFFECT, 20));
	//NBT：制作一个可以复制东西的物品
	public static final CopyItem COPY_ITEM = new CopyItem(new Item.Settings());
	public static final RecipeSerializer<CopyItemRecipe> COPY_ITEM_RECIPE = RecipeSerializer.register("crafting_copy_item", new SpecialRecipeSerializer<>(CopyItemRecipe::new));
	public static final RecipeSerializer<CopyRecipe> COPY_RECIPE = RecipeSerializer.register("crafting_copy", new SpecialRecipeSerializer<>(CopyRecipe::new));
	//NBT：制作成书
	public static final FinishBookItem FINISH_BOOK_ITEM = new FinishBookItem(new Item.Settings());
	//NBT：锦囊
	public static final SilkBag SILK_BAG = new SilkBag(new Item.Settings());
	public static final RecipeSerializer<SilkBagRecipe> SILK_BAG_RECIPE = RecipeSerializer.register("crafting_silk_bag", new SpecialRecipeSerializer<>(SilkBagRecipe::new));

	//新建自定义唱片
	public static final MusicDisc RHYTHM_OF_THE_RAIN_MUSIC_DISC = new MusicDisc(2, Sounds.MUSIC_RHYTHM_OF_THE_RAIN, new Item.Settings().maxCount(1));


	//为GUI提供静态块
	static {
		UI_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("tsunami", "ui_block"), UIBlockScreenHandler::new);
	}


	//创建物品分类栏
	private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
					new Identifier("tsunami", "other"))
			.icon(() -> new ItemStack(Main.CRIMSON_CRYSTAL))
			.appendItems(stacks -> {
				//将物品加入分类栏
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_SWORD));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_AXE));
				stacks.add(new ItemStack(Main.KETENE));
				stacks.add(new ItemStack(Main.CHINESE_CHESTNUT));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_HELMET));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_CHEST));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_LEGGINGS));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_BOOTS));
				stacks.add(new ItemStack(Main.CRIMSON_CRYSTAL_BLOCK));
				stacks.add(new ItemStack(Main.CHANGE_BLOCK));
				stacks.add(new ItemStack(Main.EXAMPLE_BLOCK));
				stacks.add(new ItemStack(Main.NUMBER_BLOCK));
				stacks.add(new ItemStack(Main.BREAK_BLOCK));
				stacks.add(new ItemStack(Main.CONTAINER));
				stacks.add(new ItemStack(Main.BOX_BLOCK));
				stacks.add(new ItemStack(Main.UI_BLOCK));
				stacks.add(new ItemStack(Main.BOWL_OF_WATER));
				stacks.add(new ItemStack(Main.COPY_ITEM));
				stacks.add(new ItemStack(Main.FINISH_BOOK_ITEM));
				stacks.add(new ItemStack(Main.SILK_BAG));
				stacks.add(new ItemStack(Main.RHYTHM_OF_THE_RAIN_MUSIC_DISC));
			})
			.build();


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//LOGGER.info("Hello Fabric world!");

		//分组注册(封装)
		ModConfiguredFeatures.registerConfiguredFeatures();//需要在顶部调用
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModVillagers.registerModVillagers();
		ModVillagers.registerTrades();
		ModPaintings.registerModPaintings();
		ModRegistries.registerModStuffs();
		ModWorldGen.generateModWorldGen();
		ModLootTableModifiers.modifyLootTables();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModBlockEntities.registerAllBlockEntities();
		ModRecipes.registerRecipes();
		ModScreenHandlers.registerAllScreenHandlers();
		ModParticles.registerParticles();


		//注册新建的自定义基础物品：猩红水晶
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal"),CRIMSON_CRYSTAL);
		//注册新建的自定义武器：猩红水晶剑
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_sword"),CRIMSON_CRYSTAL_SWORD);
		//注册新建的自定义武器：猩红水晶斧
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_axe"),CRIMSON_CRYSTAL_AXE);
		//注册：乙烯酮，燃烧时间的参数用tick来描述（20tick = 1s）
		Registry.register(Registry.ITEM,new Identifier("tsunami","ketene"),KETENE);
		FuelRegistry.INSTANCE.add(Main.KETENE,100);
		//注册：板栗
		Registry.register(Registry.ITEM,new Identifier("tsunami","chinese_chestnut"),CHINESE_CHESTNUT);
		//注册：装备
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_helmet"),CRIMSON_CRYSTAL_HELMET);
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_chest"),CRIMSON_CRYSTAL_CHEST);
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_leggings"),CRIMSON_CRYSTAL_LEGGINGS);
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_boots"),CRIMSON_CRYSTAL_BOOTS);
		//注册：示例方块
		Registry.register(Registry.BLOCK,new Identifier("tsunami","example_block"),EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings()));
		//注册：自定义方块，注册时要注册方块形式和背包物品形式
		Registry.register(Registry.BLOCK,new Identifier("tsunami","crimson_crystal_block"),CRIMSON_CRYSTAL_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","crimson_crystal_block"), new BlockItem(CRIMSON_CRYSTAL_BLOCK, new Item.Settings()));
		//注册：ChangeBlock
		Registry.register(Registry.BLOCK,new Identifier("tsunami","change_block"),CHANGE_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","change_block"), new BlockItem(CHANGE_BLOCK, new Item.Settings()));
		//注册：NumberBlock
		Registry.register(Registry.BLOCK,new Identifier("tsunami","number_block"),NUMBER_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","number_block"), new BlockItem(NUMBER_BLOCK, new Item.Settings()));
		//注册：BreakBlock
		Registry.register(Registry.BLOCK,new Identifier("tsunami","break_block"),BREAK_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","break_block"), new BlockItem(BREAK_BLOCK, new Item.Settings()));
		BREAK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("tsunami","break_entity"), FabricBlockEntityTypeBuilder.create(BreakEntity::new, BREAK_BLOCK).build(null));
		//注册：Container 功能：可以右键存入一个item，再右键则取出
		Registry.register(Registry.BLOCK,new Identifier("tsunami","container"),CONTAINER);
		Registry.register(Registry.ITEM,new Identifier("tsunami","container"), new BlockItem(CONTAINER, new Item.Settings()));
		CONTAINER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("tsunami","container_entity"), FabricBlockEntityTypeBuilder.create(ContainerEntity::new, CONTAINER).build(null));
		//注册：BoxBlock
		Registry.register(Registry.BLOCK,new Identifier("tsunami","box_block"),BOX_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","box_block"), new BlockItem(BOX_BLOCK, new Item.Settings()));
		BOX_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("tsunami","box_entity"), FabricBlockEntityTypeBuilder.create(BoxEntity::new, BOX_BLOCK).build(null));
		//注册：UIBlock
		Registry.register(Registry.BLOCK,new Identifier("tsunami","ui_block"),UI_BLOCK);
		Registry.register(Registry.ITEM,new Identifier("tsunami","ui_block"), new BlockItem(UI_BLOCK, new Item.Settings()));
		UI_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("tsunami","ui_block_entity"), FabricBlockEntityTypeBuilder.create(UIBlockEntity::new, UI_BLOCK).build(null));
		//注册：装满水的碗
		Registry.register(Registry.ITEM,new Identifier("tsunami","bowl_of_water"),BOWL_OF_WATER);
		//注册：基因重组药水效果
		Registry.register(Registry.STATUS_EFFECT,new Identifier("tsunami","gene_recombination_effect"),GENE_RECOMBINATION_EFFECT);
		//注册：解毒药水
		Registry.register(Registry.STATUS_EFFECT,new Identifier("tsunami","antidote_effect"), ANTIDOTE_EFFECT);
		Registry.register(Registry.POTION,new Identifier("tsunami","antidote"),ANTIDOTE);
		//注册：NBT复制物品
		Registry.register(Registry.ITEM,new Identifier("tsunami","copy_item"),COPY_ITEM);
		//注册：NBT制作成书
		Registry.register(Registry.ITEM,new Identifier("tsunami","finish_book_item"),FINISH_BOOK_ITEM);
		//注册：锦囊
		Registry.register(Registry.ITEM,new Identifier("tsunami","silk_bag"),SILK_BAG);
		//加载自定义交易
		Trades.addTrade();
		//加载自定义粒子效果
		Particles.particle();
		//加载音效，注册唱片
		Sounds.sound();
		Registry.register(Registry.ITEM,new Identifier("tsunami","rhythm_of_the_rain_music_disc"),RHYTHM_OF_THE_RAIN_MUSIC_DISC);
	}
}


