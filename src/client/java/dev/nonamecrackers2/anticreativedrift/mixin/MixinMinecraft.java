package dev.nonamecrackers2.anticreativedrift.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.nonamecrackers2.anticreativedrift.config.AntiCreativeDriftConfig;
import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class MixinMinecraft
{
	@Inject(method = "<init>", at = @At("TAIL"))
	public void anticreativedrift$onInit_init(CallbackInfo ci)
	{
		AntiCreativeDriftConfig.readFile();
	}
}
