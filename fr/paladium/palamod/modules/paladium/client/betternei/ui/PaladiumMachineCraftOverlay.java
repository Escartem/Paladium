package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.api.uikit.CraftButtonNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.PalaMachineCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UIPalaMachine;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import net.minecraft.item.ItemStack;

public class PaladiumMachineCraftOverlay extends UIItemOverlay<PalaMachineCraftHandler> {
  public PaladiumMachineCraftOverlay(PalaMachineCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    ImageNode.create(parentNode.dw(2.0D) - 118.0D, parentNode.dh(2.0D) - 118.0D + 75.0D, 236.0D, 236.0D)
      .resource(UIPalaMachine.BACKGROUND_IMAGE)
      .body(img -> {
          ItemStack mainStack = craft.getItemForSlot(0).func_77946_l();
          if (craft.getItemForSlot(0).func_77973_b() == craft.getOutputItemStack().func_77973_b())
            mainStack.func_77964_b(mainStack.func_77958_k() / 2); 
          BNEIFakeSlotNode.create(img.dw(2.0D) - 30.0D, -20.0D, 60.0D).item(mainStack).attach(img);
          BNEIFakeSlotNode.create(-15.0D, 57.0D, 60.0D).item(craft.getItemForSlot(1)).attach(img);
          BNEIFakeSlotNode.create(25.0D, img.getHeight() - 60.0D, 60.0D).item(craft.getItemForSlot(2)).attach(img);
          BNEIFakeSlotNode.create(img.getWidth() - 60.0D + 15.0D, 57.0D, 60.0D).item(craft.getItemForSlot(3)).attach(img);
          BNEIFakeSlotNode.create(img.getWidth() - 60.0D - 25.0D, img.getHeight() - 60.0D, 60.0D).item(craft.getItemForSlot(4)).attach(img);
          BNEIFakeSlotNode.create(img.dw(2.0D) - 30.0D, img.dh(2.0D) - 30.0D, 60.0D).item(craft.getOutputItemStack()).background(UIPalaMachine.MAIN_SLOT).attach(img);
          CraftButtonNode.create(img.getWidth() + 20.0D, img.getHeight() - 40.0D, 36.0D).attach(img);
        }).attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\PaladiumMachineCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */