package io.github.gaming32.vialegacymulticonnect.protocols.v1_2.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.client.Minecraft;
import net.raphimc.vialegacy.api.LegacyProtocolVersions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.gui.screens.debug.GameModeSwitcherScreen$GameModeIcon")
public class MixinGameModeSwitcherScreen_GameModeIcon {
    @Inject(method = "getCommand", at = @At("HEAD"), cancellable = true)
    private void oldCommand(CallbackInfoReturnable<String> cir) {
        if (ViaLegacyMulticonnect.isEqualToOrOlder(LegacyProtocolVersions.r1_2_4tor1_2_5)) {
            cir.setReturnValue(
                "gamemode " + Minecraft.getInstance().getUser().getName() + ' ' + switch (((Enum<?>)(Object)this).ordinal()) {
                    case 0, 3 -> 1;
                    case 1, 2 -> 0;
                    default -> throw new AssertionError();
                }
            );
        }
    }
}
