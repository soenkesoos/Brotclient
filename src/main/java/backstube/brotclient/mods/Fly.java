package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

import static backstube.brotclient.Brotclient.client;

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
                //player.sendMessage(Text.of(String.valueOf(velocity.getY())), false);
                if (flyingTimer>=50){
                    //player.sendMessage(Text.of(String.valueOf(player.getY())), false);
                    //player.sendMessage(Text.of("packet" + String.valueOf(player.getY() - velocity.getY()-5)), false);
                }
                if (Fly.enabled && mode == "vanilla" && flyingTimer >= 50) {
                    player.setVelocity(velocity.getX(), -0.1, velocity.getZ());
                }
                if (Fly.enabled && mode == "vanilla" && flyingTimer >= 52) {
                    player.setVelocity(velocity.getX(), 0.1, velocity.getZ());
                }
                if (Fly.enabled && mode == "vanilla" && flyingTimer >= 54) {
                    player.setVelocity(velocity.getX(), 0.0, velocity.getZ());
                    flyingTimer = 0;
                }
                //player.sendMessage(Text.of(String.valueOf(flyingTimer)));
                flyingTimer++;
            }
        });
    }

    public static void onEnable() {
        flyingTimer = 1;
        if(mode == "vanilla") {
            client.interactionManager.setGameMode(GameMode.SURVIVAL);
            client.player.getAbilities().allowFlying = true;
            client.player.getAbilities().flying = true;
            client.player.getAbilities().setFlySpeed(0.1f);

        }
        Fly.enabled = true;
    }

    public static void onDisable() {
        MinecraftClient.getInstance().player.jump();
        MinecraftClient.getInstance().player.getAbilities().flying=false;
        MinecraftClient.getInstance().player.getAbilities().allowFlying = false;
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
