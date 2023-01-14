package io.github.gaming32.vialegacymulticonnect;

import com.mojang.logging.LogUtils;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_6.Protocol_1_6_4;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_7.Protocol_1_7_2;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_7.Protocol_1_7_6;
import net.earthcomputer.multiconnect.connect.ConnectionMode;
import net.earthcomputer.multiconnect.impl.ConnectionInfo;
import net.earthcomputer.multiconnect.protocols.ProtocolRegistry;
import net.fabricmc.api.ModInitializer;
import net.raphimc.vialegacy.api.LegacyProtocolVersions;
import org.slf4j.Logger;

import static net.earthcomputer.multiconnect.connect.ConnectionMode.InitFlags.*;

public class ViaLegacyMulticonnect implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ConnectionMode.register("1.7.6", ProtocolVersion.v1_7_6.getVersion(), 98, MULTICONNECT_BETA | MULTICONNECT_EXTENSION);
        ConnectionMode.register("1.7.2", ProtocolVersion.v1_7_1.getVersion(), 97, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.6.4", LegacyProtocolVersions.r1_6_4.getVersion(), 97, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);

        ProtocolRegistry.register(ProtocolVersion.v1_7_6.getVersion(), new Protocol_1_7_6());
        ProtocolRegistry.register(ProtocolVersion.v1_7_1.getVersion(), new Protocol_1_7_2());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_6_4.getVersion(), new Protocol_1_6_4());
    }

    public static int protocolCompare(int a, int b) {
        if (a > 0 || b > 0) {
            // If at least one is modern, then a straight compare works fine.
            return a - b;
        }
        // Both are legacy
        a = Math.abs(a);
        b = Math.abs(b);
        final int baseProtocolA = a >> 2;
        final int baseProtocolB = b >> 2;
        if (baseProtocolA != baseProtocolB) {
            return baseProtocolA - baseProtocolB;
        }
        // They're either the same version or one where the protocol overlaps.
        final int discriminatorA = a & 3;
        final int discriminatorB = b & 3;
        return discriminatorB - discriminatorA; // Higher discriminator means older version
    }

    public static boolean isEqualToOrOlder(ProtocolVersion protocol) {
        return protocolCompare(ConnectionInfo.protocolVersion, protocol.getVersion()) <= 0;
    }
}
