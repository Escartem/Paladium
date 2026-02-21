package fr.paladium.palaspawner.common.tab;

import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SpawnerTabs extends CreativeTabs {
  private static SpawnerTabs instance;
  
  public static SpawnerTabs getInstance() {
    return instance;
  }
  
  public SpawnerTabs() {
    super("palaspawner");
    instance = this;
  }
  
  public Item func_78016_d() {
    return (Item)SpawnerItemRegistry.CAVERN_HAMMER;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\tab\SpawnerTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */