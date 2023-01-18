package io.github.gaming32.vialegacymulticonnect.protocols.v1_4;

import net.earthcomputer.multiconnect.api.ProtocolBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Protocol_1_4_2 extends ProtocolBehavior {
    @Override
    public Block[] getBlocksWithChangedCollision() {
        return new Block[] {Blocks.CHEST, Blocks.TRAPPED_CHEST};
    }
}
