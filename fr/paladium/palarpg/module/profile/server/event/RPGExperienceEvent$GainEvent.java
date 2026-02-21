package fr.paladium.palarpg.module.profile.server.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class GainEvent extends RPGExperienceEvent {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\event\RPGExperienceEvent$GainEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */