package fr.paladium.palaforgeutils.lib.packet.utils;

import io.netty.buffer.ByteBuf;

public interface IBufSerializable {
  void write(ByteBuf paramByteBuf);
  
  void read(ByteBuf paramByteBuf);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packe\\utils\IBufSerializable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */