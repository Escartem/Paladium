package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityChatDetector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<PacketChatDetector, IMessage> {
  public IMessage onMessage(PacketChatDetector message, MessageContext ctx) {
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    if (!((EntityPlayer)entityPlayerMP).field_70170_p.func_72899_e(message.x, message.y, message.z))
      return null; 
    TileEntityChatDetector detector = (TileEntityChatDetector)((EntityPlayer)entityPlayerMP).field_70170_p.func_147438_o(message.x, message.y, message.z);
    if (detector != null)
      detector.setWord(message.player); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketChatDetector$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */