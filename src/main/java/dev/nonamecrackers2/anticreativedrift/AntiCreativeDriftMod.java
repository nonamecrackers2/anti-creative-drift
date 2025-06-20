package dev.nonamecrackers2.anticreativedrift;

import dev.nonamecrackers2.anticreativedrift.config.AntiCreativeDriftConfig;
import dev.nonamecrackers2.anticreativedrift.event.AntiCreativeDriftEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AntiCreativeDriftMod.MODID)
public class AntiCreativeDriftMod
{
	public static final String MODID = "anticreativedrift";
	
	public AntiCreativeDriftMod(FMLJavaModLoadingContext context)
	{
		context.registerConfig(ModConfig.Type.CLIENT, AntiCreativeDriftConfig.SPEC);
		
		IEventBus modBus = context.getModEventBus();
		modBus.addListener(AntiCreativeDriftEvents::registerConfigMenu);
		modBus.addListener(AntiCreativeDriftEvents::registerConfigMenuButton);
		MinecraftForge.EVENT_BUS.register(AntiCreativeDriftEvents.class);
	}
}
