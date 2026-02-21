package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.packets.server.SCPacketUpdateLvlTokenGui;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.server.managers.JobsManager;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<CSPacketChooseLvlTokenReward, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketChooseLvlTokenReward message, MessageContext ctx) {
    JobsPlayer eep = JobsPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b);
    if (eep == null)
      return null; 
    int idx = eep.getLvlTokens().indexOf(new LvlToken(CSPacketChooseLvlTokenReward.access$000(message)));
    if (idx == -1)
      return null; 
    LvlToken lvlToken = eep.getLvlTokens().get(idx);
    boolean present = lvlToken.getPendingRewards().contains(CSPacketChooseLvlTokenReward.access$100(message));
    if (!present)
      return null; 
    lvlToken.getPendingRewards().clear();
    eep.getLvlTokens().remove(idx);
    eep.sync();
    JobsManager.getInstance().giveLvlTokenRewards((ctx.getServerHandler()).field_147369_b, CSPacketChooseLvlTokenReward.access$100(message));
    return (IMessage)new SCPacketUpdateLvlTokenGui(eep.getLvlTokens());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketChooseLvlTokenReward$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */