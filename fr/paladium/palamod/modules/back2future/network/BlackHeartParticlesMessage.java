package fr.paladium.palamod.modules.back2future.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class BlackHeartParticlesMessage implements IMessage {
  public double x;
  
  public double y;
  
  public double z;
  
  public BlackHeartParticlesMessage() {}
  
  public BlackHeartParticlesMessage(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\network\BlackHeartParticlesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */