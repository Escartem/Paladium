package fr.paladium.palamod.modules.palaboss.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public class MessageSyncTobaltToClient implements IMessage {
  public int entityID;
  
  public boolean inCharge;
  
  public boolean isStunt;
  
  public MessageSyncTobaltToClient() {}
  
  public MessageSyncTobaltToClient(int entityID, boolean inCharge, boolean isStunt) {
    this.entityID = entityID;
    this.inCharge = inCharge;
    this.isStunt = isStunt;
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.entityID = ByteBufUtils.readVarInt(buf, 4);
    this.inCharge = buf.readBoolean();
    this.isStunt = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeVarInt(buf, this.entityID, 4);
    buf.writeBoolean(this.inCharge);
    buf.writeBoolean(this.isStunt);
  }
  
  public static class Handler implements IMessageHandler<MessageSyncTobaltToClient, IMessage> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\network\MessageSyncTobaltToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */