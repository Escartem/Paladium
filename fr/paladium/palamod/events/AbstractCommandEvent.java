package fr.paladium.palamod.events;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

@DoNotRename
public class AbstractCommandEvent extends Event {
  public final EntityPlayer player;
  
  public final String command;
  
  public AbstractCommandEvent(EntityPlayer player, String command) {
    this.player = player;
    this.command = command;
  }
  
  public boolean isCancelable() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\events\AbstractCommandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */