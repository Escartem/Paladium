package fr.paladium.palamixins.event.common.entity;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.Entity;

@Cancelable
public class EntityIsInsideOpaqueBlockEvent extends Event {
  private final Entity entity;
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public EntityIsInsideOpaqueBlockEvent(Entity entity) {
    this.entity = entity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\common\entity\EntityIsInsideOpaqueBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */