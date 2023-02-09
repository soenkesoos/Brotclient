package backstube.brotclient;

import backstube.brotclient.mixins.ClientConnectionAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

import static backstube.brotclient.Brotclient.client;

public class PacketHelper {

    public static void sendPosition(Vec3d pos) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        PlayerMoveC2SPacket.PositionAndOnGround posPacket = new PlayerMoveC2SPacket.PositionAndOnGround(pos.x, pos.y, pos.z, false);
        sendImmediately(posPacket);
    }

    public static void sendImmediately(Packet packet) {
        ((ClientConnectionAccessor) client.getNetworkHandler().getConnection())._sendImmediately(packet, null);
    }
}