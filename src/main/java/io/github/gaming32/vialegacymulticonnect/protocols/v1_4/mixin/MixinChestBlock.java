package io.github.gaming32.vialegacymulticonnect.protocols.v1_4.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(ChestBlock.class)
public abstract class MixinChestBlock extends AbstractChestBlock<ChestBlockEntity> {
    protected MixinChestBlock(Properties properties, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(properties, supplier);
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void oldCollision(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.r1_4_4tor1_4_5)) {
            cir.setReturnValue(Shapes.block());
        }
    }

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    private void oldCollision(BlockState state, CallbackInfoReturnable<RenderShape> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            cir.setReturnValue(RenderShape.MODEL);
        }
    }

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1) ? Shapes.block() : Shapes.empty();
    }
}
