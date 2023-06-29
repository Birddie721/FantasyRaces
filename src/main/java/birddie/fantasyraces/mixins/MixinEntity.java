package birddie.fantasyraces.mixins;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import birddie.fantasyraces.race.CapabilityRace;

@Mixin(PlayerEntity.class)
public abstract class MixinEntity {
	
	@Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
	private void getDimensions(Pose pose, CallbackInfoReturnable<EntitySize> callback)
	{
		PlayerEntity player = (PlayerEntity)(Object) this;
		player.getCapability(CapabilityRace.RACE).ifPresent(p ->{
			float height = 1.0f;
			float width = 1.0f;
			switch(p.getRace()) {
			case 1: height = 0.77f;
					width = 1.0f;
					break;
			case 2: height = 1.05f;
					width = 1.0f;
					break;
			case 3: height = 0.5f;
					width = 0.5f;
					break;
			default: height = 1.0f;
					width = 1.0f;
			}
			callback.setReturnValue(callback.getReturnValue().scale(width, height));
		});
	}
	
	
	@Inject(at = @At("RETURN"), method = "getSpeed", cancellable = true)
	private void getSpeed(CallbackInfoReturnable<Float> callback)
	{
		PlayerEntity player = (PlayerEntity)(Object) this;
		player.getCapability(CapabilityRace.RACE).ifPresent(p ->{
			float scale = 1.0f;
			switch(p.getRace()) {
			case 1: scale = 0.8f;
					break;
			case 2: scale = 1.2f;
					break;
			case 3: scale = 0.8f;
					break;
			default: scale = 1.0f;
			}
			callback.setReturnValue(callback.getReturnValue() * scale);
		});
	}
	
	@Inject(at = @At("RETURN"), method = "getStandingEyeHeight", cancellable = true)
	private void getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_, CallbackInfoReturnable<Float> callback) {
		PlayerEntity player = (PlayerEntity)(Object) this;
		player.getCapability(CapabilityRace.RACE).ifPresent(p ->{
			float scale = 1.0f;
			switch(p.getRace()) {
			case 1: scale = 0.77f;
					break;
			case 2: scale = 1.05f;
					break;
			case 3: scale = 0.5f;
					break;
			default: scale = 1.0f;
			}
			callback.setReturnValue(callback.getReturnValue() * scale);
		});
	   }
	
	@Inject(at = @At("RETURN"), method = "getLuck", cancellable = true)
	private void getLuck(CallbackInfoReturnable<Float> callback)
	{
		PlayerEntity player = (PlayerEntity)(Object) this;
		player.getCapability(CapabilityRace.RACE).ifPresent(p ->{
			Float scale = 1.0f;
			if (p.getRace() == 3){
				scale = 2.0f;
			}
			callback.setReturnValue(callback.getReturnValue() * scale);
		});
	}
	
}
