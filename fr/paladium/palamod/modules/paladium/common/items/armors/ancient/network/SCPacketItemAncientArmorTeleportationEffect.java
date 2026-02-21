package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.client.events.ClientItemAncientArmorRenderListener;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

@Packet(side = Side.CLIENT)
public class SCPacketItemAncientArmorTeleportationEffect implements IMessage {
  private int entityId;
  
  public SCPacketItemAncientArmorTeleportationEffect() {}
  
  public SCPacketItemAncientArmorTeleportationEffect(int entityId) {
    this.entityId = entityId;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.entityId = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.entityId);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketItemAncientArmorTeleportationEffect, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketItemAncientArmorTeleportationEffect message, MessageContext ctx) {
      if ((Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(message.entityId) != null)
        ClientItemAncientArmorRenderListener.getTeleportationEffectPlayers().put(Integer.valueOf(message.entityId), Long.valueOf(System.currentTimeMillis() + 100L)); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\network\SCPacketItemAncientArmorTeleportationEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */