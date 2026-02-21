package fr.paladium.palapass.common.network.packet.server;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.palashop.server.pb.PBManager;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketPalapassBuyNextStep extends ForgePacket {
  @PacketData
  private String rewardUUID;
  
  public CSPacketPalapassBuyNextStep() {}
  
  public CSPacketPalapassBuyNextStep(String rewardUUID) {
    this.rewardUUID = rewardUUID;
  }
  
  public void processServer(EntityPlayerMP player) {
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    List<RewardLevel> rewards = (List<RewardLevel>)PalapassManager.getInstance().getRewards().get(Integer.valueOf(PalapassManager.getMonth()));
    int idx = rewards.indexOf(new RewardLevel(this.rewardUUID));
    if (idx == -1) {
      PalapassTranslateEnum.REWARD_NOT_EXIST.notificationOrDefault(PalapassTranslateEnum.REWARD_NOT_EXIST.textOrDefault("Cette récompense n'existe pas"), player);
      reply(Boolean.valueOf(false));
      return;
    } 
    if (PalapassManager.getInstance().isFirstMonth()) {
      PalapassTranslateEnum.REWARD_FIRST_MONTH.notificationOrDefault(PalapassTranslateEnum.REWARD_FIRST_MONTH.textOrDefault("Vous ne pouvez pas acheter de palier le premier mois de la version"), player);
      reply(Boolean.valueOf(false));
      return;
    } 
    RewardLevel reward = rewards.get(idx);
    if (playerData.getRewardsClaimed().contains(this.rewardUUID)) {
      PalapassTranslateEnum.REWARD_ALREADY_RETRIEVED.notificationOrDefault("Vous avez déjà récupéré cette récompense", player);
      reply(Boolean.valueOf(false));
      return;
    } 
    int points = playerData.getPoints();
    if (points >= reward.getNeededPoints()) {
      PalapassTranslateEnum.TIER_ALREADY_UNLOCKED.notificationOrDefault("Vous avez déjà débloqué ce palier", player);
      reply(Boolean.valueOf(false));
      return;
    } 
    if (points < reward.getNeededPoints() && reward.getNeededPoints() - points > 500) {
      PalapassTranslateEnum.CANNOT_UNLOCK_TIER.notificationOrDefault("Vous ne pouvez pas débloquer ce palier.", player);
      reply(Boolean.valueOf(false));
      return;
    } 
    if (points < reward.getNeededPoints() && reward.getNeededPoints() - points <= 500)
      PBManager.buy(UUIDUtils.toString((Entity)player), 1000L, "Buy Palapass Step " + reward.getNeededPoints()).thenAccept(result -> {
            if (result.booleanValue()) {
              int next500PointTier = (points / 500 + 1) * 500;
              playerData.setPoints(next500PointTier);
              playerData.sync();
              PalapassTranslateEnum.TIER_UNLOCKED.notificationOrDefault("Vous venez de débloquer le palier.", player);
              PalaOracleEvent destroyedPbEvent = new PalaOracleEvent(player.func_110124_au().toString(), "[SHOP] Destroyed PBs");
              destroyedPbEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
              destroyedPbEvent.addField("amount", Integer.valueOf(1000));
              destroyedPbEvent.addField("reason", "BUY");
              destroyedPbEvent.capture();
              PalaOracleEvent buyItemEvent = new PalaOracleEvent(player.func_110124_au().toString(), "[SHOP] Item Buyed");
              buyItemEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
              buyItemEvent.addField("item-id", "palapass-step-" + reward.getNeededPoints());
              buyItemEvent.addField("item-name", "Palapass Step");
              buyItemEvent.addField("item-price", Integer.valueOf(1000));
              buyItemEvent.capture();
            } 
            reply(result);
          }).exceptionally(e -> {
            e.printStackTrace();
            reply(Boolean.valueOf(false));
            return null;
          }); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\server\CSPacketPalapassBuyNextStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */