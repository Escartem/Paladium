package fr.paladium.palamod.modules.smeltery.inventory.slot;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemPickaxe;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotGrinder extends Slot {
  private GrinderLogic grinder;
  
  public SlotGrinder(IInventory inventory, int id, int x, int y, GrinderLogic grinder) {
    super(inventory, id, x, y);
    this.grinder = grinder;
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (this.grinder.getPaladium() == 100)
      return false; 
    if (stack.func_77973_b() == ItemsRegister.PALADIUM_INGOT)
      return true; 
    if (stack.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM))
      return true; 
    if (stack.func_77973_b() instanceof ItemRepairableArmor && ((ItemRepairableArmor)stack.func_77973_b())
      .func_82812_d().equals(PArmorMaterial.paladium))
      return true; 
    if (stack.func_77973_b() instanceof BaseItemPickaxe && ((BaseItemPickaxe)stack
      .func_77973_b()).getToolMaterial().equals(PToolMaterial.paladium))
      return true; 
    if (stack.func_77973_b() instanceof BaseItemSword && ((BaseItemSword)stack
      .func_77973_b()).getToolMaterial().equals(PToolMaterial.paladium))
      return true; 
    return false;
  }
  
  public ItemStack func_75209_a(int amount) {
    return super.func_75209_a(amount);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack stack) {
    func_75208_c(stack);
    super.func_82870_a(player, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\inventory\slot\SlotGrinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */