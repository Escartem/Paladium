package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.UUID;

public class MajordomeData {
  private UUID entityUniqueId;
  
  private Location location;
  
  private Schematic schematic;
  
  public void setEntityUniqueId(UUID entityUniqueId) {
    this.entityUniqueId = entityUniqueId;
  }
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public void setSchematic(Schematic schematic) {
    this.schematic = schematic;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public Schematic getSchematic() {
    return this.schematic;
  }
  
  public MajordomeData(UUID entityUniqueId, Location location) {
    this.entityUniqueId = entityUniqueId;
    this.location = location;
    this.schematic = null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\MajordomeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */