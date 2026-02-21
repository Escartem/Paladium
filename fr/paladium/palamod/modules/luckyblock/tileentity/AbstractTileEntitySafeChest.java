package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class AbstractTileEntitySafeChest extends TileEntity {
  public String code;
  
  public abstract ItemStack[] getContent();
  
  public abstract String getCode();
  
  public abstract int getCodeMaxLength();
  
  public boolean isCodeInitialized() {
    return (getCode() != null && getCode().length() == getCodeMaxLength());
  }
  
  public boolean canAcceptItem(ItemStack item) {
    if (item.func_77973_b() instanceof fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack || item.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemDrawers || item
      .func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.pack.ItemDrawersPack || item.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemCustomDrawers || item
      .func_77973_b().func_77658_a().toLowerCase().contains("drawer") || item
      .func_77973_b().func_77658_a().toLowerCase().contains("safe_chest"))
      return false; 
    return true;
  }
  
  public void setCode(String code) {
    this.code = code;
    func_70296_d();
  }
  
  public boolean isCorrect(String code) {
    if (getCode() == null)
      return true; 
    return code.equals(getCode());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\AbstractTileEntitySafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */