package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class OfflinePlayerBufSerializer implements IBufSerializer<OfflinePlayer> {
  public void write(ByteBuf buf, Object value) {
    OfflinePlayer player = (OfflinePlayer)value;
    PacketSerialUtils.writeString(buf, player.getUuidString());
    PacketSerialUtils.writeString(buf, player.getName());
  }
  
  public OfflinePlayer read(ByteBuf buf, Class<?>... clazz) {
    UUID uuid = UUIDUtils.from(PacketSerialUtils.readString(buf));
    String name = PacketSerialUtils.readString(buf);
    return OfflinePlayer.of(name, uuid).update();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\OfflinePlayerBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */