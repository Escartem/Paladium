package fr.paladium.palamod.events;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayerMP;

@Deprecated
@DoNotRename
public class EditSignEvent extends Event {
  public final EntityPlayerMP player;
  
  public final int x;
  
  public final int y;
  
  public final int z;
  
  public final String[] text;
  
  public EditSignEvent(EntityPlayerMP player, int x, int y, int z, String[] text) {
    this.player = player;
    this.x = x;
    this.y = y;
    this.z = z;
    this.text = text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\events\EditSignEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */