package fr.paladium.palaforgeutils.lib.packet.serial;

import io.netty.buffer.ByteBuf;

public interface IBufSerializer<T> {
  void write(ByteBuf paramByteBuf, Object paramObject);
  
  T read(ByteBuf paramByteBuf, Class<?>... paramVarArgs);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\IBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */