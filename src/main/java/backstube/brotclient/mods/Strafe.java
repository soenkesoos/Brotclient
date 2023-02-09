package backstube.brotclient.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

import static backstube.brotclient.Brotclient.client;

public class Strafe {
    private static boolean enabled;
    public KeyBinding keybind;

    public void onEnable() {
        client.player.airStrafingSpeed = 2000;
    }

    public void onDisable() {
        client.player.airStrafingSpeed = 0.02f;
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
