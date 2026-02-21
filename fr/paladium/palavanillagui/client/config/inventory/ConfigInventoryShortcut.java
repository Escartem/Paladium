package fr.paladium.palavanillagui.client.config.inventory;

import cpw.mods.fml.common.Loader;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palavanillagui.client.ui.util.container.ShortcutListContainer;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConfigInventoryShortcut {
  public LinkedList<InventoryShortcut> getShortcuts() {
    return this.shortcuts;
  }
  
  public LinkedList<InventoryShortcut> getLastUsed() {
    return this.lastUsed;
  }
  
  private LinkedList<InventoryShortcut> shortcuts = new LinkedList<>(Arrays.asList(new InventoryShortcut[] { new InventoryShortcut("https://pictures.paladium-pvp.fr/inv/clicker.png", "vanilla.gui.shortcut.clicker.name", "/clicker open"), new InventoryShortcut("https://pictures.paladium-pvp.fr/inv/jobs.png", "vanilla.gui.shortcut.jobs.name", "/palajobs opengui"), new InventoryShortcut("https://pictures.paladium-pvp.fr/inv/faction.png", "vanilla.gui.shortcut.faction.name", "/f opengui"), new InventoryShortcut("https://pictures.paladium-pvp.fr/inv/palapass.png", "vanilla.gui.shortcut.palapass.name", "/palapass"), new InventoryShortcut("https://pictures.paladium-pvp.fr/inv/pet.png", "vanilla.gui.shortcut.pet.name", "/pet") }));
  
  private final LinkedList<InventoryShortcut> lastUsed = new LinkedList<>();
  
  public void setShortcuts(LinkedList<InventoryShortcut> shortcuts) {
    this.shortcuts = shortcuts;
    save();
  }
  
  public void addLastUsed(InventoryShortcut shortcut) {
    this.lastUsed.addFirst(shortcut);
    while (this.lastUsed.size() > 5)
      this.lastUsed.removeLast(); 
    save();
  }
  
  private void save() {
    try {
      JsonConfigLoader.saveConfig(new File(Loader.instance().getConfigDir(), "shortcuts.json"), this);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void swapShortcut(InventoryShortcut next, InventoryShortcut target) {
    if (next.equals(target))
      return; 
    ListSignal<InventoryShortcut> list = ShortcutListContainer.listSignal;
    int targetIndex = list.indexOf(target);
    if (list.contains(next)) {
      int nextIndex = list.indexOf(next);
      list.set(nextIndex, target);
      list.set(targetIndex, next);
    } else {
      list.set(targetIndex, next);
    } 
    boolean isDifferent = checkModification();
    ShortcutListContainer.modifySignal.set(Boolean.valueOf(isDifferent));
  }
  
  private boolean checkModification() {
    List<InventoryShortcut> shortcuts = (List<InventoryShortcut>)ShortcutListContainer.listSignal.getOrDefault();
    boolean isDifferent = false;
    for (int i = 0; i < shortcuts.size(); i++) {
      isDifferent = !((InventoryShortcut)shortcuts.get(i)).equals(this.shortcuts.get(i));
      if (isDifferent)
        break; 
    } 
    return isDifferent;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\client\config\inventory\ConfigInventoryShortcut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */