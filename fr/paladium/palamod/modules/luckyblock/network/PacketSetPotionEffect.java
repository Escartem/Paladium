package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PacketSetPotionEffect implements IMessage {
  public String uuid;
  
  public int id;
  
  public int duration;
  
  public int power;
  
  public boolean active;
  
  public boolean ambiant;
  
  public PacketSetPotionEffect() {}
  
  public PacketSetPotionEffect(String uuid, int id, int duration, int power, boolean active, boolean ambiant) {
    this.uuid = uuid;
    this.id = id;
    this.duration = duration;
    this.power = power;
    this.active = active;
    this.ambiant = ambiant;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuid = ByteBufUtils.readUTF8String(buf);
    this.id = buf.readInt();
    this.duration = buf.readInt();
    this.power = buf.readInt();
    this.active = buf.readBoolean();
    this.ambiant = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.uuid);
    buf.writeInt(this.id);
    buf.writeInt(this.duration);
    buf.writeInt(this.power);
    buf.writeBoolean(this.active);
    buf.writeBoolean(this.ambiant);
  }
  
  public static class Handler implements IMessageHandler<PacketSetPotionEffect, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketSetPotionEffect message, MessageContext ctx) {
      if (message.active) {
        Minecraft mc = Minecraft.func_71410_x();
        if (FastUUID.toString((Entity)mc.field_71439_g).equalsIgnoreCase(message.uuid))
          return null; 
        EntityPlayer player = null;
        for (Object obj : mc.field_71439_g.field_70170_p.field_73010_i) {
          EntityPlayer pl = (EntityPlayer)obj;
          if (FastUUID.toString((Entity)pl).equalsIgnoreCase(message.uuid)) {
            player = pl;
            break;
          } 
        } 
        if (player != null)
          player.func_70690_d(new PotionEffect(message.id, message.duration, message.power, message.ambiant)); 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSetPotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */