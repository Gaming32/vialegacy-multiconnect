package io.github.gaming32.vialegacymulticonnect;

import com.mojang.logging.LogUtils;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_8.Protocol_b1_8;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_0.Protocol_1_0;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_1.Protocol_1_1;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_2.Protocol_1_2_1;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_2.Protocol_1_2_4;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_3.Protocol_1_3_1;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_4.Protocol_1_4_2;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_4.Protocol_1_4_4;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_4.Protocol_1_4_6;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_5.Protocol_1_5;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_5.Protocol_1_5_2;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_6.Protocol_1_6_1;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_6.Protocol_1_6_2;
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
        ConnectionMode.register("1.6.4", LegacyProtocolVersions.r1_6_4.getVersion(), 96, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.6.2", LegacyProtocolVersions.r1_6_2.getVersion(), 95, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.6.1", LegacyProtocolVersions.r1_6_1.getVersion(), 94, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.5.2", LegacyProtocolVersions.r1_5_2.getVersion(), 93, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.5",   LegacyProtocolVersions.r1_5tor1_5_1.getVersion(), 92, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.4.6", LegacyProtocolVersions.r1_4_6tor1_4_7.getVersion(), 91, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.4.4", LegacyProtocolVersions.r1_4_4tor1_4_5.getVersion(), 90, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.4.2", LegacyProtocolVersions.r1_4_2.getVersion(), 89, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.3.1", LegacyProtocolVersions.r1_3_1tor1_3_2.getVersion(), 88, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.2.4", LegacyProtocolVersions.r1_2_4tor1_2_5.getVersion(), 87, MULTICONNECT_EXTENSION | MULTICONNECT_BETA);
        ConnectionMode.register("1.2.1", LegacyProtocolVersions.r1_2_1tor1_2_3.getVersion(), 86, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.1",   LegacyProtocolVersions.r1_1.getVersion(), 85, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("1.0",   LegacyProtocolVersions.r1_0_0tor1_0_1.getVersion(), 84, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);
        ConnectionMode.register("b1.8",  LegacyProtocolVersions.b1_8tob1_8_1.getVersion(), 84, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);

        ProtocolRegistry.register(ProtocolVersion.v1_7_6.getVersion(), new Protocol_1_7_6());
        ProtocolRegistry.register(ProtocolVersion.v1_7_1.getVersion(), new Protocol_1_7_2());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_6_4.getVersion(), new Protocol_1_6_4());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_6_2.getVersion(), new Protocol_1_6_2());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_6_1.getVersion(), new Protocol_1_6_1());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_5_2.getVersion(), new Protocol_1_5_2());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_5tor1_5_1.getVersion(), new Protocol_1_5());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_4_6tor1_4_7.getVersion(), new Protocol_1_4_6());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_4_4tor1_4_5.getVersion(), new Protocol_1_4_4());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_4_2.getVersion(), new Protocol_1_4_2());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_3_1tor1_3_2.getVersion(), new Protocol_1_3_1());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_2_4tor1_2_5.getVersion(), new Protocol_1_2_4());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_2_1tor1_2_3.getVersion(), new Protocol_1_2_1());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_1.getVersion(), new Protocol_1_1());
        ProtocolRegistry.register(LegacyProtocolVersions.r1_0_0tor1_0_1.getVersion(), new Protocol_1_0());
        ProtocolRegistry.register(LegacyProtocolVersions.b1_8tob1_8_1.getVersion(), new Protocol_b1_8());
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
