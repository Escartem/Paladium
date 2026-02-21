package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class EnumBufSerializer implements IBufSerializer<Enum<?>> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeEnum(buf, (Enum)value);
  }
  
  public Enum<?> read(ByteBuf buf, Class<?>... clazz) {
    return (Enum)PacketSerialUtils.readEnum(buf, clazz[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\EnumBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */