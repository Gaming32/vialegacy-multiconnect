package io.github.gaming32.vialegacymulticonnect.protocols.b1_7.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
@SuppressWarnings({"UnresolvedMixinReference", "MixinAnnotationTarget"})
public class MixinSwordItem extends TieredItem {
    public MixinSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Inject(method = {"use", "method_7836", "m_znsmbimv"}, at = @At("HEAD"), cancellable = true, require = 1)
    private void noBlock(Level level, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            cir.setReturnValue(super.use(level, user, hand));
        }
    }

    @Inject(method = {"getUseAnimation", "method_7853", "m_yvddkccr"}, at = @At("HEAD"), cancellable = true, require = 1)
    private void noBlockAnimation(ItemStack stack, CallbackInfoReturnable<UseAnim> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            cir.setReturnValue(super.getUseAnimation(stack));
        }
    }

    @Inject(method = {"getUseDuration", "method_7881", "m_hwznhtnf"}, at = @At("HEAD"), cancellable = true, require = 1)
    private void noBlockDuration(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            cir.setReturnValue(super.getUseDuration(stack));
        }
    }
}
