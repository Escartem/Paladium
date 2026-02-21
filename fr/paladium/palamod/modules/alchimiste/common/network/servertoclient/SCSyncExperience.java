package fr.paladium.palamod.modules.alchimiste.common.network.servertoclient;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class SCSyncExperience implements IMessage {
  public float experience;
  
  public int experienceLevel;
  
  public int experienceTotal;
  
  public SCSyncExperience(float experience, int experienceLevel, int experienceTotal) {
    this.experience = experience;
    this.experienceLevel = experienceLevel;
    this.experienceTotal = experienceTotal;
  }
  
  public SCSyncExperience() {}
  
  public void fromBytes(ByteBuf buf) {
    this.experience = buf.readFloat();
    this.experienceLevel = buf.readInt();
    this.experienceTotal = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(this.experience);
    buf.writeInt(this.experienceLevel);
    buf.writeInt(this.experienceTotal);
  }
  
  public static class Handler implements IMessageHandler<SCSyncExperience, IMessage> {
    public IMessage onMessage(SCSyncExperience message, MessageContext ctx) {
      (Minecraft.func_71410_x()).field_71439_g.field_71106_cc = message.experience;
      (Minecraft.func_71410_x()).field_71439_g.field_71068_ca = message.experienceLevel;
      (Minecraft.func_71410_x()).field_71439_g.field_71067_cb = message.experienceTotal;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\network\servertoclient\SCSyncExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */