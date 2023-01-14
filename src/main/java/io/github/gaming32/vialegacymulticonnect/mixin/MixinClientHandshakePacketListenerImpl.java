package io.github.gaming32.vialegacymulticonnect.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnectTranslator;
import net.earthcomputer.multiconnect.mixin.connect.ConnectionAccessor;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.raphimc.vialegacy.protocols.release.protocol1_7_2_5to1_6_4.storage.ProtocolMetadataStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientHandshakePacketListenerImpl.class)
public class MixinClientHandshakePacketListenerImpl {
    @Shadow @Final private Connection connection;

    @Inject(method = "authenticateServer", at = @At("HEAD"), cancellable = true)
    private void cancelIfNoAuthenticate(String serverHash, CallbackInfoReturnable<Component> cir) {
        //noinspection DataFlowIssue
        if (
            !((ConnectionAccessor)connection).getChannel()
                .attr(ViaLegacyMulticonnectTranslator.VIA_USER_CONNECTION_KEY)
                .get()
                .get(ProtocolMetadataStorage.class)
                .authenticate
        ) {
            cir.setReturnValue(null);
        }
    }
}
