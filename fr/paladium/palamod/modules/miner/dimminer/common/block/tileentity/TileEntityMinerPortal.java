package fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMinerPortal extends TileEntity {
  private String owner;
  
  public String getOwner() {
    return this.owner;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("owner"))
      this.owner = nbt.func_74779_i("owner"); 
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    if (this.owner != null)
      nbt.func_74778_a("owner", this.owner); 
  }
  
  public void setOwner(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    this.owner = UUIDUtils.toString(entity);
  }
  
  public boolean isOwner(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return this.owner.equals(UUIDUtils.toString(entity));
  }
  
  public boolean hasOwner() {
    return (this.owner != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\tileentity\TileEntityMinerPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */