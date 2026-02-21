package fr.paladium.palajobs.core.network.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<SCPacketSyncExtendedJobsPlayerData, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketSyncExtendedJobsPlayerData message, MessageContext ctx) {
    if (SCPacketSyncExtendedJobsPlayerData.access$000(message) == null)
      return null; 
    JobsPlayer eep = JobsPlayer.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
    eep.loadNBTData(SCPacketSyncExtendedJobsPlayerData.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketSyncExtendedJobsPlayerData$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */