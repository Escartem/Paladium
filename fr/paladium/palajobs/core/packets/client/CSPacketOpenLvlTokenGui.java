package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.packets.server.SCPacketOpenLvlTokenGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class CSPacketOpenLvlTokenGui implements IMessage {
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<CSPacketOpenLvlTokenGui, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketOpenLvlTokenGui message, MessageContext ctx) {
      JobsPlayer eep = JobsPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b);
      if (eep == null)
        return null; 
      return (IMessage)new SCPacketOpenLvlTokenGui(eep.getLvlTokens());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketOpenLvlTokenGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */