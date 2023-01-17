package com.tsunamicloud.tsunami.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;


public class EightBallItem extends Item {
    public EightBallItem(Settings settings) {
        super(settings);
    }

    //重写use方法，添加功能
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //将功能写至server端，only execute on server
        //use会被两只手各调用一次
        if (!world.isClient() && hand == Hand.MAIN_HAND){
            //output a random number
            outputRandomNumber(user);
            //add a cooldown
            user.getItemCooldownManager().set(this, 20);//20ticks
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()){
            tooltip.add(new TranslatableText("item.tsunami.eight_ball_function.tooltip").formatted(Formatting.AQUA));
        }else{
            tooltip.add(new TranslatableText("item.tsunami.eight_ball.tooltip").formatted(Formatting.BLUE));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    //1.19的Text方法貌似和1.18不同，1.19有Text.literal
    //Text.of会将消息显示在bottom，literal显示的是聊天框
    private void outputRandomNumber(PlayerEntity player){
        player.sendMessage(Text.of("Your Number is " + getRandomNumber()), true);
    }

    //追求代码的clean
    //1.19相比1.18好像在net.minecraft.util.math.random中添加了minecraft的random方法，可以直接返回Random.nextInt
    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(10);//生成0-9的随机数
    }

}
