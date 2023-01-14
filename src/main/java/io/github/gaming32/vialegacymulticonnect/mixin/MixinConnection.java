package io.github.gaming32.vialegacymulticonnect.mixin;

import io.github.gaming32.vialegacymulticonnect.EncryptableConnection;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnectTranslator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.CipherDecoder;
import net.minecraft.network.CipherEncoder;
import net.minecraft.network.Connection;
import net.raphimc.vialegacy.api.LegacyProtocolVersions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.crypto.Cipher;

@Mixin(Connection.class)
public abstract class MixinConnection implements EncryptableConnection {
    @Shadow private Channel channel;
    @Shadow private boolean encrypted;
    private Cipher vlm$decryptingCipher;
    private Cipher vlm$encryptingCipher;

    @Inject(method = "setEncryptionKey", at = @At("HEAD"), cancellable = true)
    private void storeEncryptionKey(Cipher decryptingCipher, Cipher encryptingCipher, CallbackInfo ci) {
        if (ViaLegacyMulticonnect.isEqualToOrOlder(LegacyProtocolVersions.r1_6_4)) {
            ci.cancel();
            vlm$decryptingCipher = decryptingCipher;
            vlm$encryptingCipher = encryptingCipher;
        }
    }

    @Inject(method = "channelActive", at = @At("TAIL"))
    private void setConnectionKey(ChannelHandlerContext channelHandlerContext, CallbackInfo ci) {
        channel.attr(ViaLegacyMulticonnectTranslator.MC_CONNECTION_KEY).set((Connection)(Object)this);
    }

    @Override
    public void encryptNow() {
        encrypted = true;
        channel.pipeline().addBefore("via-pre-netty-decoder", "decrypt", new CipherDecoder(vlm$decryptingCipher));
        channel.pipeline().addBefore("via-pre-netty-encoder", "encrypt", new CipherEncoder(vlm$encryptingCipher));
    }
}
