package fr.paladium.pet.common.event.level;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.experience.PetExperienceSource;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import net.minecraft.entity.player.EntityPlayer;

public class PetLevelUpEvent extends Event {
  private final EntityPlayer player;
  
  private final PetExperienceSource source;
  
  private final int currentLevel;
  
  private final int newLevel;
  
  private final int currentSlots;
  
  private final int newSlots;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public PetExperienceSource getSource() {
    return this.source;
  }
  
  public int getCurrentLevel() {
    return this.currentLevel;
  }
  
  public int getNewLevel() {
    return this.newLevel;
  }
  
  public int getCurrentSlots() {
    return this.currentSlots;
  }
  
  public int getNewSlots() {
    return this.newSlots;
  }
  
  public PetLevelUpEvent(EntityPlayer player, PetExperienceSource source, int currentLevel, int newLevel, int currentSlots, int newSlots) {
    this.player = player;
    this.source = source;
    this.currentLevel = currentLevel;
    this.newLevel = newLevel;
    this.currentSlots = currentSlots;
    this.newSlots = newSlots;
    PetStatChangeEvent.call(player, UpdateStatType.LEVEL);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\level\PetLevelUpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */