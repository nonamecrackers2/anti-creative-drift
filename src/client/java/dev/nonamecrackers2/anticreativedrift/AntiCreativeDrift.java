package dev.nonamecrackers2.anticreativedrift;

import dev.nonamecrackers2.anticreativedrift.command.AntiCreativeDriftCommands;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class AntiCreativeDrift implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		 CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			 AntiCreativeDriftCommands.registerCommands(dispatcher);
		 });
	}
}