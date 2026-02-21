package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.AffaireEstDansLeSac;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSDealInBagPacket implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<CSDealInBagPacket, IMessage> {
    public IMessage onMessage(CSDealInBagPacket message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      AffaireEstDansLeSac.getInstance().giveReward(player);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\CSDealInBagPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */