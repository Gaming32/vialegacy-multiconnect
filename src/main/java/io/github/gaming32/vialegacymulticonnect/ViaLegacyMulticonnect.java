package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_7.Protocol_1_7_2;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_7.Protocol_1_7_6;
import net.earthcomputer.multiconnect.connect.ConnectionMode;
import net.earthcomputer.multiconnect.protocols.ProtocolRegistry;
import net.fabricmc.api.ModInitializer;

import static net.earthcomputer.multiconnect.connect.ConnectionMode.InitFlags.*;

public class ViaLegacyMulticonnect implements ModInitializer {
    @Override
    public void onInitialize() {
        ConnectionMode.register("1.7.6", ProtocolVersion.v1_7_6.getVersion(), 98, MULTICONNECT_BETA | MULTICONNECT_EXTENSION);
        ConnectionMode.register("1.7.2", ProtocolVersion.v1_7_1.getVersion(), 97, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | MAJOR_RELEASE);

        ProtocolRegistry.register(ProtocolVersion.v1_7_6.getVersion(), new Protocol_1_7_6());
        ProtocolRegistry.register(ProtocolVersion.v1_7_1.getVersion(), new Protocol_1_7_2());
    }
}
