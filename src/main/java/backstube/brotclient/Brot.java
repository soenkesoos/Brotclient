package backstube.brotclient;

import net.fabricmc.api.ModInitializer;

public class Brot implements ModInitializer {

    public static Brotclient instance;

    @Override
    public void onInitialize() {
        instance = new Brotclient();
    }
}
