package backstube.brotclient.mods;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

import static backstube.brotclient.Brotclient.client;

public class FastBreak {

    public FastBreak(){}

    private static boolean enabled = true;
    public KeyBinding keybind;

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onToggle() {
        client.player.sendMessage(Text.literal(this.getClass().getSimpleName() + " " + isEnabled()), false);
    }

    public static boolean isEnabled() {return enabled;}

    public void toggle() {
        enabled = !enabled;
        onToggle();
        if(enabled) {onEnable();}
        else {onDisable();}
    }
}
