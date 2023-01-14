package com.tsunami.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrimsonCrystal extends Item {
    //构建匹配的super函数
    //ctrl+lclick进入Item，可查看一些我们可用的方法
    public CrimsonCrystal(Settings settings) {
        super(settings);
    }
    @Override
    //重写use方法：给物品自定义使用效果
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        /*
        if (this.isFood()) {
            ItemStack itemStack = user.getStackInHand(hand);
            if (user.canConsume(this.getFoodComponent().isAlwaysEdible())) {
                user.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            }
            return TypedActionResult.fail(itemStack);
        }
        */
        //主手使用：发出铁砧放置声音，设置玩家hp为5，创建爆炸
        if (hand == Hand.MAIN_HAND){
            user.playSound(BlockSoundGroup.ANVIL.getPlaceSound(),3 ,4);
            user.setHealth(5.0f);
            world.createExplosion(user,user.getX(),user.getY(),user.getZ(),10,true, Explosion.DestructionType.DESTROY);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    //重写appendTooltip方法，给物品自定义提示
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        //super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.tsunami.crimson_crystal.tooltip"));
    }
}
