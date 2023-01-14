package io.github.gaming32.vialegacymulticonnect.protocols.b1_8.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.raphimc.vialegacy.api.LegacyProtocolVersions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayer {
    @Inject(method = "getHurtSound", at = @At("HEAD"), cancellable = true)
    private void OOF(DamageSource damageSource, CallbackInfoReturnable<SoundEvent> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersions.r1_0_0tor1_0_1)) {
            cir.setReturnValue(ViaLegacyMulticonnect.RANDOM_HURT);
        }
    }
}
