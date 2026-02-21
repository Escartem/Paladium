package fr.paladium.palamod.modules.paladium.dummy.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class MessageHandlerClient implements IMessageHandler<SyncEquipmentMessage, IMessage> {
  public SyncEquipmentMessage onMessage(SyncEquipmentMessage message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(SyncEquipmentMessage.access$000(message));
    if (entity != null && entity instanceof fr.paladium.palamod.modules.paladium.dummy.EntityDummy)
      entity.func_70062_b(SyncEquipmentMessage.access$100(message), SyncEquipmentMessage.access$200(message)); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\network\SyncEquipmentMessage$MessageHandlerClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */