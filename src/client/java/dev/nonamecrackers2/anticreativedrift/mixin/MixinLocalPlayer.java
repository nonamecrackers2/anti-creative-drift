package dev.nonamecrackers2.anticreativedrift.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.nonamecrackers2.anticreativedrift.config.AntiCreativeDriftConfig;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;

@Mixin(LocalPlayer.class)
public class MixinLocalPlayer extends AbstractClientPlayer
{
	@Shadow
	public Input input;
	@Unique
	private boolean isApplyingMovement;
	@Unique
	private int diminishTicks;
	
	private MixinLocalPlayer()
	{
		super(null, null);
		throw new UnsupportedOperationException();
	}
	
	@Inject(method = "serverAiStep", at = @At("HEAD"))
	public void anticreativedrift$stopDrift_serverAiStep(CallbackInfo ci)
	{
		if (this.isApplyingMovement && this.input.getMoveVector().length() == 0.0F)
		{
			if (this.isCreative() && !this.isOnGround())
			{
				float factor = Mth.clamp((float)this.diminishTicks / (float)AntiCreativeDriftConfig.getConfig().getDriftDiminishTicks(), 0.0F, 1.0F);
				this.setDeltaMovement(this.getDeltaMovement().multiply((double)factor, 1.0D, (double)factor));
			}
			if (this.diminishTicks > 0)
			{
				this.diminishTicks--;
				if (this.diminishTicks == 0)
				{
					this.setDeltaMovement(this.getDeltaMovement().multiply(0.0D, 1.0D, 0.0D));
					this.isApplyingMovement = false;
				}
			}
		}
		if (this.input.getMoveVector().length() > 0.0F)
		{
			this.diminishTicks = AntiCreativeDriftConfig.getConfig().getDriftDiminishTicks();
			this.isApplyingMovement = true;
		}
	}
}
