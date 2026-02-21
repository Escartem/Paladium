package fr.paladium.asgard.packet.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.asgard.AsgardClient;
import fr.paladium.asgard.packet.S45PacketTitle;

public class TitleHandler implements IMessageHandler<S45PacketTitle, IMessage> {
  @SideOnly(Side.CLIENT)
  public static void handleTitle(S45PacketTitle packetIn) {
    AsgardClient client = AsgardClient.getInstance();
    S45PacketTitle.Type s45packettitle$type = packetIn.getType();
    String s = null;
    String s1 = null;
    String s2 = (packetIn.getMessage() != null) ? packetIn.getMessage().func_150254_d() : "";
    switch (s45packettitle$type) {
      case TITLE:
        s = s2;
        break;
      case SUBTITLE:
        s1 = s2;
        break;
      case ACTIONBAR:
        AsgardClient.getInstance().setRecordPlaying(s2, false);
        return;
      case RESET:
        client.displayTitle("", "", -1, -1, -1);
        client.setDefaultTitlesTimes();
        return;
    } 
    client.displayTitle(s, s1, packetIn.getFadeInTime(), packetIn.getDisplayTime(), packetIn
        .getFadeOutTime());
  }
  
  public IMessage onMessage(S45PacketTitle message, MessageContext ctx) {
    handleTitle(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\packet\handler\TitleHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */