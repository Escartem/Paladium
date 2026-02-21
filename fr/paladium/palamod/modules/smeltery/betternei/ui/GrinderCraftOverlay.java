package fr.paladium.palamod.modules.smeltery.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.api.uikit.CraftButtonNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.blueprint.client.betternei.ui.node.ShowStructureButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.ablueprint.BluePrintRegistry;
import fr.paladium.palamod.modules.smeltery.betternei.craft.GrinderCraft;
import fr.paladium.palamod.modules.smeltery.betternei.handler.GrinderCraftHandler;
import fr.paladium.palamod.modules.smeltery.ui.UiGrinder;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingArrowNode;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingFireNode;
import fr.paladium.palamod.modules.smeltery.ui.util.TankNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.item.ItemStack;

public class GrinderCraftOverlay extends UIItemOverlay<GrinderCraftHandler> {
  public GrinderCraftOverlay(GrinderCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    GrinderCraft gCraft = (GrinderCraft)craft;
    FlexNode.horizontal(25.0D, 180.0D, 40.0D)
      .margin(18.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 40.0D, 40.0D).resource(UiGrinder.BADGE_CRAFT).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create("craft", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F, Color.WHITE)).modifier(TextModifier.UPPER_CASE)).anchorY(Align.CENTER).attach(row);
          RectNode.create(0.0D, row.dh(2.0D) - 1.0D, 122.0D, 2.0D).color(Color.WHITE).attach(row);
        }).attach(parentNode);
    FlexNode.horizontal(360.0D, 180.0D, 40.0D)
      .margin(18.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 40.0D, 40.0D).resource(UiGrinder.BADGE_MELT).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create("melt", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F, Color.WHITE)).modifier(TextModifier.UPPER_CASE)).anchorY(Align.CENTER).attach(row);
          RectNode.create(0.0D, row.dh(2.0D) - 1.0D, 154.0D, 2.0D).color(Color.WHITE).attach(row);
        }).attach(parentNode);
    FlexNode.horizontal(25.0D, 460.0D, 40.0D)
      .margin(18.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 40.0D, 40.0D).resource(UiGrinder.BADGE_UPGRADE).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create("UPGRADE", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F, Color.WHITE)).modifier(TextModifier.UPPER_CASE)).anchorY(Align.CENTER).attach(row);
          RectNode.create(0.0D, row.dh(2.0D) - 1.0D, 78.0D, 2.0D).color(Color.WHITE).attach(row);
        }).attach(parentNode);
    BNEIFakeSlotNode.create(70.0D, 250.0D, 60.0D)
      .item(gCraft.getItemForSlot(1))
      .attach(parentNode);
    BNEIFakeSlotNode.create(70.0D, 350.0D, 60.0D)
      .item(gCraft.getItemForSlot(2))
      .attach(parentNode);
    LoadingArrowNode.create(145.0D, 300.0D, 89.0D, 60.0D)
      .thickness(12.0D)
      .loadingState(1.0D)
      .attach(parentNode);
    BNEIFakeSlotNode.create(254.0D, 300.0D, 60.0D)
      .item((gCraft.getCraftType() == GrinderCraft.GrinderCraftType.CRAFTING) ? gCraft.getOutputItemStack() : null)
      .attach(parentNode);
    BNEIFakeSlotNode.create(70.0D, 550.0D, 60.0D)
      .item(gCraft.getItemForSlot(3))
      .attach(parentNode);
    LoadingArrowNode.create(145.0D, 550.0D, 89.0D, 60.0D)
      .thickness(12.0D)
      .loadingState(1.0D)
      .attach(parentNode);
    BNEIFakeSlotNode.create(254.0D, 550.0D, 60.0D)
      .item((gCraft.getCraftType() == GrinderCraft.GrinderCraftType.UPGRADE) ? gCraft.getOutputItemStack() : null)
      .attach(parentNode);
    RectNode.create(360.0D, 240.0D, 300.0D, 300.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .body(rect -> {
          BNEIFakeSlotNode.create(20.0D, 20.0D, 60.0D).item(gCraft.getItemForSlot(5)).attach(rect);
          LoadingFireNode.create(24.5D, 100.0D, 53.0D, 53.0D).loadingState(1.0D).attach(rect);
          TankNode.create(110.0D, 20.0D, 164.0D, 226.0D).loadingState((gCraft.getPaladiumCost() / 100.0F)).body(()).attach(rect);
        }).attach(parentNode);
    ShowStructureButtonNode.create(600.0D, 559.0D, 36.0D)
      .blueprint(BluePrintRegistry.GRINDER.getId())
      .attach(parentNode);
    CraftButtonNode.create(600.0D, 605.0D, 36.0D)
      .attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\betterne\\ui\GrinderCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */