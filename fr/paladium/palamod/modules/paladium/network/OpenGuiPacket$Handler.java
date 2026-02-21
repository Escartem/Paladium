package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<OpenGuiPacket, IMessage> {
  public IMessage onMessage(OpenGuiPacket message, MessageContext ctx) {
    if (OpenGuiPacket.access$000(message)) {
      EntityPlayer player = PalaMod.proxy.getPlayerEntity(ctx);
      if (OpenGuiPacket.access$100(message)) {
        player.openGui(PalaMod.instance, OpenGuiPacket.access$200(message), player.field_70170_p, OpenGuiPacket.access$300(message), OpenGuiPacket.access$400(message), 
            OpenGuiPacket.access$500(message));
      } else {
        player.openGui(PalaMod.instance, OpenGuiPacket.access$200(message), player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
      } 
    } else {
      EntityPlayer player = PalaMod.proxy.getServerPlayerEntity(ctx);
      if (OpenGuiPacket.access$100(message)) {
        player.openGui(PalaMod.instance, OpenGuiPacket.access$200(message), player.field_70170_p, OpenGuiPacket.access$300(message), OpenGuiPacket.access$400(message), 
            OpenGuiPacket.access$500(message));
      } else {
        player.openGui(PalaMod.instance, OpenGuiPacket.access$200(message), player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\OpenGuiPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */