package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.inventory.UnclaimFinderBlueInventory;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUnclaimFinderBlue extends Container {
  private UnclaimFinderBlueInventory inventoryUnclaimFinderBlue;
  
  public ContainerUnclaimFinderBlue(UnclaimFinderBlueInventory inventoryUnclaimFinderBlue, InventoryPlayer inventory, EntityPlayer player) {
    this.inventoryUnclaimFinderBlue = inventoryUnclaimFinderBlue;
    this.inventoryUnclaimFinderBlue.func_70295_k_();
    func_75146_a(new Slot((IInventory)inventoryUnclaimFinderBlue, 0, 58, 52) {
          public boolean func_75214_a(@NonNull ItemStack stack) {
            if (stack == null)
              throw new NullPointerException("stack is marked non-null but is null"); 
            if (Block.func_149634_a(stack.func_77973_b()) != null && 
              Block.func_149634_a(stack.func_77973_b()).hasTileEntity(stack.func_77973_b().func_77647_b(0)))
              return true; 
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)inventoryUnclaimFinderBlue, 1, 84, 52) {
          public boolean func_75214_a(@NonNull ItemStack stack) {
            if (stack == null)
              throw new NullPointerException("stack is marked non-null but is null"); 
            if (Block.func_149634_a(stack.func_77973_b()) != null && 
              Block.func_149634_a(stack.func_77973_b()).hasTileEntity(stack.func_77973_b().func_77647_b(0)))
              return true; 
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)inventoryUnclaimFinderBlue, 2, 110, 52) {
          public boolean func_75214_a(@NonNull ItemStack stack) {
            if (stack == null)
              throw new NullPointerException("stack is marked non-null but is null"); 
            if (Block.func_149634_a(stack.func_77973_b()) != null && 
              Block.func_149634_a(stack.func_77973_b()).hasTileEntity(stack.func_77973_b().func_77647_b(0)))
              return true; 
            return false;
          }
        });
    func_75146_a(new Slot((IInventory)inventoryUnclaimFinderBlue, 3, 132, 15) {
          public boolean func_75214_a(@NonNull ItemStack stack) {
            if (stack == null)
              throw new NullPointerException("stack is marked non-null but is null"); 
            if (stack.func_77973_b().equals(ItemsRegister.FINDIUM))
              return true; 
            return false;
          }
        });
    bingPInventory(inventory);
  }
  
  public boolean func_75145_c(EntityPlayer playerIn) {
    return this.inventoryUnclaimFinderBlue.func_70300_a(playerIn);
  }
  
  private void bingPInventory(InventoryPlayer inventoryPlayer) {
    int l1 = 7;
    for (int l = 9; l <= 17; l++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, l, l1, 95));
      l1 += 20;
    } 
    int a1 = 7;
    for (int a = 18; a <= 26; a++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, a, a1, 117));
      a1 += 20;
    } 
    int b1 = 7;
    for (int b = 27; b <= 35; b++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, b, b1, 139));
      b1 += 20;
    } 
    int c1 = 7;
    for (int c = 0; c <= 8; c++) {
      func_75146_a(new Slot((IInventory)inventoryPlayer, c, c1, 161));
      c1 += 20;
    } 
  }
  
  public void func_75134_a(EntityPlayer playerIn) {
    super.func_75134_a(playerIn);
    this.inventoryUnclaimFinderBlue.func_70305_f();
  }
  
  @NonNull
  public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(index);
    return itemstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerUnclaimFinderBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */