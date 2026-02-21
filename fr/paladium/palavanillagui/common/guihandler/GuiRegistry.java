package fr.paladium.palavanillagui.common.guihandler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.paladium.palaforgeutils.lib.guihandler.CustomGuiHandler;
import fr.paladium.palavanillagui.PalaVanillaGuiMod;

public class GuiRegistry {
  private static CustomGuiHandler GUI_HANDLER;
  
  public static int GUI_ANVIL;
  
  public static void init() {
    GUI_HANDLER = new CustomGuiHandler();
    NetworkRegistry.INSTANCE.registerGuiHandler(PalaVanillaGuiMod.getInstance(), (IGuiHandler)GUI_HANDLER);
    register();
  }
  
  private static void register() {
    GUI_ANVIL = GUI_HANDLER.registerHandler(new AnvilGuiHandler());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\guihandler\GuiRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */