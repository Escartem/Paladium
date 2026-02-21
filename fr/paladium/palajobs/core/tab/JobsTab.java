package fr.paladium.palajobs.core.tab;

import fr.paladium.palajobs.core.registry.ItemsRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class JobsTab extends CreativeTabs {
  private static JobsTab instance;
  
  public JobsTab() {
    super("jobs");
    instance = this;
    func_78014_h();
  }
  
  public static JobsTab getInstance() {
    if (instance == null)
      instance = new JobsTab(); 
    return instance;
  }
  
  public Item func_78016_d() {
    return ItemsRegistry.AMETHYST_RADIUS_HOE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tab\JobsTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */