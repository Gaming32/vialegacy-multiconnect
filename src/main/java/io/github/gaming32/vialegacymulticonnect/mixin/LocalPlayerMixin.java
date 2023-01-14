package io.github.gaming32.vialegacymulticonnect.mixin;

import com.mojang.authlib.GameProfile;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.type.Type;
import com.viaversion.viaversion.protocols.protocol1_9to1_8.ClientboundPackets1_9;
import com.viaversion.viaversion.protocols.protocol1_9to1_8.Protocol1_9To1_8;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnectTranslator;
import net.earthcomputer.multiconnect.mixin.connect.ConnectionAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    private static final UUID ARMOR_ATTRIBUTE = UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150");

    @Shadow @Final protected Minecraft minecraft;

    @Shadow @Final public ClientPacketListener connection;

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void recalculateArmor(CallbackInfo ci) {
        if (ViaLegacyMulticonnect.isEqualToOrNewer(ProtocolVersion.v1_9)) return;
        int armor = 0;
        for (ItemStack stack : getArmorSlots()) {
            if (stack.getItem() instanceof ArmorItem armorItem) {
                armor += armorItem.getDefense();
            }
        }

        PacketWrapper wrapper = PacketWrapper.create(
            ClientboundPackets1_9.ENTITY_PROPERTIES, null,
            ((ConnectionAccessor)connection.getConnection())
                .getChannel()
                .attr(ViaLegacyMulticonnectTranslator.VIA_USER_CONNECTION_KEY)
                .get()
        );
        try {
            wrapper.write(Type.VAR_INT, getId()); // Player ID
            wrapper.write(Type.INT, 1); // only 1 property
            wrapper.write(Type.STRING, "generic.armor");
            wrapper.write(Type.DOUBLE, 0D); //default 0 armor
            wrapper.write(Type.VAR_INT, 1); // 1 modifier
            wrapper.write(Type.UUID, ARMOR_ATTRIBUTE); // armor modifier uuid
            wrapper.write(Type.DOUBLE, (double) armor); // the modifier value
            wrapper.write(Type.BYTE, (byte) 0);// the modifier operation, 0 is add number

            wrapper.scheduleSend(Protocol1_9To1_8.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
