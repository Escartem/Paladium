package fr.paladium.palamod.modules.luckyblock.tileentity.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.christmas.PacketChristmasMockup;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChristmasMockup extends TileEntity {
  private int orientation = -1;
  
  public void setOrientation(int orientation) {
    this.orientation = orientation;
  }
  
  private boolean isSending = false;
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.orientation = compound.func_74762_e("orientation");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("orientation", this.orientation);
  }
  
  public int getOrientation() {
    if (!this.isSending && this.orientation == -1) {
      this.isSending = true;
      PalaMod.getNetwork().sendToServer((IMessage)new PacketChristmasMockup(this, this.orientation));
    } 
    return this.orientation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\christmas\TileEntityChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */