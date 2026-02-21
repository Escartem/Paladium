package fr.paladium.palamod.modules.luckyblock.structures.tiles;

import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityExpirableBlock extends TileEntity {
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setStructure(AbstractStructure structure) {
    this.structure = structure;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public AbstractStructure getStructure() {
    return this.structure;
  }
  
  private long expirationMillis = 0L;
  
  private AbstractStructure structure = null;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (!hasExpired())
      return; 
    if (this.structure == null) {
      this.field_145850_b.func_147475_p(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
      return;
    } 
    this.structure.onExpire(this);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("expirationMillis"))
      return; 
    this.expirationMillis = compound.func_74763_f("expirationMillis");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("expirationMillis", this.expirationMillis);
  }
  
  public void onExpire() {}
  
  public void init(long expirationMillis, AbstractStructure structure) {
    this.expirationMillis = expirationMillis;
    this.structure = structure;
  }
  
  public boolean hasExpired() {
    if (this.expirationMillis <= 0L)
      return false; 
    return (System.currentTimeMillis() > this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\tiles\TileEntityExpirableBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */