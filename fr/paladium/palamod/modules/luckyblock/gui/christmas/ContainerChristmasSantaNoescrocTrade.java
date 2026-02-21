package fr.paladium.palamod.modules.luckyblock.gui.christmas;

import fr.paladium.lib.apollon.container.ApollonContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChristmasSantaNoescrocTrade extends Container implements ApollonContainer {
  public static IInventory inventory = new InventoryChristmasSantaNoescrocTrade();
  
  private int santaNoescroc;
  
  public int getSantaNoescroc() {
    return this.santaNoescroc;
  }
  
  public void setSantaNoescroc(int santaNoescroc) {
    this.santaNoescroc = santaNoescroc;
  }
  
  public ContainerChristmasSantaNoescrocTrade() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new SlotChristmasSantaNoescrocTrade(inventory, j + i * 9, 8 + j * 18, 66 + i * 18)); 
    } 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return false;
  }
  
  public Slot func_75139_a(int index) {
    if (index < inventory.func_70302_i_())
      return this.field_75151_b.get(index); 
    return null;
  }
  
  public void func_75141_a(int index, ItemStack itemStack) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\christmas\ContainerChristmasSantaNoescrocTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */