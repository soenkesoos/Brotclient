package backstube.brotclient.mixins;


import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow
    public abstract boolean isDemo();

    @Inject(at = @At("RETURN"), method = "isDemo", cancellable = true)
    public boolean isDemo(CallbackInfoReturnable<Boolean> cir) {
        return false;
    }
}
