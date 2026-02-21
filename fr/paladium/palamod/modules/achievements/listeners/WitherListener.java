package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.wither.PlayerSummonWitherEvent;
import fr.paladium.palamod.modules.achievements.AchievementUtils;
import fr.paladium.palamod.modules.achievements.types.SummonWitherAchievement;
import net.minecraft.entity.player.EntityPlayer;

public class WitherListener {
  @SubscribeEvent
  public void onSummonWither(PlayerSummonWitherEvent event) {
    AchievementUtils.performCheck(SummonWitherAchievement.class, (EntityPlayer)event.getPlayer(), 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\WitherListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */