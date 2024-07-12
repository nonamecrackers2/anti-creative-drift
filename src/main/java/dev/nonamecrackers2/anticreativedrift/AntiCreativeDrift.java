package dev.nonamecrackers2.anticreativedrift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.nonamecrackers2.anticreativedrift.command.AntiCreativeDriftCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class AntiCreativeDrift implements ModInitializer
{
	public static final Logger LOGGER = LoggerFactory.getLogger("anticreativedrift");

	@Override
	public void onInitialize()
	{
		 CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			 AntiCreativeDriftCommands.registerCommands(dispatcher);
		 });
	}
}