package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.achievements.AchievementUtils;
import fr.paladium.palamod.modules.achievements.types.TradeMoneyAchievement;
import fr.paladium.palatrade.server.event.TradeSuccesEvent;
import net.minecraft.entity.player.EntityPlayer;

public class TradeListener {
  @SubscribeEvent
  public void onTradeEvent(TradeSuccesEvent event) {
    double firstPlayerAmount = event.getFirstTrade().getMoney();
    double secondPlayerAmount = event.getSecondTrade().getMoney();
    if (event.getFirstPlayer() != null)
      AchievementUtils.performCheck(TradeMoneyAchievement.class, (EntityPlayer)event.getFirstPlayer(), (int)firstPlayerAmount); 
    if (event.getSecondPlayer() != null)
      AchievementUtils.performCheck(TradeMoneyAchievement.class, (EntityPlayer)event.getSecondPlayer(), (int)secondPlayerAmount); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\TradeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */