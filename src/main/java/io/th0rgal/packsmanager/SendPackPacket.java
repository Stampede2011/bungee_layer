package io.th0rgal.packsmanager;

import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.mapping.AbstractProtocolMapping;
import dev.simplix.protocolize.api.mapping.ProtocolIdMapping;
import dev.simplix.protocolize.api.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants;

import java.util.*;

import static dev.simplix.protocolize.api.util.ProtocolUtil.readString;
import static dev.simplix.protocolize.api.util.ProtocolVersions.*;
import static net.md_5.bungee.protocol.DefinedPacket.writeString;

public class SendPackPacket extends AbstractPacket {


    private String url;
    private String sha1;
    private boolean forced;
    private boolean hasPromptMessage;
    private String message;

    public final static List<ProtocolIdMapping> MAPPING = new ArrayList<>();

    static {
        MAPPING.add(AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_14, MINECRAFT_1_14_4, 0x3C));
        MAPPING.add(AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_15, MINECRAFT_1_15_2, 0x3C));
        MAPPING.add(AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_16, MINECRAFT_1_16_4, 0x3C));
        MAPPING.add(AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_17, MINECRAFT_1_17_1, 0x3C));
        MAPPING.add(AbstractProtocolMapping.rangedIdMapping(MINECRAFT_1_18, MINECRAFT_1_18, 0x3C));
    }

    public SendPackPacket() {

    }

    @Override
    public void read(ByteBuf buf, PacketDirection direction, int protocolVersion) {
        url = readString(buf);
        sha1 = readString(buf);
        forced = buf.readBoolean();
        hasPromptMessage = buf.readBoolean();
        if (hasPromptMessage)
            message = readString(buf);
    }

    @Override
    public void write(ByteBuf buf, PacketDirection direction, int protocolVersion) {
        writeString(url, buf);
        writeString(sha1, buf);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha1() {
        return sha1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPackPacket that = (SendPackPacket) o;
        return that.sha1.equals(sha1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha1);
    }

    @Override
    public String toString() {
        return sha1;
    }
}
