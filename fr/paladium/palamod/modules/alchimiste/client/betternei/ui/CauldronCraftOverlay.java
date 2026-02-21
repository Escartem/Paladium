package fr.paladium.palamod.modules.alchimiste.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.blueprint.client.betternei.ui.node.ShowStructureButtonNode;
import fr.paladium.blueprint.client.betternei.ui.node.StructureDisplayNode;
import fr.paladium.palamod.modules.ablueprint.BluePrintRegistry;
import fr.paladium.palamod.modules.alchimiste.client.betternei.handler.CauldronCraftHandler;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CauldronCraftOverlay extends UIItemOverlay<CauldronCraftHandler> {
  private static final Resource ARROW = Resource.of(new ResourceLocation("palamod", "textures/gui/alchimiste/arrow.png"));
  
  public CauldronCraftOverlay(CauldronCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parent, Craft craft) {
    craft.getSlotItem().forEach((slotId, stack) -> BNEIFakeSlotNode.create(40.0D + slotId.intValue() * 60.0D, 400.0D, 60.0D).item(stack).attach(parent));
    ImageNode.create(67.0D, 320.0D, 228.0D, 60.0D)
      .resource(ARROW)
      .hoverLines(() -> "Drop")
      .attach(parent);
    ImageNode.create(426.0D, 320.0D, 228.0D, 60.0D)
      .resource(ARROW)
      .hoverLines(() -> "Output")
      .attach(parent);
    StructureDisplayNode.create(parent.dw(2.0D) - 100.0D, 340.0D, 200.0D, 200.0D)
      .blueprint(BluePrintRegistry.CAULDRON_CORE.getId())
      .zoomable(false)
      .zoomLevel(1.5D)
      .rotating(false)
      .rotateAngle(45.0D)
      .attach(parent);
    BNEIFakeSlotNode.create(parent.getWidth() - 100.0D, 400.0D, 60.0D)
      .item(craft.getOutputItemStack())
      .attach(parent);
    ShowStructureButtonNode.create(640.0D, 220.0D, 36.0D)
      .blueprint(BluePrintRegistry.CAULDRON_CORE.getId())
      .attach(parent);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\betterne\\ui\CauldronCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */