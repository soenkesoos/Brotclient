package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

public class BunnyHop {

    private static boolean enabled;
    public KeyBinding keybind;
    private double speedFactor = 1.1;


    public BunnyHop() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(BunnyHop.isEnabled()) {
                if((MinecraftClient.getInstance().options.forwardKey.isPressed() ||
                    MinecraftClient.getInstance().options.backKey.isPressed() ||
                    MinecraftClient.getInstance().options.rightKey.isPressed() ||
                    MinecraftClient.getInstance().options.leftKey.isPressed()) &&
                    MinecraftClient.getInstance().options.sprintKey.isPressed()) {
                    if(MinecraftClient.getInstance().player.isOnGround()) {
                        MinecraftClient.getInstance().options.jumpKey.setPressed(true);
                    }
                    double velocityX = MinecraftClient.getInstance().player.getVelocity().getX() * speedFactor;
                    double velocityY = MinecraftClient.getInstance().player.getVelocity().getY() * speedFactor;
                    double velocityZ = MinecraftClient.getInstance().player.getVelocity().getZ() * speedFactor;
                    MinecraftClient.getInstance().player.setVelocity(velocityX, velocityY, velocityZ);
                }

            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!BunnyHop.isEnabled()) {return;}
            if(MinecraftClient.getInstance().player.isOnGround()) {
                MinecraftClient.getInstance().options.jumpKey.setPressed(false);
            }
        });
    }

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
