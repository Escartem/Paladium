package fr.paladium.palamod.modules.luckyblock.tileentity.christmas;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChristmasTree extends TileEntity implements IEntityAdditionalSpawnData {
  private int state;
  
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
    if (additionalData.isReadable())
      this.state = additionalData.readInt(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\christmas\TileEntityChristmasTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */