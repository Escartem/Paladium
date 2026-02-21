package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles;

import java.util.concurrent.TimeUnit;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCorruptedPlant extends TileEntity {
  public static final String TILE_ENTITY_ID = "tileEntityCorruptedPlant";
  
  public static final int MAX_STAGE = 4;
  
  public void setCreationMillis(long creationMillis) {
    this.creationMillis = creationMillis;
  }
  
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TileEntityCorruptedPlant))
      return false; 
    TileEntityCorruptedPlant other = (TileEntityCorruptedPlant)o;
    return !other.canEqual(this) ? false : ((getCreationMillis() != other.getCreationMillis()) ? false : (!(getTick() != other.getTick())));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TileEntityCorruptedPlant;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $creationMillis = getCreationMillis();
    result = result * 59 + (int)($creationMillis >>> 32L ^ $creationMillis);
    return result * 59 + getTick();
  }
  
  public String toString() {
    return "TileEntityCorruptedPlant(creationMillis=" + getCreationMillis() + ", tick=" + getTick() + ")";
  }
  
  private static long[] HOURS_STAGES = new long[] { TimeUnit.HOURS
      .toMillis(2L), TimeUnit.HOURS
      .toMillis(4L), TimeUnit.HOURS
      .toMillis(5L), TimeUnit.HOURS
      .toMillis(6L) };
  
  private static final String NBT_CREATION_MILLIS = "creationMillis";
  
  public long getCreationMillis() {
    return this.creationMillis;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  private long creationMillis = System.currentTimeMillis();
  
  private int tick = 0;
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("creationMillis", this.creationMillis);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.creationMillis = compound.func_74763_f("creationMillis");
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    this.tick++;
    if (!canUpdateTimer())
      return; 
    long now = System.currentTimeMillis();
    int data = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    grow(now, data);
  }
  
  public void grow(long now, int data) {
    if (data >= 4)
      return; 
    long aliveTime = getAliveTime(now);
    if (data == 0 && aliveTime >= HOURS_STAGES[0]) {
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2);
    } else if (data == 1 && aliveTime >= HOURS_STAGES[1]) {
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 2);
    } else if (data == 2 && aliveTime >= HOURS_STAGES[2]) {
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, 2);
    } else if (data == 3 && aliveTime >= HOURS_STAGES[3]) {
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 4, 2);
    } 
  }
  
  private long getAliveTime(long now) {
    return now - this.creationMillis;
  }
  
  private boolean canUpdateTimer() {
    return (this.tick % 20 == 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\tiles\TileEntityCorruptedPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */