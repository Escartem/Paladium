package fr.paladium.pet.common.event.experience;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.PetLogger;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class PetEarnExperienceEvent extends Event {
  public static final double MINIMUM_AMOUNT = 0.0D;
  
  private final EntityPlayer player;
  
  private final PetExperienceSource source;
  
  private double amount;
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public PetExperienceSource getSource() {
    return this.source;
  }
  
  public double getAmount() {
    return this.amount;
  }
  
  public PetEarnExperienceEvent(EntityPlayer player, PetExperienceSource source, double amount) {
    if (amount < 0.0D) {
      amount = 0.0D;
      PetLogger.error("Tried to earn negative experience: " + amount + " from source: " + source.name());
    } 
    this.player = player;
    this.source = source;
    this.amount = amount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\experience\PetEarnExperienceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */