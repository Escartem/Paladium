package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles;

import java.util.concurrent.TimeUnit;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGiftChest extends TileEntity {
  public void setNextUseMillis(long nextUseMillis) {
    this.nextUseMillis = nextUseMillis;
  }
  
  private static final long COOLDOWN = TimeUnit.HOURS.toMillis(24L);
  
  public static final String NEXT_USE_MILLIS_TAG = "nextUseMillis";
  
  public long getNextUseMillis() {
    return this.nextUseMillis;
  }
  
  private long nextUseMillis = 0L;
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.nextUseMillis = compound.func_74763_f("nextUseMillis");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("nextUseMillis", this.nextUseMillis);
  }
  
  public void use(long now) {
    this.nextUseMillis = now + COOLDOWN;
  }
  
  public boolean isOnCooldown() {
    return (this.nextUseMillis > System.currentTimeMillis());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\tiles\TileEntityGiftChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */