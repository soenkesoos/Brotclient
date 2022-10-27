package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

import static backstube.brotclient.Brotclient.mc;

public class AntiHuman {

    public AntiHuman(){
        /*ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if(AntiHuman.enabled) {
                mc.player.setPos(Math.round(mc.player.getX()*1000d)/1000d, mc.player.getY(), Math.round(mc.player.getZ()*1000d)/1000d);
            }
        });*/
    }

    private static boolean enabled;
    public KeyBinding keybind;

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onToggle() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal(this.getClass().getSimpleName() + " " + isEnabled()), false);
    }

    public static boolean isEnabled() {return enabled;}




    public void toggle() {
        enabled = !enabled;
        onToggle();
        if(enabled) {onEnable();}
        else {onDisable();}
    }
}
