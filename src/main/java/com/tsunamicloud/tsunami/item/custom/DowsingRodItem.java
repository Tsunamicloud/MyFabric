package com.tsunamicloud.tsunami.item.custom;

import com.tsunamicloud.tsunami.item.ModItems;
import com.tsunamicloud.tsunami.sound.ModSounds;
import com.tsunamicloud.tsunami.util.InventoryUtil;
import com.tsunamicloud.tsunami.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {
    //探矿棒：搜索右击方块的下方
    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //minecraft的两个线程：server和client
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
            //1.18主世界最低深度为-64（+64即为--64）
            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();
                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.down(i), player, blockBelow);
                    foundBlock = true;

                    //找到后相Tablet物品添加nbt信息
                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)){
                        addNbtToDataTablet(player, positionClicked.add(0, -i, 0), blockBelow);
                    }
                    //找到后播放音效
                    context.getWorld().playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE,
                            SoundCategory.BLOCKS, 1f, 1f);

                    break;
                }
            }

            if(!foundBlock) {
                //文本在lang中配置
                player.sendMessage(new TranslatableText("item.tsunami.dowsing_rod.no_valuables"), false);
            }
        }
        //使用后销毁item
        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()){
            //传入的Text变量是数组，需要多行时只需依次添加即可
            tooltip.add(new TranslatableText("item.tsunami.dowsing_rod.tooltip").formatted(Formatting.BLUE));
        }else{
            tooltip.add(new TranslatableText("item.tsunami.dowsing_rod.tooltip.shift").formatted(Formatting.AQUA));
            tooltip.add(new TranslatableText("item.tsunami.dowsing_rod.tooltip.shift_2").formatted(Formatting.AQUA));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }



    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(new LiteralText("Found " + blockBelow.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(Block block) {
        /*return block == Blocks.COAL_ORE || block == Blocks.COPPER_ORE
                || block == Blocks.DIAMOND_ORE || block == Blocks.IRON_ORE;*/
        //return ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS.contains(block);//1.18.1
        return Registry.BLOCK.getOrCreateEntry(Registry.BLOCK.getKey(block).get()).isIn(ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS);
    }



    private void addNbtToDataTablet(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET));
        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("tsunami.last_ore", "Found " + blockBelow.asItem().getName().getString() + " at (" +
                pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");
        dataTablet.setNbt(nbtData);
    }


}
