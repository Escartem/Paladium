package fr.paladium.achievement.core.packets.server.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.achievement.client.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;

public class SCAchievementHome implements IMessage {
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCAchievementHome, IMessage> {
    public IMessage onMessage(SCAchievementHome message, MessageContext ctx) {
      ClientProxy.getInstance().openAchievement();
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\packets\serve\\ui\SCAchievementHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */