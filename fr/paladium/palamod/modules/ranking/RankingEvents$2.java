package fr.paladium.palamod.modules.ranking;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.ranking.server.api.async.IRankingCallback;
import fr.paladium.ranking.server.api.async.SilentRankingCallback;
import fr.paladium.ranking.server.api.inputs.RankedData;
import fr.paladium.ranking.server.manager.RankingManager;
import net.minecraft.entity.player.EntityPlayer;

final class null implements CresusCallback<Double> {
  public void onSuccess(Double value) {
    RankedData moneyData = new RankedData(player, factionName, "money", value.doubleValue(), System.currentTimeMillis());
    RankingManager.getInstance().updateRankingData(moneyData, (IRankingCallback)new SilentRankingCallback());
  }
  
  public void onFail(Double value, Throwable error) {
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ranking\RankingEvents$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */