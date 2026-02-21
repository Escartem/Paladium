package fr.paladium.palashop.client.ui.kit.selector;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import lombok.NonNull;

public class SelectorNode extends SelectorNode {
  protected SelectorNode(double x, double y, double width) {
    super(x, y, width, 49.0D);
  }
  
  @NonNull
  public static SelectorNode create(double x, double y, double width) {
    return new SelectorNode(x, y, width);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRoundedRect(getX(), getY(), getWidth(), getHeight(), ColorConstant.LIGHT_DARK, 6.0F);
    Resource resource = isActive() ? ResourceConstant.ICON_ARROW_UP : ResourceConstant.ICON_ARROW_DOWN;
    double iconWidth = 10.0D;
    double iconHeight = resource.getHeight() * 10.0D / resource.getWidth();
    double iconMargin = 10.0D;
    double iconX = getX() + getWidth() + 9.5D - 29.0D - 10.0D;
    double iconY = getY() + (getDefaultHeight() - iconHeight) / 2.0D;
    DrawUtils.SHAPE.drawRoundedRect(iconX + 5.0D - 14.0D, iconY + iconHeight / 2.0D - 14.0D, 29.0D, 29.0D, ColorConstant.PRIMARY, 6.0F);
    DrawUtils.RESOURCE.drawScaledImageWidth(iconX, iconY, 10.0D, resource);
  }
  
  public static class SelectorItemNode extends Node {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\selector\SelectorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */