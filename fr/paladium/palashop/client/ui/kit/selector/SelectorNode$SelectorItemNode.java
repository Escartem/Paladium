package fr.paladium.palashop.client.ui.kit.selector;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public class SelectorItemNode extends Node {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE);
  
  private String text;
  
  private String id;
  
  public String getText() {
    return this.text;
  }
  
  public String getId() {
    return this.id;
  }
  
  protected SelectorItemNode(double x, double y) {
    super(x, y);
  }
  
  @NonNull
  public static SelectorItemNode create() {
    return new SelectorItemNode(0.0D, 0.0D);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.text == null)
      return; 
    boolean selected = (getParent() instanceof SelectorNode && ((SelectorNode)getParent()).getSelected() == this);
    DrawUtils.TEXT.drawText(getX() + 10.0D, getY() + dh(2.0D) - TEXT_INFO.dh(2.0D), Text.create(this.text, TEXT_INFO.copy().color(TEXT_INFO.getColor().to(ColorConstant.PRIMARY, selected ? 0.0F : hoverValue(1.0F)))).modifier(TextModifier.CAPITALIZE));
  }
  
  @NonNull
  public <T extends SelectorItemNode> T text(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    this.text = text;
    if (this.id == null)
      this.id = text; 
    return (T)this;
  }
  
  @NonNull
  public <T extends SelectorItemNode> T id(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    this.id = id;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\selector\SelectorNode$SelectorItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */