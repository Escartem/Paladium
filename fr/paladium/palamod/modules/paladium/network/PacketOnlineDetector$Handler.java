package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityOnlineDetector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<PacketOnlineDetector, IMessage> {
  public IMessage onMessage(PacketOnlineDetector message, MessageContext ctx) {
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    if (!((EntityPlayer)entityPlayerMP).field_70170_p.func_72899_e(message.x, message.y, message.z))
      return null; 
    TileEntityOnlineDetector detector = (TileEntityOnlineDetector)((EntityPlayer)entityPlayerMP).field_70170_p.func_147438_o(message.x, message.y, message.z);
    if (detector != null)
      detector.setName(message.player); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketOnlineDetector$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */