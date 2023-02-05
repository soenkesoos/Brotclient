package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;


public class Boatfly {

    public Boatfly() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(Boatfly.enabled) {
                if(MinecraftClient.getInstance().player.hasVehicle()){
                    Entity vehicle = MinecraftClient.getInstance().player.getVehicle();
                    Vec3d velocity = vehicle.getVelocity();

                    double motionX = vehicle.getVelocity().x/1.2;
                    double motionY = 0;
                    double motionZ = vehicle.getVelocity().z/1.2;
                    if (MinecraftClient.getInstance().options.forwardKey.isPressed() ||
                            MinecraftClient.getInstance().options.backKey.isPressed() ||
                            MinecraftClient.getInstance().options.rightKey.isPressed()||
                            MinecraftClient.getInstance().options.leftKey.isPressed()) {
                        motionX = vehicle.getVelocity().x * 1.1;
                        motionZ = vehicle.getVelocity().z * 1.1;
                    }
                    motionY = MinecraftClient.getInstance().options.jumpKey.isPressed() ? 0.3 : 0;
                    motionY += key2.isPressed() ? -0.3 : 0;
                    vehicle.setVelocity(new Vec3d(motionX, motionY, motionZ));
                    System.out.println(velocity.x);
                }
            }
        });
    }


    private static boolean enabled;
    public KeyBinding keybind;
    public KeyBinding key2;

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onToggle() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal(this.getClass().getSimpleName() + " " + isEnabled()), false);
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
