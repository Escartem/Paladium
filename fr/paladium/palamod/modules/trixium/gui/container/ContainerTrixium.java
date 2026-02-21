package fr.paladium.palamod.modules.trixium.gui.container;

import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.trixium.gui.container.slot.TrixiumSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerTrixium extends Container implements ApollonContainer {
  private EntityPlayer player;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
  
  public ContainerTrixium(SimpleGHandler.GuiHandlerData data) {
    this(data.getPlayer());
  }
  
  public ContainerTrixium(EntityPlayer player) {
    setPlayer(player);
    createContainerSlots(new TrixiumInventory(), player);
    createPlayerSlots(player.field_71071_by);
  }
  
  private void createContainerSlots(TrixiumInventory inventory, EntityPlayer player) {
    func_75146_a((Slot)new TrixiumSlot(inventory, 0, 0, 0, player));
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
    if (stack.func_77973_b() != ItemsRegister.TRIXIUM && stack.func_77973_b() != Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM))
      return null; 
    func_75141_a(id, null);
    func_75141_a(0, stack);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\ContainerTrixium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */