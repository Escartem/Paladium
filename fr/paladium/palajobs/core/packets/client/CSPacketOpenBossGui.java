package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.packets.server.SCPacketOpenBossGui;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import fr.paladium.palajobs.server.managers.JobsManager;
import io.netty.buffer.ByteBuf;

public class CSPacketOpenBossGui implements IMessage {
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<CSPacketOpenBossGui, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketOpenBossGui message, MessageContext ctx) {
      BossData bossData = JobsManager.getInstance().getBossData();
      if (bossData == null)
        return null; 
      return (IMessage)new SCPacketOpenBossGui(bossData);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketOpenBossGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */