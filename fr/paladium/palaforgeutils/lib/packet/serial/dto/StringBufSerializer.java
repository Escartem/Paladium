package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class StringBufSerializer implements IBufSerializer<String> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeString(buf, (String)value);
  }
  
  public String read(ByteBuf buf, Class<?>... clazz) {
    return PacketSerialUtils.readString(buf);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\StringBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */