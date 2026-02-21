package fr.paladium.palamod.modules.paladium.client.betternei.craft;

import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.client.util.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.crafting.BowMachineRecipies;
import fr.paladium.palamod.modules.paladium.utils.BowHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BowMachineCraft extends Craft {
  public static BowMachineCraft from(ItemStack modifier, int type) {
    BowMachineCraft craft = new BowMachineCraft();
    ItemStack result = new ItemStack((Item)ItemsRegister.PALADIUM_BOW);
    BowHelper.applyModifiers(result, type);
    craft.setOutputItemStack(result);
    craft.getSlotItem().put(Integer.valueOf(0), modifier);
    craft.getSlotItem().put(Integer.valueOf(1), new ItemStack((Item)ItemsRegister.PALADIUM_BOW));
    return craft;
  }
  
  public boolean haveRequiredItems(EntityPlayer player) {
    AtomicBoolean haveItems = new AtomicBoolean(true);
    this.slotItem.forEach((slot, stack) -> {
          if (stack != null)
            if (stack.func_77973_b() == ItemsRegister.PALADIUM_BOW) {
              haveItems.set((haveItems.get() && haveBowWithoutModifier(player, BowMachineRecipies.instance.getModifier(((ItemStack)getSlotItem().get(Integer.valueOf(0))).func_77973_b()))));
            } else {
              haveItems.set((haveItems.get() && ItemUtils.haveRequiredItems(player, stack, 1)));
            }  
        });
    return haveItems.get();
  }
  
  private boolean haveBowWithoutModifier(EntityPlayer player, int modifier) {
    boolean bowWithoutModifier = false;
    for (ItemStack invStack : player.field_71071_by.field_70462_a) {
      if (bowWithoutModifier)
        return bowWithoutModifier; 
      if (invStack != null)
        if (invStack.func_77973_b() == ItemsRegister.PALADIUM_BOW) {
          int[] modifiers = BowHelper.getModifiers(invStack);
          boolean notModifier = true;
          for (int i : modifiers) {
            if (notModifier)
              break; 
            notModifier = (notModifier && i != modifier);
          } 
          bowWithoutModifier = notModifier;
        }  
    } 
    return bowWithoutModifier;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\craft\BowMachineCraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */