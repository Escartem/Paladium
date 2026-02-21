package fr.paladium.pet.common.event.capture;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class PetCaptureEvent extends Event {
  private final EntityPlayer player;
  
  private final PetPlayer pet;
  
  private final DoubleLocation location;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public PetPlayer getPet() {
    return this.pet;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public PetCaptureEvent(EntityPlayer player, PetPlayer pet, DoubleLocation location) {
    this.player = player;
    this.pet = pet;
    this.location = location;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\capture\PetCaptureEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */