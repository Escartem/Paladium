package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

public class HashMapBufSerializer implements IBufSerializer<HashMap<?, ?>> {
  public void write(ByteBuf buf, Object value) {
    HashMap<?, ?> map = (HashMap<?, ?>)value;
    PacketSerialUtils.writeMap(buf, map);
  }
  
  public HashMap<?, ?> read(ByteBuf buf, Class<?>... clazz) {
    Map<?, ?> map = PacketSerialUtils.readMap(buf, clazz[0], clazz[1]);
    return new HashMap<>(map);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\HashMapBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */