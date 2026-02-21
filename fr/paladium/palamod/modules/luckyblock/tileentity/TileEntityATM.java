package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.util.FastUUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityATM extends TileEntity {
  private String owner = "";
  
  private int ticks;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public int getTicks() {
    return this.ticks;
  }
  
  public void setTicks(int ticks) {
    this.ticks = ticks;
  }
  
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K && 
      !this.owner.isEmpty() && 
      this.field_145850_b.func_152378_a(FastUUID.parseUUID(this.owner)) != null)
      this.ticks++; 
    super.func_145845_h();
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (!this.owner.isEmpty())
      compound.func_74778_a("owner", this.owner); 
    compound.func_74768_a("ticks", this.ticks);
    super.func_145841_b(compound);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    if (compound.func_74764_b("ticks"))
      this.ticks = compound.func_74762_e("ticks"); 
    super.func_145839_a(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityATM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */