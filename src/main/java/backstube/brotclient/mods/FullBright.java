package backstube.brotclient.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

public class FullBright {
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
