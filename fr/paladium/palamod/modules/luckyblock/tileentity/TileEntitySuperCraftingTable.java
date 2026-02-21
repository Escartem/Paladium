package fr.paladium.palamod.modules.luckyblock.tileentity;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySuperCraftingTable extends TileEntity {
  private int durability = 0;
  
  public int getDurability() {
    return this.durability;
  }
  
  public void setDurability(int durability) {
    this.durability = durability;
  }
  
  Random r = new Random();
  
  public TileEntitySuperCraftingTable(int durability) {
    this.durability = durability;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("durability"))
      this.durability = compound.func_74762_e("durability"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    if (this.durability != 0)
      compound.func_74768_a("durability", this.durability); 
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public TileEntitySuperCraftingTable() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntitySuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */