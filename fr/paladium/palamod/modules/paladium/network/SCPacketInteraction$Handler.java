package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import net.minecraft.client.Minecraft;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketInteraction, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketInteraction message, MessageContext ctx) {
    if (SCPacketInteraction.access$000(message))
      return null; 
    switch (SCPacketInteraction.null.$SwitchMap$fr$paladium$palamod$modules$paladium$network$CSPacketInteraction$InteractionType[SCPacketInteraction.access$100(message).ordinal()]) {
      case 1:
        (Minecraft.func_71410_x()).field_71442_b.func_78767_c();
        break;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\SCPacketInteraction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */