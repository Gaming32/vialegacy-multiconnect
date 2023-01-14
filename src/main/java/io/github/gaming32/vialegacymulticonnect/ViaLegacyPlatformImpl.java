package io.github.gaming32.vialegacymulticonnect;

import com.google.common.collect.Range;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.raphimc.vialegacy.api.LegacyProtocolVersions;
import net.raphimc.vialegacy.platform.ViaLegacyPlatform;
import net.raphimc.vialegacy.protocols.release.protocol1_7_2_5to1_6_4.baseprotocols.EmptyBaseProtocol;

import java.io.File;
import java.util.logging.Logger;

public class ViaLegacyPlatformImpl implements ViaLegacyPlatform {
    public ViaLegacyPlatformImpl() {
        init(getDataFolder());
        for (ProtocolVersion version : LegacyProtocolVersions.PROTOCOLS) {
            Via.getManager().getProtocolManager().registerBaseProtocol(new EmptyBaseProtocol(), Range.singleton(version.getVersion()));
        }
    }

    @Override
    public Logger getLogger() {
        return Via.getPlatform().getLogger();
    }

    @Override
    public File getDataFolder() {
        return Via.getPlatform().getDataFolder();
    }
}
