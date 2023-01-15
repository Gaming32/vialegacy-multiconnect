package io.github.gaming32.vialegacymulticonnect.protocols.b1_7.mixin;

import io.github.gaming32.vialegacymulticonnect.ViaLegacyMulticonnect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.raphimc.vialegacy.api.LegacyProtocolVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class MixinBowItem {
    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    private void getUseAnimation(ItemStack stack, CallbackInfoReturnable<UseAnim> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            cir.setReturnValue(UseAnim.NONE);
        }
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void use(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (ViaLegacyMulticonnect.isOlder(LegacyProtocolVersion.b1_8tob1_8_1)) {
            final ItemStack inHand = player.getItemInHand(usedHand);
            player.getProjectile(inHand).shrink(1);
            cir.setReturnValue(InteractionResultHolder.consume(inHand));
        }
    }
}
