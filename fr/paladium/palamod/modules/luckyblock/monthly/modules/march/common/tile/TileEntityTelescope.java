package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTelescope extends TileEntity {
  public static final String TAG_REWARD_TAKEN = "rewardTaken";
  
  private boolean rewardTaken;
  
  public void setRewardTaken(boolean rewardTaken) {
    this.rewardTaken = rewardTaken;
  }
  
  public boolean isRewardTaken() {
    return this.rewardTaken;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("rewardTaken"))
      return; 
    this.rewardTaken = compound.func_74767_n("rewardTaken");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74757_a("rewardTaken", this.rewardTaken);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\tile\TileEntityTelescope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */