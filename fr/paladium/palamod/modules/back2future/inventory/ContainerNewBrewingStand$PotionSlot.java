package fr.paladium.palamod.modules.back2future.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;

class PotionSlot extends Slot {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerNewBrewingStand$PotionSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */