package com.tsunamicloud.tsunami;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {

    //提供供主函数加载的函数
    public static void sound(){}

    //提供一个注册函数
    private static SoundEvent register(String name){
        return Registry.register(Registry.SOUND_EVENT, new Identifier("tsunami:"+name),  new SoundEvent(new Identifier("tsunami:" + name)));
    }

    //注册音效
    public static final SoundEvent MUSIC_RHYTHM_OF_THE_RAIN = Sounds.register("music_rhythm_of_the_rain");


}
