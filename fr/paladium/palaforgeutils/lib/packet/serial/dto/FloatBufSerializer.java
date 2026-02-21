package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class FloatBufSerializer implements IBufSerializer<Float> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeFloat(buf, ((Float)value).floatValue());
  }
  
  public Float read(ByteBuf buf, Class<?>... clazz) {
    return Float.valueOf(PacketSerialUtils.readFloat(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\FloatBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */