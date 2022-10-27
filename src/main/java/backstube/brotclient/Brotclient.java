package backstube.brotclient;


import backstube.brotclient.mods.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;


public class Brotclient implements ModInitializer {


    public static MinecraftClient mc;
    public ModManager modManager;

    public Brotclient () {}


    @Override
    public void onInitialize() {
        mc = MinecraftClient.getInstance();
        modManager = new ModManager();
    }


}
