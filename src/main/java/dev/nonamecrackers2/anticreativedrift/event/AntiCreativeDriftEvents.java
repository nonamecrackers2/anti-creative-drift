package dev.nonamecrackers2.anticreativedrift.event;

import dev.nonamecrackers2.anticreativedrift.AntiCreativeDriftMod;
import dev.nonamecrackers2.anticreativedrift.config.AntiCreativeDriftConfig;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import nonamecrackers2.crackerslib.client.event.impl.ConfigMenuButtonEvent;
import nonamecrackers2.crackerslib.client.event.impl.RegisterConfigScreensEvent;
import nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen;
import nonamecrackers2.crackerslib.client.gui.title.ImageTitle;
import nonamecrackers2.crackerslib.client.gui.title.TextTitle;
import nonamecrackers2.crackerslib.common.command.ConfigCommandBuilder;

public class AntiCreativeDriftEvents
{
	@SubscribeEvent
	public static void registerCommands(RegisterClientCommandsEvent event)
	{
		ConfigCommandBuilder.builder(event.getDispatcher(), AntiCreativeDriftMod.MODID).addSpec(ModConfig.Type.CLIENT, AntiCreativeDriftConfig.SPEC).register();
	}
	
	public static void registerConfigMenu(RegisterConfigScreensEvent event)
	{
		event.builder(ConfigHomeScreen.builder(TextTitle.ofModDisplayName(AntiCreativeDriftMod.MODID)).crackersDefault("https://github.com/nonamecrackers2/anti-creative-drift").build())
				.addSpec(ModConfig.Type.CLIENT, AntiCreativeDriftConfig.SPEC).register();
	}
	
	public static void registerConfigMenuButton(ConfigMenuButtonEvent event)
	{
		event.defaultButtonWithSingleCharacter('A', 0xffffc7c7);
	}
}
