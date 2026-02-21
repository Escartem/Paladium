package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palajobs.core.quest.types.ItemGiveQuest;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<CSPalajobsGiveItemQuest, IMessage> {
  public IMessage onMessage(CSPalajobsGiveItemQuest message, MessageContext ctx) {
    if (ItemGiveQuest.performCheck((EntityPlayer)(ctx.getServerHandler()).field_147369_b, CSPalajobsGiveItemQuest.access$000(message)))
      (ctx.getServerHandler()).field_147369_b.func_71053_j(); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPalajobsGiveItemQuest$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */