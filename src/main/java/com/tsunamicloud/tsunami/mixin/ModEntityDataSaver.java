package com.tsunamicloud.tsunami.mixin;


import com.tsunamicloud.tsunami.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//mixin:改变原版source code中的Entity类
@Mixin(Entity.class)
public abstract class ModEntityDataSaver implements IEntityDataSaver {
    //这个field会被加入原版Entity类中
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    //玩家死亡时方法不会被调用，另外的方法会在死亡时被调用（event）
    //植入原版的writeNbt方法：if语句会被植入writeNbt方法的开头
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put("tsunami.tsunami_data", persistentData);
        }
    }
    //植入原版的writeNbt方法
    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("tsunami.tsunami_data", 10)) {
            persistentData = nbt.getCompound("tsunami.tsunami_data");
        }
    }
}
