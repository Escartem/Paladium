package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class DoubleBufSerializer implements IBufSerializer<Double> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeDouble(buf, ((Double)value).doubleValue());
  }
  
  public Double read(ByteBuf buf, Class<?>... clazz) {
    return Double.valueOf(PacketSerialUtils.readDouble(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\DoubleBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */