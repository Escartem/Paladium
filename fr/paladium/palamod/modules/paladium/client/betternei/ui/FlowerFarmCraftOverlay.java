package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.FlowerFarmCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UIFlowerFarm;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.item.ItemStack;

public class FlowerFarmCraftOverlay extends UIItemOverlay<FlowerFarmCraftHandler> {
  public FlowerFarmCraftOverlay(FlowerFarmCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parent, Craft craft) {
    double marginX = parent.dw(2.0D) - 266.5D;
    ImageNode.create(marginX + 150.0D + 101.5D - UIFlowerFarm.ARROW.getWidth() / 2.0D, 355.0D)
      .resource(UIFlowerFarm.ARROW)
      .attach(parent);
    TextNode.create(marginX + 75.0D, 260.0D)
      .text(Text.create("Carburant", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 27.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(parent);
    BNEIFakeSlotNode.create(marginX, 300.0D, 150.0D)
      .item(craft.getItemForSlot(0))
      .attach(parent);
    double x = 436.0D;
    double y = 285.0D;
    for (int i = 0; i < 9; i++)
      BNEIFakeSlotNode.create(436.0D + 60.0D * (i % 3), 285.0D + 60.0D * Math.floor(i / 3.0D), 60.0D)
        .item((i == 0) ? craft.getOutputItemStack() : null)
        .attach(parent); 
    double yProg = 500.0D;
    ProgressNode.create(marginX, 500.0D, 533.0D, 62.0D)
      .progress(0.0F)
      .color(new Color(90, 212, 1), new Color(50, 50, 50))
      .body(prog -> TextNode.create(prog.dw(2.0D), 15.0D).text(Text.create("100%", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 27.0F, Color.WHITE))).anchorX(Align.CENTER).attach(prog))
      
      .attach(parent);
    ProgressNode.create(marginX, 562.0D, 533.0D, 6.0D)
      .progress(0.0F)
      .color(new Color(36, 83, 32), new Color(26, 26, 26))
      .attach(parent);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\FlowerFarmCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */