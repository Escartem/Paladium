package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;

public class CharacterBufSerializer implements IBufSerializer<Character> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeChar(buf, ((Character)value).charValue());
  }
  
  public Character read(ByteBuf buf, Class<?>... clazz) {
    return Character.valueOf(PacketSerialUtils.readChar(buf));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\CharacterBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */