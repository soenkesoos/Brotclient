package backstube.brotclient.mods;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static backstube.brotclient.Brotclient.client;

public class AntiHuman {

    public AntiHuman(){

    }

    public static double roundCoordinate(double n) {
        n = Math.round(n * 100) / 100d;  // Round to 1/100th
        return Math.nextAfter(n, n + Math.signum(n));
    }

    public static void onPositionPacket(Args args) {
        if (!AntiHuman.isEnabled()) {
            return;
        }
        double x = args.get(0);
        double y = args.get(1);
        double z = args.get(2);

        // Round to 100ths for "Anti-Human" check
        x = roundCoordinate(x);
        z = roundCoordinate(z);

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

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
