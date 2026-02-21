package fr.paladium.palamod.modules.enderchest.config;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.item.ItemStack;

@ConfigFile(path = "enderchest/config.json", blocking = true)
public class EnderchestConfig implements IConfig {
  public List<ItemStack> getBlackListed() {
    return this.blackListed;
  }
  
  private final List<ItemStack> blackListed = new LinkedList<>();
  
  public boolean isValid() {
    return (this.blackListed != null);
  }
  
  public void onLoaded() {
    System.out.println("[EnderChest] Loading " + this.blackListed.size() + " blacklisted items");
    for (ItemStack item : this.blackListed)
      System.out.println("[EnderChest] Blacklisted item: " + item); 
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
  
  public boolean isBlackListed(ItemStack stack) {
    return this.blackListed.stream().anyMatch(item -> (item.func_77973_b() == stack.func_77973_b()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\config\EnderchestConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */