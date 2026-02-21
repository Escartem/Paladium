package fr.paladium.palamod.modules.packetreducer.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;

public class PReducerSettingsPacket implements IMessage {
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PReducerSettingsPacket))
      return false; 
    PReducerSettingsPacket other = (PReducerSettingsPacket)o;
    return !!other.canEqual(this);
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PReducerSettingsPacket;
  }
  
  public int hashCode() {
    int result = 1;
    return 1;
  }
  
  public String toString() {
    return "PReducerSettingsPacket()";
  }
  
  public void fromBytes(ByteBuf buf) {}
  
  @SideOnly(Side.CLIENT)
  public void toBytes(ByteBuf buf) {
    buf.writeInt((SoundCategory.values()).length);
    for (int i = 0; i < (SoundCategory.values()).length; i++) {
      boolean active = ((Minecraft.func_71410_x()).field_71474_y.func_151438_a(SoundCategory.values()[i]) > 0.0F);
      buf.writeInt(SoundCategory.values()[i].ordinal());
      buf.writeBoolean(active);
    } 
  }
  
  public static class Handler implements IMessageHandler<PReducerSettingsPacket, IMessage> {
    public IMessage onMessage(PReducerSettingsPacket message, MessageContext ctx) {
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\common\packets\PReducerSettingsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */