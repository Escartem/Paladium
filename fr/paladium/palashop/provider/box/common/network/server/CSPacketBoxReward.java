package fr.paladium.palashop.provider.box.common.network.server;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.palashop.provider.box.common.network.rabbit.RBPacketBoxReward;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketBoxReward extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private int spinIndex;
  
  @PacketData
  private int rewardIndex;
  
  public CSPacketBoxReward() {}
  
  public CSPacketBoxReward(int entityId, int spinIndex, int rewardIndex) {
    this.entityId = entityId;
    this.spinIndex = spinIndex;
    this.rewardIndex = rewardIndex;
  }
  
  public void processServer(EntityPlayerMP player) {
    Entity entity = player.field_70170_p.func_73045_a(this.entityId);
    if (!(entity instanceof EntityBox))
      return; 
    EntityBox box = (EntityBox)entity;
    if (box.getBoxData() == null || !box.isWatching((EntityPlayer)player) || this.spinIndex > box.getBroadcastRewards().size() - 1)
      return; 
    List<EntityBox.EntityBoxBroadcastReward> rewards = box.getBroadcastRewards().get(this.spinIndex);
    if (rewards == null || rewards.isEmpty() || this.rewardIndex > rewards.size() - 1)
      return; 
    EntityBox.EntityBoxBroadcastReward broadcastReward = rewards.get(this.rewardIndex);
    if (broadcastReward == null || broadcastReward.isBroadcasted())
      return; 
    broadcastReward.setBroadcasted(true);
    BoxReward reward = broadcastReward.getReward();
    if (reward.getShopItem() == null || reward.getRarity() != ShopRarity.LEGENDARY)
      return; 
    (new RBPacketBoxReward(this.entityId, box.getBoxId(), box.getActiveName(), Server.getServerName(), reward.getShopItem().getName(), reward.getShopItem().getRarity())).broadcast();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\server\CSPacketBoxReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */