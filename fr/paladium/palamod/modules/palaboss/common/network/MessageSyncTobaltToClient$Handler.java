package fr.paladium.palamod.modules.palaboss.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<MessageSyncTobaltToClient, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(MessageSyncTobaltToClient message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityID);
    if (entity instanceof EntityTobalt) {
      EntityTobalt tobalt = (EntityTobalt)entity;
      tobalt.syncTobaltData(message);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\network\MessageSyncTobaltToClient$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */