package fr.paladium.palamod.modules.luckyblock.tileentity.halloween;

import java.util.concurrent.TimeUnit;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHalloweenTrade extends TileEntity {
  public static final String TAG_NEXT_USAGE_MILLIS = "nextUsageMillis";
  
  public void setNextUsageMillis(long nextUsageMillis) {
    this.nextUsageMillis = nextUsageMillis;
  }
  
  public static final long USAGE_COOLDOWN = TimeUnit.HOURS.toMillis(24L);
  
  public long getNextUsageMillis() {
    return this.nextUsageMillis;
  }
  
  private long nextUsageMillis = System.currentTimeMillis();
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("nextUsageMillis", this.nextUsageMillis);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.nextUsageMillis = compound.func_74763_f("nextUsageMillis");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\halloween\TileEntityHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */