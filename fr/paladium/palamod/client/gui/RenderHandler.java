package fr.paladium.palamod.client.gui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.UIInGameMenu;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban.UIBan;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderHandler {
  private static GuiScreen lastGuiScreen = null;
  
  private static PGuiIngame guiIngame = new PGuiIngame();
  
  public static void guiInstance(GuiOpenEvent event) {
    if (event.gui instanceof net.minecraft.client.gui.GuiMainMenu) {
      showMainMenu(event);
    } else if (event.gui instanceof net.minecraft.client.gui.GuiLanguage) {
      event.gui = new PGuiLanguage(lastGuiScreen, (Minecraft.func_71410_x()).field_71474_y, Minecraft.func_71410_x().func_135016_M());
    } else if (event.gui instanceof net.minecraft.client.gui.GuiIngameMenu) {
      event.gui = (GuiScreen)new UIInGameMenu();
    } else if (event.gui instanceof net.minecraft.client.gui.GuiSelectWorld && !"mc.paladium-pvp.fr".equals("var_server_ip") && !Utils.isDev()) {
      showMainMenu(event);
    } else if (event.gui instanceof net.minecraft.client.gui.GuiDownloadTerrain && (Minecraft.func_71410_x()).field_71462_r instanceof UIMainMenu) {
      event.setCanceled(true);
    } else if (event.gui instanceof GuiDisconnected) {
      GuiDisconnected guiDisconnected = (GuiDisconnected)event.gui;
      IChatComponent chatComponent = (IChatComponent)ObfuscationReflectionHelper.getPrivateValue(GuiDisconnected.class, guiDisconnected, new String[] { "field_146304_f" });
      String message = chatComponent.func_150260_c();
      if (message.startsWith("{") && message.endsWith("}")) {
        UIBan.BanData data = UIBan.BanData.parse((JsonObject)(new Gson()).fromJson(message, JsonObject.class));
        if (data != null && data.isValid())
          event.gui = (GuiScreen)ZUI.open((UI)new UIBan(data), true); 
      } 
    } 
    lastGuiScreen = event.gui;
  }
  
  private static void showMainMenu(GuiOpenEvent event) {
    event.gui = (GuiScreen)new UIMainMenu();
  }
  
  public static void guiIngameInstance(RenderGameOverlayEvent.Pre event) {
    guiIngame.renderGameOverlay(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\gui\RenderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */