package backstube.brotclient.mixins;

import backstube.brotclient.mods.AntiHuman;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Shadow public abstract ClientConnection getConnection();

    @Inject(at = @At("HEAD"), method = "sendPacket", cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof PlayerMoveC2SPacket && AntiHuman.isEnabled()) {
            Packet newPacket = new PlayerMoveC2SPacket.Full(
                    Math.round(((PlayerMoveC2SPacket) packet).getX(0) * 1000d) / 1000d,
                    ((PlayerMoveC2SPacket) packet).getY(0),
                    Math.round(((PlayerMoveC2SPacket) packet).getZ(0) * 1000d) / 1000d,
                    ((PlayerMoveC2SPacket) packet).getYaw(0),
                    ((PlayerMoveC2SPacket) packet).getPitch(0),
                    ((PlayerMoveC2SPacket) packet).isOnGround()
                    );
            System.out.println(((PlayerMoveC2SPacket) packet).getY(0));
            System.out.println(((PlayerMoveC2SPacket) newPacket).getY(0));
            getConnection().send(newPacket);
            ci.cancel();
        } else if (packet instanceof PlayerPositionLookS2CPacket && AntiHuman.isEnabled()) {
            Packet Packet = new PlayerPositionLookS2CPacket(
                    Math.round(MinecraftClient.getInstance().player.getX() * 1000d) / 1000d,
                    ((PlayerPositionLookS2CPacket) packet).getY(),
                    Math.round(MinecraftClient.getInstance().player.getZ() * 1000d) / 1000d,
                    ((PlayerPositionLookS2CPacket) packet).getYaw(),
                    ((PlayerPositionLookS2CPacket) packet).getPitch(),
                    ((PlayerPositionLookS2CPacket) packet).getFlags(),
                    ((PlayerPositionLookS2CPacket) packet).getTeleportId(),
                    ((PlayerPositionLookS2CPacket) packet).shouldDismount()
            );
        }
    }
}