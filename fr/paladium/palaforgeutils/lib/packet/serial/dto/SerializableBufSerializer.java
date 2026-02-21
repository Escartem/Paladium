package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import io.netty.buffer.ByteBuf;

public class SerializableBufSerializer implements IBufSerializer<IBufSerializable> {
  public void write(ByteBuf buf, Object value) {
    IBufSerializable serializable = (IBufSerializable)value;
    serializable.write(buf);
  }
  
  public IBufSerializable read(ByteBuf buf, Class<?>... clazz) {
    try {
      IBufSerializable serializable = (IBufSerializable)clazz[0].newInstance();
      serializable.read(buf);
      return serializable;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\SerializableBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */