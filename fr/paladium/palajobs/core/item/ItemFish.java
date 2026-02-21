package fr.paladium.palajobs.core.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemFish extends ItemFood {
  private final FishRarity rarity;
  
  public ItemFish(int feed, float saturation, String name, FishRarity rarity) {
    super(feed, saturation, false);
    this.rarity = rarity;
    func_77655_b(name);
    func_111206_d("palajobs:" + name);
    func_77637_a(CreativeTabs.field_78039_h);
  }
  
  public String func_77653_i(ItemStack item) {
    StringBuilder name = new StringBuilder();
    if (item.func_77973_b() instanceof ItemFish) {
      ItemFish fish = (ItemFish)item.func_77973_b();
      name.append(fish.rarity.prefix);
    } 
    name.append(super.func_77653_i(item));
    return name.toString();
  }
  
  public enum FishRarity {
    COMMON("§a"),
    RARE("§3"),
    LEGENDARY("§c"),
    MYTHIC("§d");
    
    private final String prefix;
    
    FishRarity(String prefix) {
      this.prefix = prefix;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\item\ItemFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */