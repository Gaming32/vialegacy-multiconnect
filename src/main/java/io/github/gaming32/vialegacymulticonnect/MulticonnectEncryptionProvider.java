package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.api.connection.UserConnection;
import net.raphimc.vialegacy.protocols.release.protocol1_7_2_5to1_6_4.providers.EncryptionProvider;

public class MulticonnectEncryptionProvider extends EncryptionProvider {
    @Override
    public void enableDecryption(UserConnection user) {
        ((EncryptableConnection)user.getChannel().attr(ViaLegacyMulticonnectTranslator.MC_CONNECTION_KEY).get()).encryptNow();
    }
}
