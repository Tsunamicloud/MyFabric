package com.tsunami.items;

import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class SilkBag extends Item {
    public SilkBag(Settings settings) {
        super(settings);
    }

    @Override
    //制作：在工作台中放皮革（用皮革包裹其他的物品）
    //功能：右键取出锦囊物品，且锦囊消失
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();//获取锦囊
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            NbtList list = (NbtList) stack.getNbt().get("items");
            assert list != null;
            if (!list.isEmpty()){
                for (NbtElement nbtElement : list){
                    NbtCompound nbt = (NbtCompound) nbtElement;
                    String id = nbt.getString("id");
                    int count = nbt.getInt("count");
                    Item item = Registry.ITEM.getOrEmpty(new Identifier(id)).orElseThrow(() -> new JsonSyntaxException("No such item "+ id));
                    user.giveItemStack(new ItemStack(item, count));
                }
                user.getMainHandStack().decrement(1);
            }
        }
        return super.use(world, user, hand);
    }
}
