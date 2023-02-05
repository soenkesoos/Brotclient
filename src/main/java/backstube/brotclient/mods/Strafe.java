package backstube.brotclient.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

import static backstube.brotclient.Brotclient.mc;

public class Strafe {
    private static boolean enabled;
    public KeyBinding keybind;

    public void onEnable() {
        mc.player.airStrafingSpeed = 2000;
    }

    public void onDisable() {
        mc.player.airStrafingSpeed = 0.02f;
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
