package fr.paladium.palajobs.core.container;

import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBoss extends Container implements ApollonContainer {
  private EntityPlayer player;
  
  private ItemStack filter;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
  
  public ContainerBoss(EntityPlayer player, ItemStack filter) {
    setPlayer(player);
    this.filter = filter;
    createContainerSlots(new BossInventory(), player, filter);
    createPlayerSlots(player.field_71071_by);
  }
  
  private void createContainerSlots(BossInventory inventory, EntityPlayer player, ItemStack filter) {
    func_75146_a(new BossSlot(inventory, 0, 0, 0, player, filter));
  }
  
  private void createPlayerSlots(InventoryPlayer player) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)player, j + i * 9 + 9, 0, 0)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)player, i, 0, 0)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int id) {
    ItemStack stack = null;
    Slot slot = this.field_75151_b.get(id);
    if (slot == null || !slot.func_75216_d())
      return null; 
    stack = slot.func_75211_c();
    if (!stack.func_77969_a(this.filter))
      return null; 
    func_75141_a(id, null);
    func_75141_a(0, stack);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\container\ContainerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */