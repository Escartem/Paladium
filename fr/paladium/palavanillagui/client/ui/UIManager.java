package fr.paladium.palavanillagui.client.ui;

import cpw.mods.ironchest.client.GUIChest;
import fr.paladium.palavanillagui.client.ui.brewingstand.UIBrewingStand;
import fr.paladium.palavanillagui.client.ui.chest.UIChest;
import fr.paladium.palavanillagui.client.ui.chest.UICpwChest;
import fr.paladium.palavanillagui.client.ui.dispenser.UIDispenser;
import fr.paladium.palavanillagui.client.ui.enchanttable.UIEnchantingTable;
import fr.paladium.palavanillagui.client.ui.furnace.UIFurnace;
import fr.paladium.palavanillagui.client.ui.hopper.UIHopper;
import fr.paladium.palavanillagui.client.ui.inventory.UIInventory;
import fr.paladium.palavanillagui.client.ui.workbench.UIWorkbench;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Container;

public class UIManager {
  private static final Map<Class<? extends GuiScreen>, Class<? extends UI>> UI_MAP = new LinkedHashMap<>();
  
  private static final List<Class<? extends UI>> IGNORED_CONSTRUCTOR = new ArrayList<>();
  
  static {
    register((Class)GuiInventory.class, (Class)UIInventory.class, true);
    register((Class)GuiEnchantment.class, (Class)UIEnchantingTable.class, false);
    register((Class)GuiChest.class, (Class)UIChest.class, false);
    register((Class)GuiCrafting.class, (Class)UIWorkbench.class, false);
    register((Class)GuiBrewingStand.class, (Class)UIBrewingStand.class, false);
    register((Class)GuiDispenser.class, (Class)UIDispenser.class, false);
    register((Class)GuiHopper.class, (Class)UIHopper.class, false);
    register((Class)GuiFurnace.class, (Class)UIFurnace.class, false);
    try {
      register((Class)GUIChest.class, (Class)UICpwChest.class, false);
    } catch (NoClassDefFoundError noClassDefFoundError) {}
  }
  
  public static void openUI(Class<? extends GuiScreen> gui, @Nullable Container container) {
    if (containKey(gui))
      try {
        Class<? extends UI> uiClazz = UI_MAP.get(gui);
        if (IGNORED_CONSTRUCTOR.contains(uiClazz))
          container = null; 
        UI ui = (container != null) ? uiClazz.getConstructor(new Class[] { Container.class }).newInstance(new Object[] { container }) : uiClazz.newInstance();
        ZUI.open(ui);
      } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
        e.printStackTrace();
      }  
  }
  
  public static boolean containKey(Class<? extends GuiScreen> gui) {
    return UI_MAP.containsKey(gui);
  }
  
  public static boolean containValue(Class<? extends UI> ui) {
    return UI_MAP.containsKey(ui);
  }
  
  private static void register(Class<? extends GuiScreen> guiScreen, Class<? extends UI> ui, boolean isConstructorIgnored) {
    UI_MAP.put(guiScreen, ui);
    if (isConstructorIgnored)
      IGNORED_CONSTRUCTOR.add(ui); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\UIManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */