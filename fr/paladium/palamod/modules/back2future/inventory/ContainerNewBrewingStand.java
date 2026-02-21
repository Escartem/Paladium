package fr.paladium.palamod.modules.back2future.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.recipes.BrewingFuelRegistry;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBrewingStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;

public class ContainerNewBrewingStand extends Container {
  private TileEntityNewBrewingStand tile;
  
  private final Slot ingredientSlot;
  
  private int prevBrewTime;
  
  private int prevFuel;
  
  private int prevCurrentFuel;
  
  public ContainerNewBrewingStand(InventoryPlayer playerInvt, TileEntityNewBrewingStand tile) {
    this.tile = tile;
    func_75146_a(new PotionSlot((IInventory)tile, 0, 56, 51));
    func_75146_a(new PotionSlot((IInventory)tile, 1, 79, 58));
    func_75146_a(new PotionSlot((IInventory)tile, 2, 102, 51));
    this.ingredientSlot = func_75146_a(new IngredientSlot((IInventory)tile, 3, 79, 17));
    func_75146_a(new BlazePowderSlot((IInventory)tile, 4, 17, 17));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)playerInvt, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)playerInvt, i, 8 + i * 18, 142)); 
  }
  
  public void func_75142_b() {
    super.func_75142_b();
    for (int i = 0; i < this.field_75149_d.size(); i++) {
      ICrafting icrafting = this.field_75149_d.get(i);
      if (this.prevBrewTime != this.tile.func_145935_i())
        icrafting.func_71112_a(this, 0, this.tile.func_145935_i()); 
      if (this.prevFuel != this.tile.getFuel())
        icrafting.func_71112_a(this, 1, this.tile.getFuel()); 
      if (this.prevCurrentFuel != this.tile.getCurrentFuel())
        icrafting.func_71112_a(this, 2, this.tile.getCurrentFuel()); 
    } 
    this.prevBrewTime = this.tile.func_145935_i();
    this.prevFuel = this.tile.getFuel();
    this.prevCurrentFuel = this.tile.getCurrentFuel();
  }
  
  @SideOnly(Side.CLIENT)
  public void func_75137_b(int id, int value) {
    if (id == 0) {
      this.tile.func_145938_d(value);
    } else if (id == 1) {
      this.tile.setFuel(value);
    } else if (id == 2) {
      this.tile.setCurrentFuel(value);
    } 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(slotIndex);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if ((slotIndex < 0 || slotIndex > 2) && slotIndex != 3) {
        if (!this.ingredientSlot.func_75216_d() && this.ingredientSlot.func_75214_a(itemstack1)) {
          if (!func_75135_a(itemstack1, 3, 4, false))
            return null; 
        } else if (PotionSlot.canHoldPotion(itemstack)) {
          if (!func_75135_a(itemstack1, 0, 3, false))
            return null; 
        } else if (slotIndex >= 4 && slotIndex < 31) {
          if (!func_75135_a(itemstack1, 31, 40, false))
            return null; 
        } else if (slotIndex >= 31 && slotIndex < 40) {
          if (!func_75135_a(itemstack1, 4, 31, false))
            return null; 
        } else if (!func_75135_a(itemstack1, 4, 40, false)) {
          return null;
        } 
      } else {
        if (!func_75135_a(itemstack1, 4, 40, true))
          return null; 
        slot.func_75220_a(itemstack1, itemstack);
      } 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      slot.func_82870_a(player, itemstack1);
    } 
    return itemstack;
  }
  
  class BlazePowderSlot extends Slot {
    public BlazePowderSlot(IInventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
    }
    
    public boolean func_75214_a(ItemStack stack) {
      return (stack != null && BrewingFuelRegistry.isFuel(stack));
    }
  }
  
  class IngredientSlot extends Slot {
    public IngredientSlot(IInventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
    }
    
    public boolean func_75214_a(ItemStack stack) {
      return (stack != null && stack.func_77973_b().func_150892_m(stack));
    }
  }
  
  static class PotionSlot extends Slot {
    public PotionSlot(IInventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
    }
    
    public boolean func_75214_a(ItemStack stack) {
      return canHoldPotion(stack);
    }
    
    public int func_75219_a() {
      return 1;
    }
    
    public void func_82870_a(EntityPlayer player, ItemStack stack) {
      if (stack.func_77973_b() instanceof net.minecraft.item.ItemPotion && stack.func_77960_j() > 0)
        player.func_71064_a((StatBase)AchievementList.field_76001_A, 1); 
      super.func_82870_a(player, stack);
    }
    
    public static boolean canHoldPotion(ItemStack stack) {
      return (stack != null && (stack
        .func_77973_b() instanceof net.minecraft.item.ItemPotion || stack.func_77973_b() == Items.field_151069_bo));
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerNewBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */