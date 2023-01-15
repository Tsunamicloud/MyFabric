package com.tsunamicloud.tsunami.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class AntidoteEffect extends InstantStatusEffect {
    public AntidoteEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xF8C825);//i:药水粒子效果颜色
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    //药水要实现的效果
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player){
            //移除中毒药水效果
            player.removeStatusEffect(StatusEffects.POISON);
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
