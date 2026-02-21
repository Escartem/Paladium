package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStoneHearth extends TileEntity {
  public static final String TAG_LAST_HEAL = "lastHealTimestamp";
  
  private long lastHealTimestamp;
  
  public void setLastHealTimestamp(long lastHealTimestamp) {
    this.lastHealTimestamp = lastHealTimestamp;
  }
  
  public long getLastHealTimestamp() {
    return this.lastHealTimestamp;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("lastHealTimestamp"))
      return; 
    this.lastHealTimestamp = compound.func_74763_f("lastHealTimestamp");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("lastHealTimestamp", this.lastHealTimestamp);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\tile\TileEntityStoneHearth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */