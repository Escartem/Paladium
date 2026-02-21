package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.concurrent.TimeUnit;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHunterCorruptedPlant extends TileEntity {
  public static final String TILE_ENTITY_ID = "tileEntityHunterCorruptedPlant";
  
  private static final long LAST_SPAWN_TIME = TimeUnit.MINUTES.toMillis(10L);
  
  private long lastSpawnMillis = System.currentTimeMillis();
  
  private int tick = 0;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    this.tick++;
    if (!canUpdateTimer())
      return; 
    long now = System.currentTimeMillis();
    if (!canSpawn(now))
      return; 
    this.lastSpawnMillis = now;
    if (!this.field_145850_b.field_73012_v.nextBoolean())
      return; 
    spawnEntity();
  }
  
  @SideOnly(Side.SERVER)
  private void spawnEntity() {}
  
  private long getLastSpawnTime(long now) {
    return now - this.lastSpawnMillis;
  }
  
  private boolean canSpawn(long now) {
    return (getLastSpawnTime(now) >= LAST_SPAWN_TIME);
  }
  
  private boolean canUpdateTimer() {
    return (this.tick % 20 == 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\tiles\TileEntityHunterCorruptedPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */