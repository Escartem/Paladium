package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.utils.AskManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Handler implements IMessageHandler<PacketAskAction, IMessage> {
  public IMessage onMessage(PacketAskAction message, MessageContext ctx) {
    EntityPlayer player = AskManager.getAsker((EntityPlayer)(ctx.getServerHandler()).field_147369_b, PacketAskAction.access$000(message));
    if (player != null)
      player.func_145747_a((IChatComponent)new ChatComponentText("§cLe joueur §e" + 
            (ctx.getServerHandler()).field_147369_b.func_70005_c_() + " §ca §b" + (
            (PacketAskAction.access$100(message) == PacketAskAction.AskAction.CLOSE) ? "fermé la fenêtre" : (
            (PacketAskAction.access$100(message) == PacketAskAction.AskAction.DOWNLOAD) ? "cliqué sur télécharger" : (
            (PacketAskAction.access$100(message) == PacketAskAction.AskAction.JOIN) ? "cliqué sur rejoindre" : "error"))))); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketAskAction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */