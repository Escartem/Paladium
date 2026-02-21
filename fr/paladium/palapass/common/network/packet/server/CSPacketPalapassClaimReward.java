package fr.paladium.palapass.common.network.packet.server;

import fr.paladium.palaforgeutils.lib.command.CommandUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.reward.EnumRewardType;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketPalapassClaimReward extends ForgePacket {
  @PacketData
  private String rewardUUID;
  
  public CSPacketPalapassClaimReward() {}
  
  public CSPacketPalapassClaimReward(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public void processServer(EntityPlayerMP player) {
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    List<RewardLevel> rewards = (List<RewardLevel>)PalapassManager.getInstance().getRewards().get(Integer.valueOf(PalapassManager.getMonth()));
    playerData.refreshQuests();
    int idx = rewards.indexOf(new RewardLevel(this.rewardUUID));
    if (idx == -1) {
      PalapassTranslateEnum.REWARD_NOT_EXIST.notificationOrDefault("Cette récompense n'existe pas.", player);
      return;
    } 
    RewardLevel reward = rewards.get(idx);
    if (playerData.getRewardsClaimed().contains(this.rewardUUID)) {
      PalapassTranslateEnum.REWARD_ALREADY_RETRIEVED.notificationOrDefault("Vous avez déjà récupéré cette récompense.", player);
      return;
    } 
    int points = playerData.getPoints();
    if (points < reward.getNeededPoints() && reward.getNeededPoints() - points <= 500)
      return; 
    if (points < reward.getNeededPoints()) {
      PalapassTranslateEnum.NOT_ENOUGH_POINTS_FOR_TIER.notificationOrDefault("Vous n'avez pas assez de points pour récupérer ce palier.", player);
      return;
    } 
    if (reward.getType() == EnumRewardType.PREMIUM && !playerData.isPremium()) {
      PalapassTranslateEnum.TIER_ONLY_FOR_PREMIUM.notificationOrDefault("Ce palier n'est disponible que via le Palapass Premium", player);
      return;
    } 
    if (InventoryUtils.isFullInventory((EntityPlayer)player)) {
      PalapassTranslateEnum.INVENTORY_FULL.notificationOrDefault("Vous n'avez pas assez de place dans votre inventaire.", player);
      return;
    } 
    for (String c : reward.getCommands()) {
      CommandUtils.performCommands((EntityPlayer)player, new String[] { c.replace("{PLAYERNAME}", player.func_70005_c_()) });
    } 
    playerData.getRewardsClaimed().add(reward.getRewardUUID());
    playerData.sync();
    PalapassTranslateEnum.REWARD_RETRIEVED.notificationOrDefault("Vous venez de récupérer votre récompense.", player);
    reply(Boolean.valueOf(true));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\server\CSPacketPalapassClaimReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */