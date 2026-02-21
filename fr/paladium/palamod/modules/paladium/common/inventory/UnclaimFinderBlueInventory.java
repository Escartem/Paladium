package fr.paladium.palamod.modules.paladium.common.inventory;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class UnclaimFinderBlueInventory implements IInventory {
  private ItemStack[] contents = new ItemStack[4];
  
  private ItemStack itemStack;
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public int func_70302_i_() {
    return this.contents.length;
  }
  
  public ItemStack func_70301_a(int p_70301_1_) {
    return this.contents[p_70301_1_];
  }
  
  public ItemStack func_70298_a(int index, int count) {
    ItemStack itemstack = ItemStackHelper.getAndSplit(this.contents, index, count);
    if (itemstack != null)
      func_70296_d(); 
    return itemstack;
  }
  
  public ItemStack func_70304_b(int p_70304_1_) {
    return null;
  }
  
  public void func_70299_a(int index, ItemStack stack) {
    this.contents[index] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    if (func_70301_a(3) != null && func_70301_a(3).func_77973_b().equals(ItemsRegister.FINDIUM))
      for (int i = (func_70301_a(3)).field_77994_a; i > 0; i--) {
        NBTTagCompound nbtTagCompound = this.itemStack.func_77978_p();
        if (nbtTagCompound.func_74762_e("findium") + 1200 <= 153600) {
          func_70298_a(3, 1);
          nbtTagCompound.func_74768_a("findium", nbtTagCompound.func_74762_e("findium") + 1200);
          this.itemStack.func_77982_d(nbtTagCompound);
        } 
      }  
    func_70296_d();
  }
  
  public String func_145825_b() {
    return null;
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public void func_70296_d() {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.contents.length; i++) {
      if (this.contents[i] != null) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74774_a("Slot", (byte)i);
        this.contents[i].func_77955_b(nbttagcompound);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound);
      } 
    } 
    NBTTagCompound compound = this.itemStack.func_77978_p();
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
    this.itemStack.func_77982_d(compound);
  }
  
  public boolean func_70300_a(EntityPlayer p_70300_1_) {
    return true;
  }
  
  public void func_70295_k_() {
    NBTTagList nbttaglist = this.itemStack.func_77978_p().func_150295_c("Items", 10);
    this.contents = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
      int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < this.contents.length)
        this.contents[j] = ItemStack.func_77949_a(nbttagcompound); 
    } 
  }
  
  public void func_70305_f() {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.contents.length; i++) {
      if (this.contents[i] != null) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74774_a("Slot", (byte)i);
        this.contents[i].func_77955_b(nbttagcompound);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound);
      } 
    } 
    NBTTagCompound compound = this.itemStack.func_77978_p();
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
    this.itemStack.func_77982_d(compound);
  }
  
  public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\inventory\UnclaimFinderBlueInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */