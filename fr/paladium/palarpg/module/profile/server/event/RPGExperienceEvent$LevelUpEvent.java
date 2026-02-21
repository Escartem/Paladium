package fr.paladium.palarpg.module.profile.server.event;

import net.minecraft.entity.player.EntityPlayer;

public class LevelUpEvent extends RPGExperienceEvent {
  private final int oldLevel;
  
  private final int newlevel;
  
  public int getOldLevel() {
    return this.oldLevel;
  }
  
  public int getNewlevel() {
    return this.newlevel;
  }
  
  public LevelUpEvent(EntityPlayer player, int oldLevel, int newLevel) {
    super(player);
    this.oldLevel = oldLevel;
    this.newlevel = newLevel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\event\RPGExperienceEvent$LevelUpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */