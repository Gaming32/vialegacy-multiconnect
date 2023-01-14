package io.github.gaming32.vialegacymulticonnect.mixin.multiconnect;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.earthcomputer.multiconnect.protocols.generic.MulticonnectAddedRegistryEntries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MulticonnectAddedRegistryEntries.class, remap = false)
public class MixinMulticonnectAddedRegistryEntries {
    @Inject(method = "register", at = @At("TAIL"))
    private static void myRegister(CallbackInfo ci) {
        Registry.register(
            BuiltInRegistries.SOUND_EVENT,
            ViaLegacyMulticonnect.RANDOM_HURT_ID,
            ViaLegacyMulticonnect.RANDOM_HURT
        );
    }
}
