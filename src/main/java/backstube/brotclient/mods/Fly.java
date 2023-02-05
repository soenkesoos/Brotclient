package backstube.brotclient.mods;

import backstube.brotclient.mixins.PlayerPositionFullPacketMixin;
import backstube.brotclient.mixins.PlayerPositionPacketMixin;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import static backstube.brotclient.Brotclient.mc;

public class Fly {



    private static boolean enabled;
    public KeyBinding keybind;
    public static String mode = "vanilla";
    private static int flyingTimer = 0;

    public static int getFlyingTimer() {
        return flyingTimer;
    }

    public static void setFlyingTimer(int flyingTimer) {
        Fly.flyingTimer = flyingTimer;
    }

    public Fly() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null){
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                Vec3d velocity = MinecraftClient.getInstance().player.getVelocity();
                if (Fly.enabled && mode == "brot") {
                    double motionX = 0;
                    double motionY = 0;
                    double motionZ = 0;
                    if (MinecraftClient.getInstance().options.forwardKey.isPressed()) {
                        motionX = Math.sin(Math.toRadians(player.getYaw())) * -1;
                        motionZ = Math.cos(Math.toRadians(player.getYaw()));
                    }
                    motionY += MinecraftClient.getInstance().options.jumpKey.isPressed() ? 0.6 : -0.04;
                    motionY += MinecraftClient.getInstance().options.sneakKey.isPressed() ? -0.6 : 0;
                    player.setVelocity(new Vec3d(motionX, motionY, motionZ));
                }
                if (Fly.enabled && mode == "vanilla" && flyingTimer >= 70) {
                    flyingTimer = 0;
                }
            flyingTimer++;
            }
        });
    }

    public void onEnable() {
        if(mode == "vanilla") {
            mc.player.jump();
            mc.interactionManager.setGameMode(GameMode.SURVIVAL);
            mc.player.getAbilities().flying=true;
            mc.player.getAbilities().setFlySpeed(0.1f);

        }
    }

    public void onDisable() {
        MinecraftClient.getInstance().player.getAbilities().flying=false;
        Fly.enabled=false;
    }

    public void onToggle() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal(this.getClass().getSimpleName() + " " + isEnabled()), false);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getMode() {
        return mode;
    }

    public void toggle() {
        enabled = !enabled;
        onToggle();
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
}
