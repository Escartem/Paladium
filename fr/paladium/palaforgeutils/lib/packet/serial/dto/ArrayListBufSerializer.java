package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collection;

public class ArrayListBufSerializer implements IBufSerializer<ArrayList<?>> {
  public void write(ByteBuf buf, Object value) {
    ArrayList<?> list = (ArrayList)value;
    PacketSerialUtils.writeList(buf, list);
  }
  
  public ArrayList<?> read(ByteBuf buf, Class<?>... clazz) {
    Collection<?> list = PacketSerialUtils.readList(buf, clazz[0]);
    return new ArrayList(list);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\ArrayListBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */