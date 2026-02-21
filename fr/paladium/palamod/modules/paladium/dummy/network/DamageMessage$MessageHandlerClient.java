package fr.paladium.palamod.modules.paladium.dummy.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.dummy.EntityDummy;
import fr.paladium.palamod.modules.paladium.dummy.EntityFloatingNumber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class MessageHandlerClient implements IMessageHandler<DamageMessage, IMessage> {
  public DamageMessage onMessage(DamageMessage message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityID);
    if (entity != null && entity instanceof EntityDummy) {
      EntityDummy dummy = (EntityDummy)entity;
      dummy.shake = message.shakeAmount;
      dummy.func_94058_c(String.valueOf(message.damage));
    } 
    if (message.nrID > 0) {
      entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.nrID);
      if (entity != null && entity instanceof EntityFloatingNumber)
        ((EntityFloatingNumber)entity).reSet(message.damage); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\network\DamageMessage$MessageHandlerClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */