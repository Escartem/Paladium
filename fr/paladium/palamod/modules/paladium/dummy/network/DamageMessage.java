package fr.paladium.palamod.modules.paladium.dummy.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.dummy.EntityDummy;
import fr.paladium.palamod.modules.paladium.dummy.EntityFloatingNumber;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class DamageMessage implements IMessage {
  public float damage;
  
  public float shakeAmount;
  
  public int entityID;
  
  public int nrID;
  
  public DamageMessage() {}
  
  public DamageMessage(float damage, float shakeAmount, EntityDummy entity, EntityFloatingNumber e2) {
    this.damage = damage;
    this.shakeAmount = shakeAmount;
    this.entityID = entity.func_145782_y();
    this.nrID = (e2 != null) ? e2.func_145782_y() : -1;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.damage = buf.readFloat();
    this.shakeAmount = buf.readFloat();
    this.entityID = buf.readInt();
    this.nrID = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(this.damage);
    buf.writeFloat(this.shakeAmount);
    buf.writeInt(this.entityID);
    buf.writeInt(this.nrID);
  }
  
  public static class MessageHandlerClient implements IMessageHandler<DamageMessage, IMessage> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\network\DamageMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */