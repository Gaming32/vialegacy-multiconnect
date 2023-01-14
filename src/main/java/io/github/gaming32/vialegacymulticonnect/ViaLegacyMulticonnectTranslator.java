package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viaversion.ViaManagerImpl;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.protocol.ProtocolManagerImpl;
import net.earthcomputer.multiconnect.api.IMulticonnectTranslatorApi;
import net.earthcomputer.multiconnect.impl.via.MulticonnectInjector;
import net.earthcomputer.multiconnect.impl.via.MulticonnectLoader;
import net.earthcomputer.multiconnect.impl.via.MulticonnectPlatform;
import net.earthcomputer.multiconnect.impl.via.ViaMulticonnectTranslator;

public class ViaLegacyMulticonnectTranslator extends ViaMulticonnectTranslator {
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

        Via.getManager().addEnableListener(ViaLegacyPlatformImpl::new);

        manager.init();

        Via.getManager().getProtocolManager().setMaxProtocolPathSize(Integer.MAX_VALUE);
        Via.getManager().getProtocolManager().setMaxPathDeltaIncrease(-1);
        ((ProtocolManagerImpl)Via.getManager().getProtocolManager()).refreshVersions();
    }
}
