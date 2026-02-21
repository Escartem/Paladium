package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.blueprint.client.betternei.ui.node.ShowStructureButtonNode;
import fr.paladium.palamod.modules.ablueprint.BluePrintRegistry;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.GolemBoxCraftHandler;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import net.minecraft.item.ItemStack;

public class GolemBoxCraftOverlay extends UIItemOverlay<GolemBoxCraftHandler> {
  public GolemBoxCraftOverlay(GolemBoxCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parent, Craft craft) {
    ShowStructureButtonNode.create(640.0D, 220.0D, 36.0D)
      .blueprint(BluePrintRegistry.GOLEM_BOX.getId())
      .attach(parent);
    ContainerNode.create(parent.dw(2.0D) - 170.0D, 270.0D, 340.0D, 100.0D)
      .body(container -> {
          BNEIFakeSlotNode.create(0.0D, 0.0D, 100.0D).item(craft.getItemForSlot(0)).attach(container);
          BNEIFakeSlotNode.create(120.0D, 0.0D, 100.0D).item(craft.getItemForSlot(1)).attach(container);
          BNEIFakeSlotNode.create(240.0D, 0.0D, 100.0D).item(craft.getItemForSlot(2)).attach(container);
        }).attach(parent);
    ContainerNode.create(parent.dw(2.0D) - 135.0D, 450.0D, 270.0D, 130.0D)
      .body(container -> {
          BNEIFakeSlotNode.create(0.0D, 0.0D, 60.0D).item(craft.getOutputItemStack()).attach(container);
          BNEIFakeSlotNode.create(70.0D, 0.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(140.0D, 0.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(210.0D, 0.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(0.0D, 70.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(70.0D, 70.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(140.0D, 70.0D, 60.0D).attach(container);
          BNEIFakeSlotNode.create(210.0D, 70.0D, 60.0D).attach(container);
        }).attach(parent);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\GolemBoxCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */