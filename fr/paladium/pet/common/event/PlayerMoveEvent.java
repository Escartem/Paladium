package fr.paladium.pet.common.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.entity.player.EntityPlayerMP;

@Cancelable
public class PlayerMoveEvent extends Event {
  private final EntityPlayerMP player;
  
  private final DoubleLocation lastLocation;
  
  private final DoubleLocation currentLocation;
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public DoubleLocation getLastLocation() {
    return this.lastLocation;
  }
  
  public DoubleLocation getCurrentLocation() {
    return this.currentLocation;
  }
  
  public PlayerMoveEvent(EntityPlayerMP player, DoubleLocation lastLocation, DoubleLocation currentLocation) {
    this.player = player;
    this.lastLocation = lastLocation;
    this.currentLocation = currentLocation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\PlayerMoveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */