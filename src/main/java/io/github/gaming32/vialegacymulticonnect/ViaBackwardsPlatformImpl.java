package io.github.gaming32.vialegacymulticonnect;

import com.viaversion.viabackwards.api.ViaBackwardsPlatform;
import com.viaversion.viaversion.api.Via;

import java.io.File;
import java.util.logging.Logger;

public class ViaBackwardsPlatformImpl implements ViaBackwardsPlatform {
    public ViaBackwardsPlatformImpl() {
        init(Via.getPlatform().getDataFolder());
    }

    @Override
    public Logger getLogger() {
        return Via.getPlatform().getLogger();
    }

    @Override
    public boolean isOutdated() {
        return false;
    }

    @Override
    public void disable() {
    }

    @Override
    public File getDataFolder() {
        return new File(Via.getPlatform().getDataFolder(), "viabackwards.yml");
    }
}
