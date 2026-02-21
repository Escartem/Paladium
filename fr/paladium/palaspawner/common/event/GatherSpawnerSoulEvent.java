package fr.paladium.palaspawner.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public class GatherSpawnerSoulEvent extends Event {
  private final EntityPlayer player;
  
  public GatherSpawnerSoulEvent(EntityPlayer player) {
    this.player = player;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\event\GatherSpawnerSoulEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */