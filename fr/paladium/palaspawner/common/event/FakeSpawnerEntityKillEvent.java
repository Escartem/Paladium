package fr.paladium.palaspawner.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaspawner.common.entity.EntitySpawner;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.entity.Entity;

public class FakeSpawnerEntityKillEvent extends Event {
  private final Entity killer;
  
  private final EntitySpawner entitySpawner;
  
  private final Class<? extends Entity> fakeEntityClass;
  
  private final TileEntitySpawnerController tileEntitySpawnerController;
  
  public FakeSpawnerEntityKillEvent(Entity killer, EntitySpawner entitySpawner, Class<? extends Entity> fakeEntityClass, TileEntitySpawnerController tileEntitySpawnerController) {
    this.killer = killer;
    this.entitySpawner = entitySpawner;
    this.fakeEntityClass = fakeEntityClass;
    this.tileEntitySpawnerController = tileEntitySpawnerController;
  }
  
  public Entity getKiller() {
    return this.killer;
  }
  
  public EntitySpawner getEntitySpawner() {
    return this.entitySpawner;
  }
  
  public Class<? extends Entity> getFakeEntityClass() {
    return this.fakeEntityClass;
  }
  
  public TileEntitySpawnerController getTileEntitySpawnerController() {
    return this.tileEntitySpawnerController;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\event\FakeSpawnerEntityKillEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */