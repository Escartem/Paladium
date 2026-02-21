package fr.paladium.palaclicker.client.ui.node.left;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import lombok.NonNull;

public class ClickerCursorHitNode extends TextNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 28.0F).color(Color.WHITE).shadow().shadow(0.0F, 4.0F);
  
  private double value;
  
  private long creationTime;
  
  private long lifespan;
  
  protected ClickerCursorHitNode(double x, double y) {
    super(x, y, 0.0D, 0.0D);
    this.creationTime = System.currentTimeMillis();
    this.lifespan = 1500L;
  }
  
  public static ClickerCursorHitNode create(double x, double y) {
    return new ClickerCursorHitNode(x, y);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    text(Text.create("+" + UIClicker.formatBigNumber(Double.valueOf(this.value)), TEXT_INFO));
    super.init(ui);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    text(Text.create("+" + UIClicker.formatBigNumber(Double.valueOf(this.value)), TEXT_INFO));
    long currentTime = System.currentTimeMillis();
    long diff = currentTime - this.creationTime;
    if (diff > this.lifespan) {
      getParent().getChildren().remove((IndexedElement)this);
      return;
    } 
    y(getDefaultY() - (diff / 10L));
    if (this.lifespan - diff < 1000L) {
      Color newColor = new Color(TEXT_INFO.getColor());
      newColor.a = (float)(this.lifespan - diff) / 1000.0F;
      text(Text.create("+" + UIClicker.formatBigNumber(Double.valueOf(this.value)), TEXT_INFO.copy().color(newColor).shadow()));
    } 
    super.draw(mouseX, mouseY, ticks);
  }
  
  public ClickerCursorHitNode creationTime(long creationTime) {
    this.creationTime = creationTime;
    return this;
  }
  
  public ClickerCursorHitNode lifespan(long lifespan) {
    this.lifespan = lifespan;
    return this;
  }
  
  public ClickerCursorHitNode value(double value) {
    this.value = value;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\left\ClickerCursorHitNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */