package com.tsunamicloud.tsunami.sound;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    
    public static SoundEvent DOWSING_ROD_FOUND_ORE = registerSoundEvent("dowsing_rod_found_ore");

    public static SoundEvent SAUALPITE_LAMP_BREAK = registerSoundEvent("saualpite_lamp_break");
    public static SoundEvent SAUALPITE_LAMP_STEP = registerSoundEvent("saualpite_lamp_step");
    public static SoundEvent SAUALPITE_LAMP_PLACE = registerSoundEvent("saualpite_lamp_place");
    public static SoundEvent SAUALPITE_LAMP_HIT = registerSoundEvent("saualpite_lamp_hit");
    public static SoundEvent SAUALPITE_LAMP_FALL = registerSoundEvent("saualpite_lamp_fall");

    public static SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");

    public static final BlockSoundGroup SAUALPITE_SOUNDS = new BlockSoundGroup(1f, 1f,
            ModSounds.SAUALPITE_LAMP_BREAK, ModSounds.SAUALPITE_LAMP_STEP, ModSounds.SAUALPITE_LAMP_PLACE,
            ModSounds.SAUALPITE_LAMP_HIT, ModSounds.SAUALPITE_LAMP_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Main.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
