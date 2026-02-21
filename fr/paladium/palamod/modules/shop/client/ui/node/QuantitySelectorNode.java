package fr.paladium.palamod.modules.shop.client.ui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.IntegerFieldNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import net.minecraft.client.gui.GuiScreen;

public class QuantitySelectorNode extends Node {
  private final IntegerSignal quantitySignal = new IntegerSignal(1);
  
  protected QuantitySelectorNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    body(() -> {
          TextNode.create(0.0D, 0.0D).text(Text.create("QUANTITÉ", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 16.0F, Color.WHITE))).attach(this);
          ((IntegerFieldNode)((IntegerFieldNode)IntegerFieldNode.create(0.0D, 33.0D, 241.0D, 72.0D).backgroundColor(new Color(0, 0, 0, 0)).borderColor(Color.WHITE).focusColor(Color.WHITE).min(1).marginLeft(23.0D).marginRight(90.0D).info(TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F, Color.WHITE)).onInit(())).onChange(())).watch((Signal)this.quantitySignal).attach(this);
          TextNode.create(180.0D, 69.0D).mode(TextMode.BOX).text(Text.create("+", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 35.0F, Color.WHITE), Align.CENTER, Align.CENTER)).anchor(Align.CENTER, Align.CENTER).zindex(1).size(40.0D, 71.0D).onClick(()).hoverLines(()).attach(this);
          TextNode.create(220.0D, 69.0D).mode(TextMode.BOX).text(Text.create("-", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F, Color.WHITE), Align.CENTER, Align.CENTER)).anchor(Align.CENTER, Align.CENTER).size(40.0D, 71.0D).zindex(1).onClick(()).hoverLines(()).attach(this);
        });
  }
  
  private void addQuantity(int value) {
    this.quantitySignal.add(value);
  }
  
  private void subQuantity(int value) {
    int tmpValue = ((Integer)this.quantitySignal.getOrDefault()).intValue() - value;
    if (tmpValue < 1)
      tmpValue = 1; 
    this.quantitySignal.set(Integer.valueOf(tmpValue));
  }
  
  public IntegerSignal getQuantitySignal() {
    return this.quantitySignal;
  }
  
  public int getQuantity() {
    return ((Integer)this.quantitySignal.getOrDefault()).intValue();
  }
  
  public static QuantitySelectorNode create(double x, double y, double width, double height) {
    return new QuantitySelectorNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\node\QuantitySelectorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */