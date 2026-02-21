package fr.paladium.paladiumui.kit.selector;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import lombok.NonNull;

public class SelectorNode extends SelectorNode {
  protected SelectorNode(double x, double y, double width) {
    super(x, y, width, 54.0D);
  }
  
  @NonNull
  public static SelectorNode create(double x, double y, double width) {
    return new SelectorNode(x, y, width);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawFilledBorder(getX(), getY(), getX() + getWidth(), getY() + getHeight(), ColorConstant.LIGHT_GRAY);
    Resource icon = isActive() ? ResourceConstant.ARROW_UP : ResourceConstant.ARROW_DOWN;
    double iconWidth = getDefaultHeight() / 3.0D;
    double iconHeight = icon.getHeight() * iconWidth / icon.getWidth();
    DrawUtils.RESOURCE.drawScaledImageWidth(getX() + aw(-30.0D), getY() + getSelected().dh(2.0D) - iconHeight / 2.0D, iconWidth, icon);
  }
  
  public static class SelectorItemNode extends Node {
    private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 18.0F, ColorConstant.LIGHT_GRAY);
    
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
      zindex(-1);
    }
    
    @NonNull
    public static SelectorItemNode create() {
      return new SelectorItemNode(0.0D, 0.0D);
    }
    
    public void draw(double mouseX, double mouseY, float ticks) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), ColorConstant.BLACK.darker(hoverValue(0.5F)));
      if (this.text != null)
        DrawUtils.TEXT.drawText(getX() + 10.0D, getY() + dh(2.0D) - TEXT_INFO.dh(2.0D), Text.create(this.text.toUpperCase(), TEXT_INFO)); 
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


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\selector\SelectorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */