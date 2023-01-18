package com.tsunamicloud.tsunami.util;

import com.tsunamicloud.tsunami.Main;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {
        public static final Tag.Identified<Block> DOWSING_ROD_DETECTABLE_BLOCKS =
                createTag("dowsing_rod_detectable_blocks");

        //生成block的tag
        private static Tag.Identified<Block> createTag(String name) {
            return TagFactory.BLOCK.create(new Identifier(Main.MOD_ID, name));
        }
        //生成可被不同mod共用的block的tag
        private static Tag.Identified<Block> createCommonTag(String name) {
            return TagFactory.BLOCK.create(new Identifier("c", name));
        }
    }

    public static class Items {
        public static final Tag.Identified<Item> SAUALPITE = createCommonTag("saualpite");
        public static final Tag.Identified<Item> RAW_SAUALPITE = createCommonTag("raw_saualpite");

        //生成item的tag
        private static Tag.Identified<Item> createTag(String name) {
            return TagFactory.ITEM.create(new Identifier(Main.MOD_ID, name));
        }
        //生成可被不同mod共用的item的tag
        private static Tag.Identified<Item> createCommonTag(String name) {
            return TagFactory.ITEM.create(new Identifier("c", name));
        }
    }
}
