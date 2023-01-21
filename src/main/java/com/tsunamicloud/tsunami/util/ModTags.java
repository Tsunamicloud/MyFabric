package com.tsunamicloud.tsunami.util;

import com.tsunamicloud.tsunami.Main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_DETECTABLE_BLOCKS =
                createTag("dowsing_rod_detectable_blocks");

        //生成block的tag
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(Main.MOD_ID, name));
        }
        //生成可被不同mod共用的block的tag
        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SAUALPITE = createCommonTag("saualpite");
        public static final TagKey<Item> RAW_SAUALPITE = createCommonTag("raw_saualpite");

        //生成item的tag
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(Main.MOD_ID, name));
        }
        //生成可被不同mod共用的item的tag
        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
