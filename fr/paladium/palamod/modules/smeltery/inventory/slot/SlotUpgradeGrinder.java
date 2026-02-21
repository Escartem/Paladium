package fr.paladium.palamod.modules.smeltery.inventory.slot;

import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgradeGrinder extends Slot {
  public SlotUpgradeGrinder(IInventory inventory, int id, int x, int y) {
    super(inventory, id, x, y);
  }
  
  public boolean func_75214_a(ItemStack stack) {
    if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.tools.ItemPaladiumHammer || stack.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumBroadsword || stack.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumFastsword || stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEndiumGauntlet)
      return true; 
    if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.miner.item.ItemGodPickaxe || IRPGItem.is(stack) || stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor || stack.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer)
      return true; 
    return false;
  }
  
  public ItemStack func_75209_a(int amount) {
    return super.func_75209_a(amount);
  }
  
  public void func_82870_a(EntityPlayer player, ItemStack stack) {
    func_75208_c(stack);
    super.func_82870_a(player, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\inventory\slot\SlotUpgradeGrinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */