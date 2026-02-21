package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.util.HashSet;
import java.util.Set;

public class HashSetBufSerializer implements IBufSerializer<HashSet<?>> {
  public void write(ByteBuf buf, Object value) {
    HashSet<?> set = (HashSet)value;
    PacketSerialUtils.writeSet(buf, set);
  }
  
  public HashSet<?> read(ByteBuf buf, Class<?>... clazz) {
    Set<?> list = PacketSerialUtils.readSet(buf, clazz[0]);
    return new HashSet(list);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\HashSetBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */