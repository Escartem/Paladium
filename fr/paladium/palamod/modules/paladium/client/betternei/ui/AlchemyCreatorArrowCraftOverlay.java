package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.api.uikit.CraftButtonNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.AlchemyCreatorArrowCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UIAlchemyCreatorBase;
import fr.paladium.palamod.modules.paladium.client.ui.util.ThreeToOneBarNode;
import fr.paladium.palamod.modules.smeltery.ui.util.ArrowPointing;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingArrowNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import net.minecraft.item.ItemStack;

public class AlchemyCreatorArrowCraftOverlay extends UIItemOverlay<AlchemyCreatorArrowCraftHandler> {
  public AlchemyCreatorArrowCraftOverlay(AlchemyCreatorArrowCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    ImageNode.create(70.0D, 270.0D, 94.0D, 234.0D)
      .resource(UIAlchemyCreatorBase.getBubbleForeground())
      .attach(parentNode);
    BNEIFakeSlotNode.create(210.0D, 270.0D, 60.0D)
      .item(craft.getItemForSlot(4))
      .attach(parentNode);
    BNEIFakeSlotNode.create(318.0D, 270.0D, 60.0D)
      .item(craft.getItemForSlot(5))
      .attach(parentNode);
    BNEIFakeSlotNode.create(425.0D, 270.0D, 60.0D)
      .item(craft.getItemForSlot(6))
      .attach(parentNode);
    ThreeToOneBarNode.create(parentNode.dw(2.0D) - 114.0D, 330.0D, 228.0D, 75.0D)
      .regroupHeight(0.5D)
      .thickness(15.0D)
      .attach(parentNode);
    BNEIFakeSlotNode.create(parentNode.dw(2.0D) - 30.0D, 405.0D, 60.0D)
      .item(craft.getItemForSlot(7))
      .attach(parentNode);
    RectNode.create(parentNode.dw(2.0D) - 7.5D, 465.0D, 15.0D, 31.0D)
      .color(new Color(64, 64, 78))
      .attach(parentNode);
    BNEIFakeSlotNode.create(parentNode.dw(2.0D) - 30.0D, 496.0D, 60.0D)
      .item(craft.getOutputItemStack())
      .attach(parentNode);
    LoadingArrowNode.create(540.0D, 330.0D, 66.0D, 188.0D)
      .thickness(12.0D)
      .point(ArrowPointing.DOWN)
      .loadingState(1.0D)
      .attach(parentNode);
    CraftButtonNode.create(480.0D, 516.0D, 36.0D)
      .attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\AlchemyCreatorArrowCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */