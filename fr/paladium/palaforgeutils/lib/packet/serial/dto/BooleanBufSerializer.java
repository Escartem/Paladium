package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class BooleanBufSerializer implements IBufSerializer<Boolean> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeBoolean(buf, ((Boolean)value).booleanValue());
  }
  
  public Boolean read(ByteBuf buf, Class<?>... clazz) {
    return Boolean.valueOf(PacketSerialUtils.readBoolean(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\BooleanBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */