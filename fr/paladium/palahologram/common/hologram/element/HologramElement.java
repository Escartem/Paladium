package fr.paladium.palahologram.common.hologram.element;

import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.nbt.NBTTagCompound;

public abstract class HologramElement implements IDrawable, INbt<HologramElement> {
  public void setClazzName(String clazzName) {
    this.clazzName = clazzName;
  }
  
  public void setAlignment(Align alignment) {
    this.alignment = alignment;
  }
  
  private String clazzName = null;
  
  private Align alignment;
  
  public String getClazzName() {
    return this.clazzName;
  }
  
  public Align getAlignment() {
    return this.alignment;
  }
  
  public HologramElement() {
    this(Align.CENTER);
  }
  
  public HologramElement(Align alignment) {
    this.alignment = alignment;
  }
  
  public String getClassName() {
    if (this.clazzName != null)
      return this.clazzName; 
    this.clazzName = getClass().getName();
    return this.clazzName;
  }
  
  public HologramElement read(NBTTagCompound nbt) {
    this.clazzName = nbt.func_74779_i("clazzName");
    this.alignment = Align.values()[nbt.func_74762_e("alignment")];
    return this;
  }
  
  public void write(NBTTagCompound nbt) {
    nbt.func_74778_a("clazzName", getClassName());
    nbt.func_74768_a("alignment", this.alignment.ordinal());
  }
  
  public abstract double getHeight();
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\element\HologramElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */