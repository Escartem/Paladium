package fr.paladium.palavanillagui.server.config.inventory;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import java.util.List;

@ConfigFile(path = "inventory/shortcuts.json", blocking = true, reloadable = true)
public class ShortcutConfig implements IConfig {
  private List<InventoryShortcut> shortcuts;
  
  public List<InventoryShortcut> getShortcuts() {
    return this.shortcuts;
  }
  
  public boolean isValid() {
    return (this.shortcuts != null);
  }
  
  public void onFailed() {}
  
  public void onLoaded() {}
  
  public void onReloaded() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\server\config\inventory\ShortcutConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */