package backstube.brotclient.mods;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModManager {

    public ESP esp;
    public Xray xray;
    public Tracer tracers;
    public AntiHuman antiHuman;
    public Boatfly boatFly;
    public Step step;
    public NoFall noFall;
    public FullBright fullBright;
    public BunnyHop bunnyHop;
    public Fly fly;

    public Strafe strafe;



    public ModManager() {
        esp = new ESP();
        xray = new Xray();
        tracers = new Tracer();
        antiHuman = new AntiHuman();
        boatFly = new Boatfly();
        step = new Step();
        noFall = new NoFall();
        fullBright = new FullBright();
        bunnyHop = new BunnyHop();
        fly = new Fly();
        strafe = new Strafe();


        esp.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(esp.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        xray.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(xray.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        tracers.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(tracers.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        antiHuman.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(antiHuman.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        boatFly.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(boatFly.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        boatFly.key2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(boatFly.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, "Brotclient"));
        step.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(step.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        noFall.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(noFall.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        fullBright.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(fullBright.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        bunnyHop.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(bunnyHop.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Brotclient"));
        fly.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(fly.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "Brotclient"));
        strafe.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(strafe.getClass().getSimpleName(), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Brotclient"));


        //the functions that are supposed to be executed
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (esp.keybind.wasPressed()) {
                esp.toggle();
            }
            if (xray.keybind.wasPressed()) {
                xray.toggle();
            }
            if (tracers.keybind.wasPressed()) {
                tracers.toggle();
            }
            if (antiHuman.keybind.wasPressed()) {
                antiHuman.toggle();
            }
            if (boatFly.keybind.wasPressed()) {
                boatFly.toggle();
            }
            if (step.keybind.wasPressed()) {
                step.toggle();
            }
            if (noFall.keybind.wasPressed()) {
                noFall.toggle();
            }
            if (fullBright.keybind.wasPressed()) {
                fullBright.toggle();
            }
            if (bunnyHop.keybind.wasPressed()) {
                bunnyHop.toggle();
            }
            if (fly.keybind.wasPressed()) {
                fly.toggle();
            }
            if (strafe.keybind.wasPressed()) {
                strafe.toggle();
            }

        });
    }
}
