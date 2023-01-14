package com.tsunami;

import com.mojang.brigadier.Command;
import com.tsunami.commands.NbtCommand;
import com.tsunami.screen.UIBlockScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Main.UI_BLOCK_SCREEN_HANDLER, UIBlockScreen::new);

        CommandRegistrationCallback.EVENT.register(
                (dispatcher, dedicated) -> NbtCommand.register(dispatcher)
        );

        ClientLifecycleEvents.CLIENT_STARTED.register(
                client -> {
                    ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
                        registry.register(new Identifier("tsunami", "particle/red_star"));
                    });
                    ParticleFactoryRegistry.getInstance().register(Particles.RED_STAR, FlameParticle.Factory::new);
                }
        );

    }
}
