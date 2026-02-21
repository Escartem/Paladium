package fr.paladium.palamod.events;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@DoNotRename
public class EntityPortalEnterEvent extends Event {
  public final World world;
  
  public final Entity entity;
  
  public final int x;
  
  public final int y;
  
  public final int z;
  
  public EntityPortalEnterEvent(World world, Entity entity, int x, int y, int z) {
    this.world = world;
    this.entity = entity;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public boolean isCancelable() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\events\EntityPortalEnterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */