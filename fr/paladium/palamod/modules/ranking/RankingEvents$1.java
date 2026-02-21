package fr.paladium.palamod.modules.ranking;

import fr.paladium.factions.api.callback.DataCallback;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.ranking.common.data.RankedPlayerDataValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

final class null extends DataCallback<IPlayer> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ranking\RankingEvents$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */