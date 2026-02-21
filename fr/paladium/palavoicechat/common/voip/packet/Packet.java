package fr.paladium.palavoicechat.common.voip.packet;

import io.netty.buffer.ByteBuf;

public interface Packet<T extends Packet<?>> {
  T fromBytes(ByteBuf paramByteBuf);
  
  void toBytes(ByteBuf paramByteBuf);
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\Packet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */