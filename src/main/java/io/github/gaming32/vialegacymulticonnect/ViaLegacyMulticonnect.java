package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.github.gaming32.vialegacymulticonnect.protocols.b1_8.Protocol_b1_8;
import io.github.gaming32.vialegacymulticonnect.protocols.v1_4.Protocol_1_4_2;
import net.earthcomputer.multiconnect.api.CustomProtocolBuilder;
import net.earthcomputer.multiconnect.api.MultiConnectAPI;
import net.earthcomputer.multiconnect.api.ProtocolBehavior;
import net.earthcomputer.multiconnect.impl.ConnectionInfo;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class ViaLegacyMulticonnect implements ModInitializer {
    public static final String MOD_ID = "vialegacy-multiconnect";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceLocation RANDOM_HURT_ID = new ResourceLocation(MOD_ID, "random.hurt");
    public static final SoundEvent RANDOM_HURT = SoundEvent.createVariableRangeEvent(RANDOM_HURT_ID);

    @Override
    public void onInitialize() {
        register("1.7.6", ProtocolVersion.v1_7_6, false, null, null);
        register("1.7.2", ProtocolVersion.v1_7_1, true, null, "1.7");
        register("1.6.4", LegacyProtocolVersion.r1_6_4, false, null, null);
        register("1.6.2", LegacyProtocolVersion.r1_6_2, false, null, null);
        register("1.6.1", LegacyProtocolVersion.r1_6_1, true, null, "1.6");
        register("1.5.2", LegacyProtocolVersion.r1_5_2, false, null, null);
        register("1.5", LegacyProtocolVersion.r1_5tor1_5_1, true, null, null);
        register("1.4.6", LegacyProtocolVersion.r1_4_6tor1_4_7, false, null, null);
        register("1.4.4", LegacyProtocolVersion.r1_4_4tor1_4_5, false, null, null);
        register("1.4.2", LegacyProtocolVersion.r1_4_2, true, Protocol_1_4_2::new, "1.4");
        register("1.3.1", LegacyProtocolVersion.r1_3_1tor1_3_2, true, null, "1.3");
        register("1.2.4", LegacyProtocolVersion.r1_2_4tor1_2_5, false, null, null);
        register("1.2.1", LegacyProtocolVersion.r1_2_1tor1_2_3, true, null, "1.2");
        register("1.1", LegacyProtocolVersion.r1_1, true, null, null);
        register("1.0", LegacyProtocolVersion.r1_0_0tor1_0_1, true, null, null);
        register("b1.8", LegacyProtocolVersion.b1_8tob1_8_1, true, Protocol_b1_8::new, null);
        register("b1.7", LegacyProtocolVersion.b1_7tob1_7_3, true, null, null);
        register("b1.6", LegacyProtocolVersion.b1_6tob1_6_6, true, null, null);
        register("b1.5", LegacyProtocolVersion.b1_5tob1_5_2, true, null, null);
        register("b1.4", LegacyProtocolVersion.b1_4tob1_4_1, true, null, null);
        register("b1.3", LegacyProtocolVersion.b1_3tob1_3_1, true, null, null);
        register("b1.2", LegacyProtocolVersion.b1_2_0tob1_2_2, true, null, null);
        register("b1.1_02", LegacyProtocolVersion.b1_1_2, true, null, null);
        register("b1.0", LegacyProtocolVersion.b1_0tob1_1_1, true, null, null);
    }

    // Use Protocol_1_7_6, so we don't accidentally reference something newer
    private static void register(
        String name,
        ProtocolVersion version,
        boolean isMajor,
        @Nullable Supplier<ProtocolBehavior> behaviorSupplier,
        @Nullable String majorVersionName
    ) {
        final CustomProtocolBuilder protocol = MultiConnectAPI.instance()
            .createCustomProtocol(version.getVersion(), name, 99)
            .markBeta();
        if (version.getVersion() < 0) {
            protocol.sortingIndex(-(78 << 2) - version.getVersion());
        }
        if (isMajor) {
            protocol.majorVersion();
        }
        if (behaviorSupplier != null) {
            protocol.behavior(behaviorSupplier.get());
        }
        if (majorVersionName != null) {
            protocol.majorReleaseName(majorVersionName);
        }
        protocol.register();
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
