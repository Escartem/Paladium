package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.utils.LengendaryStoneFx;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;

@Packet(side = Side.CLIENT)
public class SCPacketItemAncientArmorInvisibilityEffect implements IMessage {
  private double x;
  
  private double y;
  
  private double z;
  
  public SCPacketItemAncientArmorInvisibilityEffect() {}
  
  public SCPacketItemAncientArmorInvisibilityEffect(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketItemAncientArmorInvisibilityEffect, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketItemAncientArmorInvisibilityEffect message, MessageContext ctx) {
      LengendaryStoneFx.execute(message.x, message.y, message.z, LegendaryStone.Effect.INVISIBILITY.getRed(), LegendaryStone.Effect.INVISIBILITY.getGreen(), LegendaryStone.Effect.INVISIBILITY.getBlue());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\network\SCPacketItemAncientArmorInvisibilityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */