package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.api.uikit.CraftButtonNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.BowMachineCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UIBowMachine;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import net.minecraft.item.ItemStack;

public class BowMachineCraftOverlay extends UIItemOverlay<BowMachineCraftHandler> {
  public BowMachineCraftOverlay(BowMachineCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    ContainerNode.create(parentNode.dw(2.0D) - 142.0D, 310.0D, 284.0D, 60.0D)
      .body(container -> {
          BNEIFakeSlotNode.create(0.0D, 0.0D, 60.0D).item(craft.getItemForSlot(0)).placeholder(UIBowMachine.WRENCH_PLACEHOLDER).attach(container);
          ImageNode.create(80.0D, 1.5D, 124.0D, 57.0D).resource(UIBowMachine.ARROW_FILLER).attach(container);
          BNEIFakeSlotNode.create(224.0D, 0.0D, 60.0D).item(craft.getOutputItemStack()).placeholder(UIBowMachine.BOW_PLACEHOLDER).attach(container);
          CraftButtonNode.create(container.getWidth() + 40.0D, container.getHeight() - 36.0D, 36.0D).attach(container);
        }).attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\BowMachineCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */