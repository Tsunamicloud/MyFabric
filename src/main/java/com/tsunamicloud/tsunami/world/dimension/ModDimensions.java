package com.tsunamicloud.tsunami.world.dimension;

import com.tsunamicloud.tsunami.Main;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    public static final RegistryKey<World> TSUDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(Main.MOD_ID, "tsudim"));
    public static final RegistryKey<DimensionType> TSUDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            TSUDIM_DIMENSION_KEY.getValue());


    public static void register() {
        Main.LOGGER.debug("Registering ModDimensions for " + Main.MOD_ID);
    }
}