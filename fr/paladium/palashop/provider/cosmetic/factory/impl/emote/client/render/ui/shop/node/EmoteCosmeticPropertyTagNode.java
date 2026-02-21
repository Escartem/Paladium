package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.shop.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class EmoteCosmeticPropertyTagNode extends Node {
  private String text;
  
  private Color color;
  
  protected EmoteCosmeticPropertyTagNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static EmoteCosmeticPropertyTagNode create(double x, double y, double height) {
    return new EmoteCosmeticPropertyTagNode(x, y, 0.0D, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    Text text = Text.create(this.text.toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, (float)dh(2.0D), Color.WHITE)).align(Align.CENTER, Align.CENTER);
    width(text.aw(getHeight() * 0.8D));
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(this.color)
      .effect((NodeEffect)RoundedNodeEffect.create(6.0F))
      .attach(this);
    TextNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .text(text)
      .attach(this);
  }
  
  @NonNull
  public EmoteCosmeticPropertyTagNode text(String text) {
    this.text = text;
    return this;
  }
  
  @NonNull
  public EmoteCosmeticPropertyTagNode color(Color color) {
    this.color = color;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\rende\\ui\shop\node\EmoteCosmeticPropertyTagNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */