package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.CrusherCraftHandler;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.item.ItemStack;

public class UICrusherCraftOverlay extends UIItemOverlay<CrusherCraftHandler> {
  public UICrusherCraftOverlay(CrusherCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node arg1, Craft arg2) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\UICrusherCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */