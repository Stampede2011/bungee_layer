package io.th0rgal.packsmanager;

import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacksListeners extends AbstractPacketListener<SendPackPacket> implements Listener {

    private final Map<UUID, String> map = new HashMap<>();

    public PacksListeners() {
        super(SendPackPacket.class, Direction.DOWNSTREAM, 1);
    }

    @Override
    public void packetReceive(PacketReceiveEvent<SendPackPacket> event) {
        final SendPackPacket packet = event.packet();
        final UUID uuid = event.player().uniqueId();
        if (map.containsKey(uuid) && map.get(uuid).equals(packet.getSha1())) {
            event.cancelled(true);
            return;
        }
        map.put(uuid, packet.getSha1());
    }

    @EventHandler
    public void onDisconnect(final PlayerDisconnectEvent event) {
        map.remove(event.getPlayer().getUniqueId());
    }

    @Override
    public void packetSend(PacketSendEvent<SendPackPacket> packetSendEvent) {

    }

}