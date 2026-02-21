package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.PalaForgeCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UIPalaForge;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PalaForgeCraftOverlay extends UIItemOverlay<PalaForgeCraftHandler> {
  public PalaForgeCraftOverlay(PalaForgeCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    BNEIFakeSlotNode.create(parentNode.getWidth() / 2.0D - 270.0D + 60.0D, 320.0D, 60.0D)
      .item(craft.getItemForSlot(0))
      .attach(parentNode);
    ProgressNode.create(parentNode.getWidth() / 2.0D - 270.0D + 60.0D, 400.0D, 59.0D, 76.0D)
      .resource(UIPalaForge.ARROW_DOWN, UIPalaForge.ARROW_DOWN_FILLER)
      .progress(1.0F)
      .attach(parentNode);
    BNEIFakeSlotNode.create(parentNode.getWidth() / 2.0D - 270.0D + 60.0D, 496.0D, 60.0D)
      .item(craft.getOutputItemStack())
      .attach(parentNode);
    ProgressNode.create(536.5D, 430.0D, 53.0D, 53.0D)
      .resource(UIPalaForge.FIRE, UIPalaForge.FIRE_FILLER)
      .progress(1.0F)
      .attach(parentNode);
    BNEIFakeSlotNode.create(533.0D, 496.0D, 60.0D)
      .item(Items.field_151044_h)
      .attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\PalaForgeCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */