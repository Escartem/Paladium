package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class IntegerBufSerializer implements IBufSerializer<Integer> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeInt(buf, ((Integer)value).intValue());
  }
  
  public Integer read(ByteBuf buf, Class<?>... clazz) {
    return Integer.valueOf(PacketSerialUtils.readInt(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\IntegerBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */