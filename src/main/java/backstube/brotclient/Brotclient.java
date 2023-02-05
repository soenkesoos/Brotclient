package backstube.brotclient;


import backstube.brotclient.mods.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;


public class Brotclient implements ModInitializer {


    public static MinecraftClient mc;
    public ModManager modManager;
    public static ClientConnection connection;

    public Brotclient () {}


    @Override
    public void onInitialize() {
        mc = MinecraftClient.getInstance();
        modManager = new ModManager();
        /*ClientPlayNetworking.registerGlobalReceiver(new Identifier("minecraft:0x1D"), (client, handler, buf, responseSender) -> {

        });*/
    }


}
