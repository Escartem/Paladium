package fr.paladium.common.tooltip.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.client.keybind.KeybindBridge;
import fr.paladium.common.TooltipManager;
import fr.paladium.common.tooltip.ModuleItemTooltip;
import fr.paladium.common.tooltip.utils.ItemData;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.java.shortcut.Shortcut;
import fr.paladium.palavanillagui.client.ui.inventory.UIInventory;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientItemTooltipEventHandler {
  private long lastClick;
  
  private final Map<ItemData, String[]> infoCache = (Map)new HashMap<>();
  
  private final Map<ItemData, String> wikiCache = new HashMap<>();
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onTooltip(ItemTooltipEvent event) {
    if (!ModuleItemTooltip.getInstance().isEnable())
      return; 
    GuiScreen currentScreen = (Minecraft.func_71410_x()).field_71462_r;
    boolean zUIInventory = true;
    try {
      zUIInventory = !ZUI.isOpen(UIInventory.class);
    } catch (NoClassDefFoundError noClassDefFoundError) {}
    if (!(currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory) && zUIInventory)
      return; 
    EntityPlayer player = event.entityPlayer;
    ItemStack item = event.itemStack;
    List<String> tooltip = event.toolTip;
    if (item == null)
      return; 
    ItemData data = new ItemData(item);
    String[] additionalInformationsArray = this.infoCache.getOrDefault(data, TooltipManager.getTooltipInformation(item, player, event.showAdvancedItemTooltips));
    this.infoCache.putIfAbsent(data, additionalInformationsArray);
    boolean showAdditionalInformations = (additionalInformationsArray != null && additionalInformationsArray.length != 0);
    String wikiURL = this.wikiCache.getOrDefault(data, TooltipManager.getWikiURL(item));
    this.wikiCache.putIfAbsent(data, wikiURL);
    boolean showWiki = (wikiURL != null && !wikiURL.isEmpty());
    if (showAdditionalInformations || showWiki)
      tooltip.add(""); 
    if (showAdditionalInformations)
      if (GuiScreen.func_146272_n()) {
        List<String> additionalInformations = new ArrayList<>(Arrays.asList(additionalInformationsArray));
        if (!additionalInformations.isEmpty()) {
          additionalInformations.set(0, "§8[§cINFO§8] §b§o" + (String)additionalInformations.get(0));
          for (int i = 1; i < additionalInformations.size(); i++)
            additionalInformations.set(i, "§b§o" + (String)additionalInformations.get(i)); 
        } 
        tooltip.addAll(additionalInformations);
      } else {
        tooltip.add("§8» §8[§cSHIFT§8] §7pour plus d'informations");
      }  
    if (showWiki) {
      tooltip.add("§8» §8[§c" + KeybindBridge.getKeyDisplayString() + "§8] §7pour ouvrir la page du wiki");
      if (Shortcut.isKeyDown(ModuleItemTooltip.getInstance().getKeyWiki().func_151463_i()) && System.currentTimeMillis() - this.lastClick >= 3000L) {
        GuiUtils.openBrowser(wikiURL);
        this.lastClick = System.currentTimeMillis();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\tooltip\event\ClientItemTooltipEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */