package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class ByteBufSerializer implements IBufSerializer<Byte> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeByte(buf, ((Byte)value).byteValue());
  }
  
  public Byte read(ByteBuf buf, Class<?>... clazz) {
    return Byte.valueOf(PacketSerialUtils.readByte(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\ByteBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */