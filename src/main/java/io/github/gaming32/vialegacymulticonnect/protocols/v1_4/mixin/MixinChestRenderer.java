package io.github.gaming32.vialegacymulticonnect.protocols.v1_4.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public class MixinChestRenderer<T extends BlockEntity & LidBlockEntity> {
    @Inject(
        method = "render(Lnet/minecraft/world/level/block/entity/BlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void dontRender(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, CallbackInfo ci) {
        if (partialTick != 0 && ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            // partialTick == 0 when rendering it as an item
            ci.cancel();
        }
    }
}
