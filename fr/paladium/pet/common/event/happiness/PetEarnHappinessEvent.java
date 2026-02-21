package fr.paladium.pet.common.event.happiness;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class PetEarnHappinessEvent extends Event {
  public static final int MINIMUM_AMOUNT = 0;
  
  private final EntityPlayer player;
  
  private int amount;
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public PetEarnHappinessEvent(EntityPlayer player, int amount) {
    if (amount < 0)
      amount = 0; 
    this.player = player;
    this.amount = amount;
    PetStatChangeEvent.call(player, UpdateStatType.HAPPINESS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\happiness\PetEarnHappinessEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */