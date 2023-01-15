package io.github.gaming32.vialegacymulticonnect.protocols.b1_7.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinGui {
    @Shadow private int screenHeight;

    @Shadow private int screenWidth;

    @Shadow protected abstract int getVehicleMaxHearts(LivingEntity mountEntity);

    @Inject(method = "renderVehicleHealth", at = @At("HEAD"), cancellable = true)
    private void dontRenderVehicleHealth(PoseStack poseStack, CallbackInfo ci) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderJumpMeter", at = @At("HEAD"), cancellable = true)
    private void dontRenderJumpMeter(PlayerRideableJumping playerRideableJumping, PoseStack poseStack, int i, CallbackInfo ci) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void dontRenderExperienceBar(PoseStack poseStack, int xPos, CallbackInfo ci) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            ci.cancel();
        }
    }

    @Redirect(
        method = "renderPlayerHealth",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/gui/Gui;screenHeight:I",
            opcode = Opcodes.GETFIELD
        )
    )
    private int moveHealthDown(Gui instance) {
        if (ViaLegacyMulticonnect.isNewer(LegacyProtocolVersion.b1_7tob1_7_3)) {
            return screenHeight;
        }
        return screenHeight + 6;
    }

    @ModifyArg(
        method = "renderPlayerHealth",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"
        ),
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"
            ),
            to = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V",
                ordinal = 0
            )
        ),
        index = 1
    )
    private int moveArmor(int old) {
        if (ViaLegacyMulticonnect.isNewer(LegacyProtocolVersion.b1_7tob1_7_3)) {
            return old;
        }
        return screenWidth - old - 9;
    }

    @ModifyArg(
        method = "renderPlayerHealth",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"
        ),
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"
            ),
            to = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V",
                ordinal = 0
            )
        ),
        index = 2
    )
    private int moveArmorDown(int old) {
        if (ViaLegacyMulticonnect.isNewer(LegacyProtocolVersion.b1_7tob1_7_3)) {
            return old;
        }
        return screenHeight - 39 + 6;
    }

    @Redirect(
        method = "renderPlayerHealth",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"
        )
    )
    private int dontRenderHunger(Gui instance, LivingEntity mountEntity) {
        // Hunger only renders when this returns 0
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            return 1;
        }
        return getVehicleMaxHearts(mountEntity);
    }

    @ModifyArg(
        method = "renderPlayerHealth",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"
        ),
        slice = @Slice(
            from = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V",
                ordinal = 2
            ),
            to = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"
            )
        ),
        index = 1
    )
    private int moveAir(int old) {
        if (ViaLegacyMulticonnect.isNewer(LegacyProtocolVersion.b1_7tob1_7_3)) {
            return old;
        }
        return screenWidth - old - 9;
    }
}
