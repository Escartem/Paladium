package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class Handler implements IMessageHandler<PacketSetPotionEffect, IMessage> {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSetPotionEffect$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */