package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.api.connection.UserConnection;
import net.minecraft.client.Minecraft;
import net.raphimc.vialegacy.protocols.release.protocol1_3_1_2to1_2_4_5.providers.OldAuthProvider;

public class MulticonnectOldAuthProvider extends OldAuthProvider {
    @Override
    public void sendAuthRequest(UserConnection user, String serverId) throws Throwable {
        Minecraft.getInstance().getMinecraftSessionService().joinServer(
            Minecraft.getInstance().getUser().getGameProfile(),
            Minecraft.getInstance().getUser().getAccessToken(),
            serverId
        );
    }
}
