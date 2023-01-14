package io.github.gaming32.vialegacymulticonnect.mixin.multiconnect;

import net.earthcomputer.multiconnect.connect.ConnectionMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ConnectionMode.class, remap = false)
public class MixinConnectionHandler {
    /**
     * @author Gaming32
     * @reason We support all relevant protocols for auto-detection now
     */
    @Overwrite
    public static boolean isSupportedProtocol(int protocol) {
        return true;
    }
}
