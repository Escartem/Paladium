package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.cresus.server.events.MoneyTransactionEvent;
import fr.paladium.cresus.server.events.MoneyTransactionType;
import fr.paladium.palamod.modules.achievements.AchievementUtils;
import fr.paladium.palamod.modules.achievements.types.CresusReachBalanceAchievement;
import fr.paladium.palamod.modules.achievements.types.CresusWithdrawAchievement;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AchievementsListeners {
  @SubscribeEvent
  public void cresusMoneyEvent(MoneyTransactionEvent e) {
    if (e.getPlayer().isPresent()) {
      EntityPlayerMP p = e.getPlayer().get();
      CresusReachBalanceAchievement.performCheck((EntityPlayer)p, (int)e.getCurrentBalanceAmount());
    } 
  }
  
  @SubscribeEvent
  public void onWithdraw(MoneyTransactionEvent event) {
    Optional<EntityPlayerMP> result = event.getPlayer();
    if (!result.isPresent() || event.getType() != MoneyTransactionType.WITHDRAW)
      return; 
    int amount = (int)((event.getAmount() < 0.0D) ? -event.getAmount() : event.getAmount());
    AchievementUtils.performCheck(CresusWithdrawAchievement.class, (EntityPlayer)result.get(), amount);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\AchievementsListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */