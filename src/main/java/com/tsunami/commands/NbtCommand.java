package com.tsunami.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
//引入一个静态类
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
//实现功能：获取手持物品的NBT,需要在Client端中注册
/*
public class NbtCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("nbt").requires(c -> c.hasPermissionLevel(4)).executes(c -> getNbt(c.getSource().getPlayer())));
    }
    private static int getNbt(PlayerEntity player){
        ItemStack stack = player.getMainHandStack();
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            String s = stack.getNbt().toString();
            player.sendMessage(new LiteralText(s),false);
            return Command.SINGLE_SUCCESS;
        }else{
            player.sendMessage(new LiteralText("This item has no NBT"), false);
        }
        return  0;
    }
 */

//实现：获取指定槽位的nbt
public class NbtCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("nbt").requires(c -> c.hasPermissionLevel(4))
                .then(argument("slot", IntegerArgumentType.integer())).requires(c -> c.hasPermissionLevel(4)).executes(c -> getNbt(IntegerArgumentType.getInteger(c, "slot"), c.getSource().getPlayer())));
    }

    private static int getNbt(int slot, PlayerEntity player){
        //保险措施：玩家inventory的slot只有40
        if (slot >= 0 && slot <= 40){
            ItemStack stack = player.getInventory().getStack(slot);
            if (stack.hasNbt()) {
                assert stack.getNbt() != null;
                String s = stack.getNbt().toString();
                player.sendMessage(new LiteralText(s), false);
                return Command.SINGLE_SUCCESS;
            } else {
                player.sendMessage(new LiteralText("This item has no NBT"), false);
            }
            return 0;
        }else if (slot<0){
            player.sendMessage(new LiteralText("The value you entered is too small"),  false);
        }else{
            player.sendMessage(new LiteralText("The value you entered is too large"),  false);
        }
        return 0;
    }

}







