package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.utils.AskManager;
import fr.paladium.palamod.modules.paladium.utils.AskType;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PacketAskAction implements IMessage {
  private AskType type;
  
  private AskAction action;
  
  public PacketAskAction() {}
  
  public PacketAskAction(AskType type, AskAction action) {
    this.type = type;
    this.action = action;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.type = AskType.values()[buf.readInt()];
    this.action = AskAction.values()[buf.readInt()];
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.type.ordinal());
    buf.writeInt(this.action.ordinal());
  }
  
  public static class Handler implements IMessageHandler<PacketAskAction, IMessage> {
    public IMessage onMessage(PacketAskAction message, MessageContext ctx) {
      EntityPlayer player = AskManager.getAsker((EntityPlayer)(ctx.getServerHandler()).field_147369_b, message.type);
      if (player != null)
        player.func_145747_a((IChatComponent)new ChatComponentText("§cLe joueur §e" + 
              (ctx.getServerHandler()).field_147369_b.func_70005_c_() + " §ca §b" + (
              (message.action == PacketAskAction.AskAction.CLOSE) ? "fermé la fenêtre" : (
              (message.action == PacketAskAction.AskAction.DOWNLOAD) ? "cliqué sur télécharger" : (
              (message.action == PacketAskAction.AskAction.JOIN) ? "cliqué sur rejoindre" : "error"))))); 
      return null;
    }
  }
  
  public enum AskAction {
    CLOSE, JOIN, DOWNLOAD;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketAskAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */