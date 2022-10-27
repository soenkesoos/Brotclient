package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class NoFall {
    private static boolean enabled;
    public KeyBinding keybind;


    public NoFall() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(NoFall.isEnabled() && MinecraftClient.getInstance().player.getVelocity().y <= -0.5) {
                MinecraftClient.getInstance().player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
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
