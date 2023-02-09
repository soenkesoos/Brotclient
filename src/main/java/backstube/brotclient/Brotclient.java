package backstube.brotclient;


import backstube.brotclient.mods.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;


public class Brotclient implements ModInitializer {


    public static MinecraftClient client;
    public ModManager modManager;
    public static ClientConnection connection;

    public Brotclient () {}


    @Override
    public void onInitialize() {
        client = MinecraftClient.getInstance();
        modManager = new ModManager();
        /*ClientPlayNetworking.registerGlobalReceiver(new Identifier("minecraft:0x1D"), (client, handler, buf, responseSender) -> {

        });*/
    }


}
