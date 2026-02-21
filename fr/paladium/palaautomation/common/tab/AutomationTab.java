package fr.paladium.palaautomation.common.tab;

import fr.paladium.palaautomation.common.registry.AutomationBlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AutomationTab extends CreativeTabs {
  private static AutomationTab instance;
  
  public static AutomationTab getInstance() {
    return instance;
  }
  
  public AutomationTab() {
    super("palaautomation");
    instance = this;
  }
  
  public Item func_78016_d() {
    return Item.func_150898_a(AutomationBlockRegistry.PIPE_PALADIUM);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tab\AutomationTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */