package fr.paladium.achievement.core.helios.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.achievement.core.helios.ModuleAchievement;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;

public class SCPacketAchievement implements IMessage {
  private String text;
  
  private long duration;
  
  public SCPacketAchievement() {}
  
  public SCPacketAchievement(String text, long duration) {
    this.text = text;
    this.duration = duration;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.text = ByteBufUtils.readUTF8String(buf);
    this.duration = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.text);
    buf.writeLong(this.duration);
  }
  
  public static class Handler implements IMessageHandler<SCPacketAchievement, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketAchievement message, MessageContext ctx) {
      EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
      ((EntityPlayer)entityClientPlayerMP).field_70170_p.func_72980_b(((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u, ((EntityPlayer)entityClientPlayerMP).field_70161_v, "achievement:achievement.unlock", 1.0F, 1.0F, false);
      ModuleAchievement.getInstance().setText(message.text);
      ModuleAchievement.getInstance().setEnd(System.currentTimeMillis() + message.duration);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\helios\packets\SCPacketAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */