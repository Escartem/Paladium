package fr.paladium.palamod.modules.luckyblock.monthly.schematics;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.SchematicManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class TimedSchematic {
  private UUID uniqueId;
  
  private long expirationMillis;
  
  private DoubleLocation location;
  
  private Schematic schematic;
  
  private boolean pasted;
  
  public void setUniqueId(UUID uniqueId) {
    this.uniqueId = uniqueId;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setLocation(DoubleLocation location) {
    this.location = location;
  }
  
  public void setSchematic(Schematic schematic) {
    this.schematic = schematic;
  }
  
  public void setPasted(boolean pasted) {
    this.pasted = pasted;
  }
  
  public UUID getUniqueId() {
    return this.uniqueId;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public Schematic getSchematic() {
    return this.schematic;
  }
  
  public boolean isPasted() {
    return this.pasted;
  }
  
  public TimedSchematic(long durationMillis, DoubleLocation location, String schematicPath) {
    this.uniqueId = UUID.randomUUID();
    this.expirationMillis = System.currentTimeMillis() + durationMillis;
    this.location = location;
    this.pasted = false;
    try {
      this.schematic = SchematicUtils.load(schematicPath);
      if (this.expirationMillis == -1L)
        return; 
      SchematicManager.getInstance().getStructures().add(this);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void updateExpiration(long durationMillis) {
    this.expirationMillis = System.currentTimeMillis() + durationMillis;
  }
  
  public Schematic paste(EntityPlayerMP playerMP, boolean checkAir) {
    if (this.schematic == null) {
      MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&cIl n'a pas été possible de coller la structure." });
      return null;
    } 
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            Schematic newSchematic = SchematicUtils.paste((EntityPlayer)playerMP, this.schematic, playerMP.field_70170_p, this.location, checkAir);
            if (newSchematic == null)
              MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&cVous ne pouvez pas coller la structure ici." }); 
          } });
    this.pasted = true;
    return this.schematic;
  }
  
  public void remove(World world) {
    if (this.schematic == null || !this.pasted)
      return; 
    SchematicUtils.clean(this.schematic, world, this.location);
  }
  
  public boolean isExpired(long now) {
    if (this.expirationMillis == -1L)
      return false; 
    return (now >= this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\schematics\TimedSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */