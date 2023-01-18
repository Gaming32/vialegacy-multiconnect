package io.github.gaming32.vialegacymulticonnect.protocols.b1_8.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossCollisionBlock.class)
public class MixinCrossCollisionBlock {
    private static final VoxelShape OLD_FENCE_SHAPE = Block.box(0, 0, 0, 16, 32, 16);

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void oldFenceShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        //noinspection ConstantValue
        if ((Object)this instanceof FenceBlock && ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.r1_0_0tor1_0_1)) {
            cir.setReturnValue(OLD_FENCE_SHAPE);
        }
    }
}
