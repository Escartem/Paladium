package fr.paladium.palamod.modules.hunter.gui.containers;

import fr.paladium.palamod.modules.hunter.networks.PlayerBackPackProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryBackPack implements IInventory {
  public EntityPlayer owner;
  
  public ItemStack[] content;
  
  public int size;
  
  private final String inventoryName;
  
  public InventoryBackPack(int size, EntityPlayer owner) {
    this(size, owner, "backpack");
  }
  
  public InventoryBackPack(int size, EntityPlayer owner, String inventoryName) {
    this.owner = owner;
    this.size = size;
    this.content = new ItemStack[size];
    this.inventoryName = inventoryName;
    read(owner);
  }
  
  public void read(EntityPlayer player) {
    if (!player.field_70128_L)
      this.content = PlayerBackPackProperties.get(player).getContent(); 
  }
  
  public void write(EntityPlayer player) {
    if (!player.field_70128_L)
      PlayerBackPackProperties.get(player).setContent(this.content); 
  }
  
  public int func_70302_i_() {
    return this.size;
  }
  
  public ItemStack func_70301_a(int index) {
    return this.content[index];
  }
  
  public ItemStack func_70298_a(int index, int amount) {
    ItemStack stack = func_70301_a(index);
    if (stack != null)
      if (stack.field_77994_a > amount) {
        stack = stack.func_77979_a(amount);
        if (stack.field_77994_a == 0)
          this.content[index] = null; 
      } else {
        this.content[index] = null;
      }  
    return stack;
  }
  
  public ItemStack func_70304_b(int index) {
    ItemStack stack = func_70301_a(index);
    if (stack != null)
      this.content[index] = null; 
    return stack;
  }
  
  public void func_70299_a(int index, ItemStack stack) {
    this.content[index] = stack;
    write(this.owner);
  }
  
  public String func_145825_b() {
    return "palamod." + this.inventoryName;
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public void func_70296_d() {}
  
  public boolean func_70300_a(EntityPlayer player) {
    return true;
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public boolean func_94041_b(int index, ItemStack stack) {
    if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack || stack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemDrawers || stack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.pack.ItemDrawersPack || stack.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemCustomDrawers || stack.func_77973_b().func_77658_a().toLowerCase().contains("drawer") || stack.func_77973_b().func_77658_a().toLowerCase().contains("safe_chest"))
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\containers\InventoryBackPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */