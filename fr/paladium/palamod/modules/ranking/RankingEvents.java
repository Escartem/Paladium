package fr.paladium.palamod.modules.ranking;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.factions.api.callback.DataCallback;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.ranking.common.data.RankedPlayerData;
import fr.paladium.ranking.common.data.RankedPlayerDataValue;
import fr.paladium.ranking.server.api.async.IRankingCallback;
import fr.paladium.ranking.server.api.async.SilentRankingCallback;
import fr.paladium.ranking.server.api.inputs.RankedData;
import fr.paladium.ranking.server.manager.RankingManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class RankingEvents {
  @SubscribeEvent
  public void onLevelUp(OnPlayerLevelUp e) {
    if (e.player.field_70170_p.field_72995_K)
      return; 
    IPlayer iPlayer = PlayerController.getInstance().getExistingSyncPlayer(e.player.func_110124_au());
    String factionName = (iPlayer == null) ? "Wilderness" : (iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness");
    pushRankingFactionData(e.player, factionName);
  }
  
  @SubscribeEvent
  public void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
    if (e.player.field_70170_p.field_72995_K)
      return; 
    if (ForgeEnv.isDev()) {
      System.err.println("[RankingEvents] pushRankingData is disabled in devmode.");
    } else {
      pushRankingData(e.player);
    } 
  }
  
  public static void pushRankingData(final EntityPlayer player) {
    if (!(player instanceof EntityPlayerMP))
      return; 
    PlayerController.getInstance().getPlayer((EntityPlayerMP)player, new DataCallback<IPlayer>() {
          public void onCallback(IPlayer iPlayer) {
            String factionName = (iPlayer == null) ? "Wilderness" : (iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness");
            RankingEvents.pushRankingMoneyData(player, factionName);
            RankingEvents.pushRankingFactionData(player, factionName);
            RankingEvents.pushRankingData(player, factionName, "boss");
            RankingEvents.pushRankingData(player, factionName, "egghunt");
            RankingEvents.pushRankingData(player, factionName, "end");
            RankingEvents.pushRankingData(player, factionName, "chorus");
            RankingEvents.pushRankingData(player, factionName, "koth");
            long now = System.currentTimeMillis();
            RankingEvents.pushRankingData(player, factionName, "clicker", new RankedPlayerDataValue(PlayerClickerData.get((Entity)player).getTotalPoints(), now));
          }
        });
  }
  
  public static void pushRankingMoneyData(final EntityPlayer player, final String factionName) {
    CresusManager.getInstance().getBalanceAsync(player.func_110124_au(), new CresusCallback<Double>() {
          public void onSuccess(Double value) {
            RankedData moneyData = new RankedData(player, factionName, "money", value.doubleValue(), System.currentTimeMillis());
            RankingManager.getInstance().updateRankingData(moneyData, (IRankingCallback)new SilentRankingCallback());
          }
          
          public void onFail(Double value, Throwable error) {
            error.printStackTrace();
          }
        });
  }
  
  public static void pushRankingFactionData(EntityPlayer player, String factionName) {
    if (player == null)
      return; 
    int alchemistLvl = JobsBridge.getLevel(player, "ALCHEMIST");
    long alchemistTimestamp = JobsBridge.getLastTimestamp(player, "ALCHEMIST");
    RankedData jobAlchemistData = new RankedData(player, factionName, "job.alchemist", alchemistLvl, alchemistTimestamp);
    RankingManager.getInstance().updateRankingData(jobAlchemistData, (IRankingCallback)new SilentRankingCallback());
    int hunterLvl = JobsBridge.getLevel(player, "HUNTER");
    long hunterTimestamp = JobsBridge.getLastTimestamp(player, "HUNTER");
    RankedData jobHunterData = new RankedData(player, factionName, "job.hunter", hunterLvl, hunterTimestamp);
    RankingManager.getInstance().updateRankingData(jobHunterData, (IRankingCallback)new SilentRankingCallback());
    int farmerLvl = JobsBridge.getLevel(player, "FARMER");
    long farmerTimestamp = JobsBridge.getLastTimestamp(player, "FARMER");
    RankedData jobFarmerData = new RankedData(player, factionName, "job.farmer", farmerLvl, farmerTimestamp);
    RankingManager.getInstance().updateRankingData(jobFarmerData, (IRankingCallback)new SilentRankingCallback());
    int minerLvl = JobsBridge.getLevel(player, "MINER");
    long minerTimestamp = JobsBridge.getLastTimestamp(player, "MINER");
    RankedData jobMinerData = new RankedData(player, factionName, "job.miner", minerLvl, minerTimestamp);
    RankingManager.getInstance().updateRankingData(jobMinerData, (IRankingCallback)new SilentRankingCallback());
  }
  
  public static void pushRankingData(EntityPlayer player, String factionName, String key) {
    RankedPlayerData playerData = RankedPlayerData.get((Entity)player);
    RankedPlayerDataValue value = playerData.getValue(key);
    RankedData data = new RankedData(player, factionName, key, value.getValue(), value.getTimestamp());
    RankingManager.getInstance().updateRankingData(data, (IRankingCallback)new SilentRankingCallback());
  }
  
  public static void pushRankingData(EntityPlayer player, String factionName, String key, RankedPlayerDataValue value) {
    RankedData data = new RankedData(player, factionName, key, value.getValue(), value.getTimestamp());
    RankingManager.getInstance().updateRankingData(data, (IRankingCallback)new SilentRankingCallback());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ranking\RankingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */