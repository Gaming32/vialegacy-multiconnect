package io.github.gaming32.vialegacymulticonnect.protocols.b1_8;

import net.earthcomputer.multiconnect.api.ProtocolBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Protocol_b1_8 extends ProtocolBehavior {
    @Override
    public Block[] getBlocksWithChangedCollision() {
        return new Block[] {Blocks.OAK_FENCE};
    }
}
