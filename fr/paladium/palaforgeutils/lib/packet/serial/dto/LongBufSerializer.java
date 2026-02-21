package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class LongBufSerializer implements IBufSerializer<Long> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeLong(buf, ((Long)value).longValue());
  }
  
  public Long read(ByteBuf buf, Class<?>... clazz) {
    return Long.valueOf(PacketSerialUtils.readLong(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\LongBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */