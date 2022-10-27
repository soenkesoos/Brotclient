package backstube.brotclient.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Perspective;
import net.minecraft.text.Text;

public class Step {

    private static boolean enabled;
    public KeyBinding keybind;
    public float stepHeight = 1.1f;
    public final float normalStepHeight = 0.6f;

    public void onEnable() {
        MinecraftClient.getInstance().player.stepHeight = this.stepHeight;

    }

    public void onDisable() {
        MinecraftClient.getInstance().player.stepHeight = this.normalStepHeight;
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
