package fr.paladium.palamod.modules.paladium.common.crafting;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.Item;

public class BowMachineRecipies {
  public static final int RANGE = 0;
  
  public static BowMachineRecipies instance = new BowMachineRecipies();
  
  private final Map<Item, Integer> modifiers = new HashMap<>();
  
  public Map<Item, Integer> getModifiers() {
    return this.modifiers;
  }
  
  public void addRecipie(Item stack, int type) {
    this.modifiers.put(stack, Integer.valueOf(type));
  }
  
  public boolean hasRecipie(Item stack) {
    if (this.modifiers.containsKey(stack))
      return true; 
    return false;
  }
  
  public int getModifier(Item stack) {
    if (this.modifiers.containsKey(stack))
      return ((Integer)this.modifiers.get(stack)).intValue(); 
    return -1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\BowMachineRecipies.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */