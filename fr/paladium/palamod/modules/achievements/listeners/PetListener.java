package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.achievements.types.PetReachLevelAchievement;
import fr.paladium.pet.common.event.level.PetLevelUpEvent;

public class PetListener {
  @SubscribeEvent
  public void onPetLevelUp(PetLevelUpEvent event) {
    if (event.isCanceled())
      return; 
    PetReachLevelAchievement.performCheck(event.getPlayer(), event.getNewLevel());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\PetListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */