package fr.paladium.palawither.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

public class WitherInvokeEvent extends Event {
  private final EntityPlayer player;
  
  public WitherInvokeEvent(EntityPlayer player) {
    this.player = player;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\event\WitherInvokeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */