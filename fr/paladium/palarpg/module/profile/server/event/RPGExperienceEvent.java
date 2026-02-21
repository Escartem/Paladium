package fr.paladium.palarpg.module.profile.server.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public abstract class RPGExperienceEvent extends Event {
  private final EntityPlayer player;
  
  public RPGExperienceEvent(EntityPlayer player) {
    this.player = player;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  @Cancelable
  public static class GainEvent extends RPGExperienceEvent {
    private double amount;
    
    public void setAmount(double amount) {
      this.amount = amount;
    }
    
    public double getAmount() {
      return this.amount;
    }
    
    public GainEvent(EntityPlayer player, double amount) {
      super(player);
      this.amount = amount;
    }
  }
  
  public static class LevelUpEvent extends RPGExperienceEvent {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\event\RPGExperienceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */