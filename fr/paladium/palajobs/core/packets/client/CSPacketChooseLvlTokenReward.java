package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.packets.server.SCPacketUpdateLvlTokenGui;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.server.managers.JobsManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class CSPacketChooseLvlTokenReward implements IMessage {
  private String lvlTokenUUID;
  
  private String rewardId;
  
  public CSPacketChooseLvlTokenReward() {}
  
  public CSPacketChooseLvlTokenReward(String uuid, String rewardId) {
    this.lvlTokenUUID = uuid;
    this.rewardId = rewardId;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.lvlTokenUUID);
    ByteBufUtils.writeUTF8String(buf, this.rewardId);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.lvlTokenUUID = ByteBufUtils.readUTF8String(buf);
    this.rewardId = ByteBufUtils.readUTF8String(buf);
  }
  
  public static class Handler implements IMessageHandler<CSPacketChooseLvlTokenReward, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketChooseLvlTokenReward message, MessageContext ctx) {
      JobsPlayer eep = JobsPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b);
      if (eep == null)
        return null; 
      int idx = eep.getLvlTokens().indexOf(new LvlToken(message.lvlTokenUUID));
      if (idx == -1)
        return null; 
      LvlToken lvlToken = eep.getLvlTokens().get(idx);
      boolean present = lvlToken.getPendingRewards().contains(message.rewardId);
      if (!present)
        return null; 
      lvlToken.getPendingRewards().clear();
      eep.getLvlTokens().remove(idx);
      eep.sync();
      JobsManager.getInstance().giveLvlTokenRewards((ctx.getServerHandler()).field_147369_b, message.rewardId);
      return (IMessage)new SCPacketUpdateLvlTokenGui(eep.getLvlTokens());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketChooseLvlTokenReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */