package fr.paladium.achievement.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SCSyncExtendedAchievementPlayerData implements IMessage {
  private NBTTagCompound nbt;
  
  public SCSyncExtendedAchievementPlayerData(NBTTagCompound nbt) {
    this.nbt = nbt;
  }
  
  public SCSyncExtendedAchievementPlayerData() {}
  
  public void toBytes(ByteBuf buf) {
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      buffer.func_150786_a(this.nbt);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    if (!buf.isReadable())
      return; 
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      this.nbt = buffer.func_150793_b();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static class Handler implements IMessageHandler<SCSyncExtendedAchievementPlayerData, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCSyncExtendedAchievementPlayerData message, MessageContext ctx) {
      if (message.nbt == null)
        return null; 
      ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
      eep.loadNBTData(message.nbt);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\packets\server\SCSyncExtendedAchievementPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */