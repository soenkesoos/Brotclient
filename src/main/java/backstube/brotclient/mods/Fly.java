package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class Fly {

    public Fly() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(Fly.enabled) {
                    ClientPlayerEntity player = MinecraftClient.getInstance().player;
                    Vec3d velocity = MinecraftClient.getInstance().player.getVelocity();

                    double motionX = 0;
                    double motionY = 0;
                    double motionZ = 0;
                    if (MinecraftClient.getInstance().options.forwardKey.isPressed()) {
                        motionX = Math.sin(Math.toRadians(player.getYaw()))*-1;
                        motionZ = Math.cos(Math.toRadians(player.getYaw()));
                    }
                    motionY += MinecraftClient.getInstance().options.jumpKey.isPressed() ? 0.6 : -0.04;
                    motionY += MinecraftClient.getInstance().options.sneakKey.isPressed() ? -0.6 : 0;
                    player.setVelocity(new Vec3d(motionX, motionY, motionZ));


                    //System.out.println(10%360);
                    //System.out.println((-10)%360);
            }
        });
    }

    private static boolean enabled;
    public KeyBinding keybind;

    public void onEnable() {


    }

    public void onDisable() {

    }

    public void onToggle() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal(this.getClass().getSimpleName() + " " + isEnabled()), false);
        MinecraftClient.getInstance().worldRenderer.reload();
    }

    public static boolean isEnabled() {
        return enabled;
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
