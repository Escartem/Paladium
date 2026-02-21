package fr.paladium.palajobs.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.client.ui.lvltokens.UILvlToken;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<SCPacketUpdateLvlTokenGui, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketUpdateLvlTokenGui message, MessageContext ctx) {
    if ((Minecraft.func_71410_x()).field_71462_r instanceof UILvlToken) {
      UILvlToken ui = (UILvlToken)(Minecraft.func_71410_x()).field_71462_r;
      ui.setLastdiscovery(System.currentTimeMillis());
      ui.setData(SCPacketUpdateLvlTokenGui.access$000(message));
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketUpdateLvlTokenGui$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */