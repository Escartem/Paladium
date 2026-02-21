package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.utils.IRepairable;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class ModdedChestLogic extends TileEntity implements IInventory, ISidedInventory {
  public ItemStack[] content;
  
  public boolean openning;
  
  public boolean closing;
  
  public float prevLid;
  
  public float lid;
  
  private final int repairableRow;
  
  protected Color[] colors;
  
  public int getRepairableRow() {
    return this.repairableRow;
  }
  
  public ModdedChestLogic(int repairableRow, int slotRing) {
    this.content = new ItemStack[108 + slotRing];
    this.openning = false;
    this.closing = false;
    this.repairableRow = repairableRow;
    this.colors = new Color[] { Color.WHITE, Color.WHITE };
  }
  
  public void func_145845_h() {
    for (int i = 108; i < func_70302_i_(); i++) {
      ItemStack ring = this.content[i];
      if (ring != null && ring.func_77960_j() < ring.func_77958_k())
        for (int j = 0; j < this.repairableRow * 12; j++) {
          ItemStack armor = this.content[j];
          repairArmor(armor, ring);
        }  
    } 
  }
  
  public void repairArmor(ItemStack item, ItemStack ring) {
    if (item != null && item.func_77973_b() instanceof IRepairable && item.func_77960_j() > 0 && 
      !((IRepairable)item.func_77973_b()).isEndium())
      ((IRepairable)item.func_77973_b()).repair(item, ring); 
    if (item != null && item.func_77973_b() instanceof IRepairable && item.func_77960_j() > 0 && 
      !((IRepairable)item.func_77973_b()).isEndium())
      ((IRepairable)item.func_77973_b()).repair(item, ring); 
  }
  
  public int func_70302_i_() {
    return this.content.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.content[slot];
  }
  
  public ItemStack func_70298_a(int slotIndex, int amount) {
    if (this.content[slotIndex] == null)
      return null; 
    if ((this.content[slotIndex]).field_77994_a <= amount) {
      ItemStack itemStack = this.content[slotIndex];
      this.content[slotIndex] = null;
      func_70296_d();
      return itemStack;
    } 
    ItemStack itemstack = this.content[slotIndex].func_77979_a(amount);
    if ((this.content[slotIndex]).field_77994_a == 0)
      this.content[slotIndex] = null; 
    func_70296_d();
    return itemstack;
  }
  
  public ItemStack func_70304_b(int slotIndex) {
    if (this.content[slotIndex] != null) {
      ItemStack itemstack = this.content[slotIndex];
      this.content[slotIndex] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slotIndex, ItemStack stack) {
    this.content[slotIndex] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    func_70296_d();
  }
  
  public String func_145825_b() {
    return "tile.moddedchest";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public void func_70295_k_() {
    this.openning = true;
    this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v
        .nextFloat() * 0.1F + 0.9F);
  }
  
  public void func_70305_f() {
    this.closing = true;
    this.field_145850_b.func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v
        .nextFloat() * 0.1F + 0.9F);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.content.length; i++) {
      if (this.content[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.content[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      } 
    } 
    compound.func_74782_a("Items", (NBTBase)nbttaglist);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    NBTTagList nbttaglist = compound.func_150295_c("Items", 10);
    this.content = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < this.content.length)
        this.content[j] = ItemStack.func_77949_a(nbttagcompound1); 
    } 
  }
  
  public int[] func_94128_d(int side) {
    int[] slots = new int[108];
    for (int i = 0; i < slots.length; i++)
      slots[i] = i; 
    return slots;
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return (slot < 108);
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return (slot < 108);
  }
  
  public boolean func_94041_b(int slot, ItemStack item) {
    return (slot < 108 || item.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.BaseItemRing);
  }
  
  public abstract Resource getSlotTexture();
  
  @SideOnly(Side.CLIENT)
  public abstract Color[] getFontColors();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\ModdedChestLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */