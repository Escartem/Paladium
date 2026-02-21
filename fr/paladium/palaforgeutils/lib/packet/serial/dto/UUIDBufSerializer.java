package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class UUIDBufSerializer implements IBufSerializer<UUID> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeString(buf, UUIDUtils.toString((UUID)value));
  }
  
  public UUID read(ByteBuf buf, Class<?>... clazz) {
    return UUIDUtils.parseUUID(PacketSerialUtils.readString(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\UUIDBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */