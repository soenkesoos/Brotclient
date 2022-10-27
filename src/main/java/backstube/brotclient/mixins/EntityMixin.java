package backstube.brotclient.mixins;


import backstube.brotclient.mods.ESP;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(at = @At("RETURN"), method = "isGlowing", cancellable = true)
    public boolean isGlowing(CallbackInfoReturnable<CommandSource> ci){
        if(ESP.isEnabled()) {return true;}
        ci.cancel();
        return false;
    }
}