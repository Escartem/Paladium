package fr.paladium.palarpg.module.equipment.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;

public class RPGEquipmentAnvilEventListener {
  @SubscribeEvent
  public void onCraft(AnvilUpdateEvent e) {
    ItemStack left = e.left;
    ItemStack right = e.right;
    if (left == null || right == null)
      return; 
    if (left.func_77973_b() instanceof RPGItemRune && right.func_77973_b() instanceof RPGItemRune) {
      ItemStack result = EquipmentRuneManager.fusion(left, right);
      if (result != null) {
        RPGItemRune runeItem = (RPGItemRune)right.func_77973_b();
        e.output = result;
        e.cost = (runeItem.getItemData().getTier() + 1) * 5;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\listener\RPGEquipmentAnvilEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */