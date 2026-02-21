package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuckyBlock extends TileEntity {
  private LuckyType type;
  
  private boolean isOpen;
  
  private LuckyEvents event;
  
  private int version;
  
  public LuckyType getType() {
    return this.type;
  }
  
  public boolean isOpen() {
    return this.isOpen;
  }
  
  public void setOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }
  
  public LuckyEvents getEvent() {
    return this.event;
  }
  
  public int getVersion() {
    return this.version;
  }
  
  public void setVersion(int version) {
    this.version = version;
  }
  
  public TileEntityLuckyBlock() {}
  
  public TileEntityLuckyBlock(LuckyType type) {
    this.type = type;
    this.isOpen = false;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.type = LuckyType.values()[compound.func_74762_e("type")];
    this.isOpen = compound.func_74767_n("isOpen");
    if (compound.func_74764_b("version"))
      this.version = compound.func_74762_e("version"); 
    if (!compound.func_74764_b("debug"))
      this.isOpen = false; 
    if (compound.func_74764_b("event"))
      this.event = LuckyEvents.values()[compound.func_74762_e("event")]; 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("type", this.type.ordinal());
    compound.func_74757_a("isOpen", this.isOpen);
    compound.func_74768_a("version", this.version);
    compound.func_74757_a("debug", true);
    if (this.event != null)
      compound.func_74768_a("event", this.event.ordinal()); 
  }
  
  public void setEvent(LuckyEvents event) {
    this.event = event;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */