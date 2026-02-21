package fr.paladium.palajobs.core.network.packet.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class SCPacketSyncExtendedJobsPlayerData implements IMessage {
  private NBTTagCompound nbt;
  
  public SCPacketSyncExtendedJobsPlayerData(NBTTagCompound nbt) {
    this.nbt = nbt;
  }
  
  public SCPacketSyncExtendedJobsPlayerData() {}
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeTag(buf, this.nbt);
  }
  
  public void fromBytes(ByteBuf buf) {
    if (!buf.isReadable())
      return; 
    this.nbt = ByteBufUtils.readTag(buf);
  }
  
  public static class Handler implements IMessageHandler<SCPacketSyncExtendedJobsPlayerData, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketSyncExtendedJobsPlayerData message, MessageContext ctx) {
      if (message.nbt == null)
        return null; 
      JobsPlayer eep = JobsPlayer.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
      eep.loadNBTData(message.nbt);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\packet\server\SCPacketSyncExtendedJobsPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */