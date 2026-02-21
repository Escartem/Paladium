package fr.paladium.palamod.modules.paladium.client.ui.util;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.ui.UICrusher;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import lombok.NonNull;

public class CrusherFuelTankContainer extends RectNode {
  private final TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 24.0F);
  
  protected CrusherFuelTankContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    UICrusher uiCrush = (UICrusher)getUi();
    ListSignal<Integer> fuelTankSignal = uiCrush.getFuelTank();
    double x = getWidth() - 20.0D;
    ItemNode.create(15.0D, 13.0D, 35.0D)
      .item(ItemsRegister.GOLDMIXEDCOAL)
      .attach((Node)this);
    ItemNode.create(15.0D, 52.0D, 35.0D)
      .item(ItemsRegister.AMETHYSTMIXEDCOAL)
      .attach((Node)this);
    ItemNode.create(15.0D, 92.0D, 35.0D)
      .item(ItemsRegister.TITANEMIXEDCOAL)
      .attach((Node)this);
    ItemNode.create(15.0D, 132.0D, 35.0D)
      .item(ItemsRegister.PALAMIXEDCOAL)
      .attach((Node)this);
    ((TextNode)TextNode.create(x, 15.0D)
      .text(Text.create(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.AMETHYST.ordinal()) + "/64", this.textInfo.copy().color(new Color(253, 245, 93))))
      .anchorX(Align.END)
      .onInit(node -> node.getText().text(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.AMETHYST.ordinal()) + "/64")))
      .watch((Signal)fuelTankSignal)
      .attach((Node)this);
    ((TextNode)TextNode.create(x, 55.0D)
      .text(Text.create(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.TITANE.ordinal()) + "/64", this.textInfo.copy().color(new Color(183, 30, 255))))
      .anchorX(Align.END)
      .onInit(node -> node.getText().text(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.TITANE.ordinal()) + "/64")))
      .watch((Signal)fuelTankSignal)
      .attach((Node)this);
    ((TextNode)TextNode.create(x, 95.0D)
      .text(Text.create(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.PALADIUM.ordinal()) + "/64", this.textInfo.copy().color(new Color(165, 165, 165))))
      .anchorX(Align.END)
      .onInit(node -> node.getText().text(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.PALADIUM.ordinal()) + "/64")))
      .watch((Signal)fuelTankSignal)
      .attach((Node)this);
    ((TextNode)TextNode.create(x, 135.0D)
      .text(Text.create(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.ENDIUM.ordinal()) + "/64", this.textInfo.copy().color(new Color(239, 57, 38))))
      .anchorX(Align.END)
      .onInit(node -> node.getText().text(fuelTankSignal.get(TileCrusher.EnumCrusherRecipes.ENDIUM.ordinal()) + "/64")))
      .watch((Signal)fuelTankSignal)
      .attach((Node)this);
  }
  
  public static CrusherFuelTankContainer create(double x, double y, double width, double height) {
    return new CrusherFuelTankContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\CrusherFuelTankContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */