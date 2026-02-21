package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.packets.server.SCPacketUpdateLvlTokenGui;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.server.managers.JobsManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class CSPacketUseLvlToken implements IMessage {
  private JobType job;
  
  private String lvlTokenUUID;
  
  public CSPacketUseLvlToken(JobType job, String uuid) {
    this.job = job;
    this.lvlTokenUUID = uuid;
  }
  
  public CSPacketUseLvlToken() {}
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.job.ordinal());
    ByteBufUtils.writeUTF8String(buf, this.lvlTokenUUID);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.job = JobType.values()[buf.readInt()];
    this.lvlTokenUUID = ByteBufUtils.readUTF8String(buf);
  }
  
  public static class Handler implements IMessageHandler<CSPacketUseLvlToken, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketUseLvlToken message, MessageContext ctx) {
      JobsPlayer eep = JobsPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b);
      if (eep == null)
        return null; 
      boolean hasPendingReward = (eep.getLvlTokens().stream().filter(lvl -> (lvl.getJobType() == message.job && lvl.getPendingRewards().size() > 0)).count() > 0L);
      if (hasPendingReward)
        return null; 
      int idx = eep.getLvlTokens().indexOf(new LvlToken(message.lvlTokenUUID));
      if (idx == -1)
        return null; 
      LvlToken lvlToken = eep.getLvlTokens().get(idx);
      JobsManager.getInstance().generateLvlTokenRewards(lvlToken);
      if (lvlToken.getPendingRewards().size() == 0)
        eep.getLvlTokens().remove(idx); 
      eep.sync();
      return (IMessage)new SCPacketUpdateLvlTokenGui(eep.getLvlTokens());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketUseLvlToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */