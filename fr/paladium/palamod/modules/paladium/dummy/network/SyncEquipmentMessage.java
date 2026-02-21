package fr.paladium.palamod.modules.paladium.dummy.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class SyncEquipmentMessage implements IMessage {
  private int entityID;
  
  private int slotId;
  
  private ItemStack itemstack;
  
  public SyncEquipmentMessage() {}
  
  public SyncEquipmentMessage(int entityId, int slotId, ItemStack itemstack) {
    this.entityID = entityId;
    this.slotId = slotId;
    this.itemstack = (itemstack == null) ? null : itemstack.func_77946_l();
  }
  
  public void fromBytes(ByteBuf buf) {
    this.entityID = buf.readInt();
    this.slotId = buf.readInt();
    this.itemstack = ByteBufUtils.readItemStack(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.entityID);
    buf.writeInt(this.slotId);
    ByteBufUtils.writeItemStack(buf, this.itemstack);
  }
  
  public static class MessageHandlerClient implements IMessageHandler<SyncEquipmentMessage, IMessage> {
    public SyncEquipmentMessage onMessage(SyncEquipmentMessage message, MessageContext ctx) {
      Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(message.entityID);
      if (entity != null && entity instanceof fr.paladium.palamod.modules.paladium.dummy.EntityDummy)
        entity.func_70062_b(message.slotId, message.itemstack); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\network\SyncEquipmentMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */