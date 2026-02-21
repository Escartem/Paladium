package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHunterPlant extends TileEntity {
  public int ticks = 0;
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("ticks"))
      this.ticks = compound.func_74762_e("ticks"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74768_a("ticks", this.ticks);
    super.func_145841_b(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityHunterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */