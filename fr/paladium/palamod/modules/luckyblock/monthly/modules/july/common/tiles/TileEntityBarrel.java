package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBarrel extends TileEntity {
  public static final String TILE_ENTITY_ID = "barrel";
  
  public static final String FIELD_POWDER_AMOUNT = "powderAmount";
  
  public void setPowderAmount(int powderAmount) {
    this.powderAmount = powderAmount;
  }
  
  public int getPowderAmount() {
    return this.powderAmount;
  }
  
  private int powderAmount = 0;
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("powderAmount", this.powderAmount);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (compound.func_74764_b("powderAmount"))
      this.powderAmount = compound.func_74762_e("powderAmount"); 
  }
  
  public void addPowder() {
    this.powderAmount++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\tiles\TileEntityBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */