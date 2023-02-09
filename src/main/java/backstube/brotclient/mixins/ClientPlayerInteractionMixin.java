package backstube.brotclient.mixins;

import backstube.brotclient.Brotclient;
import backstube.brotclient.mods.FastBreak;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static backstube.brotclient.Brotclient.client;


@Mixin(net.minecraft.client.network.ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionMixin {
    // Insta-Mine hack
    @Inject(method = "attackBlock", at = @At(value = "HEAD"), cancellable = true)
    private void attackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (FastBreak.isEnabled()) {
            assert client.world != null;
            assert client.player != null;
            BlockState blockState = client.world.getBlockState(pos);
            double speed = blockState.calcBlockBreakingDelta(client.player, client.world, pos);
            if (!blockState.isAir() && speed > 0.5F) {
                client.world.breakBlock(pos, true, client.player);
                client.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, direction));
                client.getNetworkHandler().sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, pos, direction));
                cir.setReturnValue(true);
            }
        }
    }
}