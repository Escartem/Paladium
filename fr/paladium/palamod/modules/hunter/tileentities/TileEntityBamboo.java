package fr.paladium.palamod.modules.hunter.tileentities;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBamboo extends TileEntity implements IEntityAdditionalSpawnData {
  private int state = 0;
  
  public int getState() {
    return this.state;
  }
  
  public void setState(int state) {
    this.state = state;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74768_a("state", this.state);
    super.func_145841_b(compound);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("state"))
      this.state = compound.func_74762_e("state"); 
    super.func_145839_a(compound);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(this.state);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.state = additionalData.readInt();
  }
  
  public boolean canUpdate() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\tileentities\TileEntityBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */