package com.tsunami;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Recommended texture size is 16×16 pixels.
 * You might test it using the command /particle tsunami:green_flame ~ ~ ~.
 */

public class Particles {

    public static void particle(){}

    //提供一个注册函数
    private static DefaultParticleType register(String name, ParticleType<?> particleType){
        return (DefaultParticleType) Registry.register(Registry.PARTICLE_TYPE, new Identifier("tsunami", name), particleType);
    }

    //注册粒子效果
    public static final DefaultParticleType RED_STAR = Particles.register("red_star", FabricParticleTypes.simple());

}
