package com.tsunami.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class GeneRecombinationEffect extends StatusEffect {
    public GeneRecombinationEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xF17ED2);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    //药水要实现的效果
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //喝药水时生命值减半
        if (entity instanceof PlayerEntity player){
            float health = player.getHealth() / 2;
            player.setHealth(health);
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity player){
            //药水效果结束后获得增益buff
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20*30,1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*30,2));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*30,2));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20*30,3));
        }
        super.onRemoved(entity, attributes, amplifier);
    }
}
