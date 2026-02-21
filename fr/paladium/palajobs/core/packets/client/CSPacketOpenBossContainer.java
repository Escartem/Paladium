package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import fr.paladium.palajobs.server.managers.JobsManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketOpenBossContainer implements IMessage {
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<CSPacketOpenBossContainer, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketOpenBossContainer message, MessageContext ctx) {
      BossData bossData = JobsManager.getInstance().getBossData();
      if (bossData == null)
        return null; 
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      entityPlayerMP.openGui(PalaJobs.instance, 0, ((EntityPlayer)entityPlayerMP).field_70170_p, (int)((EntityPlayer)entityPlayerMP).field_70165_t, (int)((EntityPlayer)entityPlayerMP).field_70163_u, (int)((EntityPlayer)entityPlayerMP).field_70161_v);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketOpenBossContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */