package fr.paladium.palamod.modules.luckyblock.gui.christmas;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryChristmasSantaNoescrocTrade implements IInventory {
  public static final int SIZE = 18;
  
  public static final String NAME = "Santa Noescroc Inventory";
  
  private static ItemStack[] inventory = new ItemStack[] { 
      new ItemStack(ItemsRegister.ENDIUM_INGOT), new ItemStack(ItemsRegister.PALADIUM_INGOT), new ItemStack(ItemsRegister.ENDIUM_SWORD), new ItemStack(ItemsRegister.ENDIUM_PICKAXE), new ItemStack(ItemsRegister.ENDIUM_AXE), new ItemStack(BlocksRegister.ENDIUM_TOTEM), new ItemStack((Item)ItemsRegister.DYNAMITE_ENDIUM), new ItemStack(ItemsRegister.INVOKER_STONE), new ItemStack(BlocksRegister.FLOWER_ENDIUM), new ItemStack((Block)BlocksRegister.CHRISTMAS_LUCKY_BLOCK), 
      new ItemStack((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK), new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK), new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK), new ItemStack((Block)BlocksRegister.BLACK_LUCKY_BLOCK), new ItemStack(BlocksRegister.PALADIUM_MACHINE_BLOCK), new ItemStack(ItemsRegister.PALADIUM_GREEN_CHESTPLATE), new ItemStack(ItemsRegister.LEGENDARYSTONE_RANDOM), new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_BLEU) };
  
  public int func_70302_i_() {
    return 18;
  }
  
  public ItemStack func_70298_a(int id, int count) {
    return null;
  }
  
  public ItemStack func_70304_b(int id) {
    return null;
  }
  
  public void func_70299_a(int id, ItemStack itemStack) {}
  
  public String func_145825_b() {
    return "Santa Noescroc Inventory";
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public int func_70297_j_() {
    return 1;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return false;
  }
  
  public boolean func_94041_b(int id, ItemStack itemStack) {
    return false;
  }
  
  public ItemStack func_70301_a(int id) {
    if (id < 18)
      return inventory[id]; 
    return null;
  }
  
  public void func_70296_d() {}
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\christmas\InventoryChristmasSantaNoescrocTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */