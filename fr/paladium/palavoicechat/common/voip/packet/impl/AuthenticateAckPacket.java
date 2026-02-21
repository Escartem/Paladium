package fr.paladium.palavoicechat.common.voip.packet.impl;

import fr.paladium.palavoicechat.common.voip.packet.Packet;
import io.netty.buffer.ByteBuf;

public class AuthenticateAckPacket implements Packet<AuthenticateAckPacket> {
  public AuthenticateAckPacket fromBytes(ByteBuf buf) {
    return new AuthenticateAckPacket();
  }
  
  public void toBytes(ByteBuf buf) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\packet\impl\AuthenticateAckPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */