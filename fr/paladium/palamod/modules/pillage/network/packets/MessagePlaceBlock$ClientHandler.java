package fr.paladium.palamod.modules.pillage.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class ClientHandler implements IMessageHandler<MessagePlaceBlock, IMessage> {
  public IMessage onMessage(MessagePlaceBlock message, MessageContext ctx) {
    switch (message.id) {
      case 0:
        (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147468_f(message.x, message.y, message.z);
        break;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\packets\MessagePlaceBlock$ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */