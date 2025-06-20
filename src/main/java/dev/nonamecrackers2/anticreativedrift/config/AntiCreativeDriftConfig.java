package dev.nonamecrackers2.anticreativedrift.config;

import dev.nonamecrackers2.anticreativedrift.AntiCreativeDriftMod;
import net.minecraftforge.common.ForgeConfigSpec;
import nonamecrackers2.crackerslib.common.config.ConfigHelper;

public class AntiCreativeDriftConfig extends ConfigHelper
{
	public static final AntiCreativeDriftConfig INSTANCE;
	public static final ForgeConfigSpec SPEC;
	
	public final ForgeConfigSpec.ConfigValue<Integer> driftDiminishTicks;;
	
	static
	{
		var pair = new ForgeConfigSpec.Builder().configure(AntiCreativeDriftConfig::new);
		INSTANCE = pair.getLeft();
		SPEC = pair.getRight();
	}
	
	private AntiCreativeDriftConfig(ForgeConfigSpec.Builder builder)
	{
		super(builder, AntiCreativeDriftMod.MODID);
		
		this.driftDiminishTicks = this.createRangedIntValue(5, 0, 1200, "driftDiminishTicks", false, "Specifies the amount of ticks it takes for the drift to fully diminish");
	}
}
