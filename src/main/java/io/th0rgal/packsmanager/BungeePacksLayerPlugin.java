package io.th0rgal.packsmanager;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.ProtocolConstants;

public class BungeePacksLayerPlugin extends Plugin {

    @Override
    public void onEnable() {
        Protocolize.protocolRegistration().registerPacket(
                SendPackPacket.MAPPING,
                Protocol.PLAY,
                PacketDirection.CLIENTBOUND,
                SendPackPacket.class);
        PacksListeners listeners = new PacksListeners();
        Protocolize.listenerProvider().registerListener(listeners);
        this.getProxy().getPluginManager().registerListener(this, listeners);
    }

}
