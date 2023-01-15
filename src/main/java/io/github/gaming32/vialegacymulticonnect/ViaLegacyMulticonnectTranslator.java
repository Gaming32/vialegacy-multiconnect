package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.ViaManagerImpl;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.data.MappingDataLoader;
import com.viaversion.viaversion.protocol.ProtocolManagerImpl;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import net.earthcomputer.multiconnect.api.IMulticonnectTranslatorApi;
import net.earthcomputer.multiconnect.impl.via.MulticonnectInjector;
import net.earthcomputer.multiconnect.impl.via.MulticonnectLoader;
import net.earthcomputer.multiconnect.impl.via.MulticonnectPlatform;
import net.earthcomputer.multiconnect.impl.via.ViaMulticonnectTranslator;
import net.minecraft.network.Connection;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import net.raphimc.vialegacy.netty.PreNettyDecoder;
import net.raphimc.vialegacy.netty.PreNettyEncoder;
import net.raphimc.vialegacy.protocols.release.protocol1_3_1_2to1_2_4_5.providers.OldAuthProvider;
import net.raphimc.vialegacy.protocols.release.protocol1_7_2_5to1_6_4.baseprotocols.PreNettyBaseProtocol;
import net.raphimc.vialegacy.protocols.release.protocol1_7_2_5to1_6_4.providers.EncryptionProvider;
import net.raphimc.vialegacy.protocols.release.protocol1_8to1_7_6_10.providers.GameProfileFetcher;

public class ViaLegacyMulticonnectTranslator extends ViaMulticonnectTranslator {
    public static final AttributeKey<UserConnection> VIA_USER_CONNECTION_KEY = AttributeKey.valueOf("multiconnect.via_user_connection");
    public static final AttributeKey<Connection> MC_CONNECTION_KEY = AttributeKey.valueOf("multiconnect.mc_connection");

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public void init(IMulticonnectTranslatorApi api) {
        this.api = api;

        var manager = ViaManagerImpl.builder()
            .injector(new MulticonnectInjector())
            .loader(new MulticonnectLoader(api))
            .platform(new MulticonnectPlatform(api))
            .build();

        Via.init(manager);
        MappingDataLoader.enableMappingsCache();

        Via.getManager().addEnableListener(ViaBackwardsPlatformImpl::new);
        Via.getManager().addEnableListener(ViaLegacyPlatformImpl::new);

        manager.init();

        Via.getManager().getProtocolManager().setMaxProtocolPathSize(Integer.MAX_VALUE);
        Via.getManager().getProtocolManager().setMaxPathDeltaIncrease(-1);
        ((ProtocolManagerImpl)Via.getManager().getProtocolManager()).refreshVersions();

        Via.getManager().getProviders().use(EncryptionProvider.class, new MulticonnectEncryptionProvider());
        Via.getManager().getProviders().use(GameProfileFetcher.class, new MulticonnectGameProfileFetcher());
        Via.getManager().getProviders().use(OldAuthProvider.class, new MulticonnectOldAuthProvider());
    }

    @Override
    public void inject(Channel channel) {
        super.inject(channel);

        if (ViaLegacyMulticonnect.isEqualToOrOlder(LegacyProtocolVersion.r1_6_4)) {
            final UserConnection user = channel.attr(VIA_USER_CONNECTION_KEY).get();
            user.getProtocolInfo().getPipeline().add(PreNettyBaseProtocol.INSTANCE);
            channel.pipeline().addBefore("prepender", "via-pre-netty-encoder", new PreNettyEncoder(user));
            channel.pipeline().addBefore("splitter", "via-pre-netty-decoder", new PreNettyDecoder(user));
        }
    }
}
