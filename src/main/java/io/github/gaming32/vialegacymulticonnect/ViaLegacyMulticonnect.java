package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_0.Protocol_b1_0;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_1.Protocol_b1_1_02;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_2.Protocol_b1_2;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_3.Protocol_b1_3;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_4.Protocol_b1_4;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_5.Protocol_b1_5;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_6.Protocol_b1_6;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_7.Protocol_b1_7;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static net.earthcomputer.multiconnect.connect.ConnectionMode.InitFlags.*;

public class ViaLegacyMulticonnect implements ModInitializer {
    public static final String MOD_ID = "vialegacy-multiconnect";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceLocation RANDOM_HURT_ID = new ResourceLocation(MOD_ID, "random.hurt");
    public static final SoundEvent RANDOM_HURT = SoundEvent.createVariableRangeEvent(RANDOM_HURT_ID);

    @Override
    public void onInitialize() {
        register("1.7.6",   ProtocolVersion.v1_7_6, false, Protocol_1_7_6::new);
        register("1.7.2",   ProtocolVersion.v1_7_1, true, Protocol_1_7_2::new);
        register("1.6.4",   LegacyProtocolVersion.r1_6_4, false, Protocol_1_6_4::new);
        register("1.6.2",   LegacyProtocolVersion.r1_6_2, false, Protocol_1_6_2::new);
        register("1.6.1",   LegacyProtocolVersion.r1_6_1, true, Protocol_1_6_1::new);
        register("1.5.2",   LegacyProtocolVersion.r1_5_2, false, Protocol_1_5_2::new);
        register("1.5",     LegacyProtocolVersion.r1_5tor1_5_1, true, Protocol_1_5::new);
        register("1.4.6",   LegacyProtocolVersion.r1_4_6tor1_4_7, false, Protocol_1_4_6::new);
        register("1.4.4",   LegacyProtocolVersion.r1_4_4tor1_4_5, false, Protocol_1_4_4::new);
        register("1.4.2",   LegacyProtocolVersion.r1_4_2, true, Protocol_1_4_2::new);
        register("1.3.1",   LegacyProtocolVersion.r1_3_1tor1_3_2, true, Protocol_1_3_1::new);
        register("1.2.4",   LegacyProtocolVersion.r1_2_4tor1_2_5, false, Protocol_1_2_4::new);
        register("1.2.1",   LegacyProtocolVersion.r1_2_1tor1_2_3, true, Protocol_1_2_1::new);
        register("1.1",     LegacyProtocolVersion.r1_1, true, Protocol_1_1::new);
        register("1.0",     LegacyProtocolVersion.r1_0_0tor1_0_1, true, Protocol_1_0::new);
        register("b1.8",    LegacyProtocolVersion.b1_8tob1_8_1, true, Protocol_b1_8::new);
        register("b1.7",    LegacyProtocolVersion.b1_7tob1_7_3, true, Protocol_b1_7::new);
        register("b1.6",    LegacyProtocolVersion.b1_6tob1_6_6, true, Protocol_b1_6::new);
        register("b1.5",    LegacyProtocolVersion.b1_5tob1_5_2, true, Protocol_b1_5::new);
        register("b1.4",    LegacyProtocolVersion.b1_4tob1_4_1, true, Protocol_b1_4::new);
        register("b1.3",    LegacyProtocolVersion.b1_3tob1_3_1, true, Protocol_b1_3::new);
        register("b1.2",    LegacyProtocolVersion.b1_2_0tob1_2_2, true, Protocol_b1_2::new);
        register("b1.1_02", LegacyProtocolVersion.b1_1_2, true, Protocol_b1_1_02::new);
        register("b1.0",    LegacyProtocolVersion.b1_0tob1_1_1, true, Protocol_b1_0::new);
    }

    // Use Protocol_1_7_6, so we don't accidentally reference something newer
    private static void register(String name, ProtocolVersion version, boolean isMajor, Supplier<Protocol_1_7_6> protocolSupplier) {
        ConnectionMode.register(name, version.getVersion(), 99, MULTICONNECT_EXTENSION | MULTICONNECT_BETA | (isMajor ? MAJOR_RELEASE : 0));
        ProtocolRegistry.register(version.getVersion(), protocolSupplier.get());
    }

    public static boolean isEqualToOrOlder(ProtocolVersion protocol) {
        return LegacyProtocolVersion.protocolCompare(ConnectionInfo.protocolVersion, protocol.getVersion()) <= 0;
    }

    public static boolean isOlder(ProtocolVersion protocol) {
        return LegacyProtocolVersion.protocolCompare(ConnectionInfo.protocolVersion, protocol.getVersion()) < 0;
    }

    public static boolean isEqualToOrNewer(ProtocolVersion protocol) {
        return LegacyProtocolVersion.protocolCompare(ConnectionInfo.protocolVersion, protocol.getVersion()) >= 0;
    }

    public static boolean isNewer(ProtocolVersion protocol) {
        return LegacyProtocolVersion.protocolCompare(ConnectionInfo.protocolVersion, protocol.getVersion()) > 0;
    }
}
