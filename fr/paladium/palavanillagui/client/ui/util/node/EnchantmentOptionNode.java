package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palavanillagui.client.ui.util.EnchantmentOption;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFont;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class EnchantmentOptionNode extends RectNode {
  private static ResourceLocation XP_BADGE = ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/xp_badge.png");
  
  private EnchantmentOption eOption = null;
  
  public EnchantmentOption getEOption() {
    return this.eOption;
  }
  
  private final Color textGreen = new Color(94, 212, 42);
  
  private final Color textWhite = Color.WHITE.copy();
  
  private final TextInfo numberInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 19.0F).color(this.textGreen);
  
  private final TextInfo galactifInfo = TextInfo.create(MinecraftFont.GALACTIC, 16.0F).color(this.textWhite);
  
  private int EOindex;
  
  public int getEOindex() {
    return this.EOindex;
  }
  
  protected EnchantmentOptionNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    hoveredColor(Color.WHITE);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    this.galactifInfo.color(this.textWhite.to(this.textGreen, hoverValue(1.0F)));
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          if (!isEnabled())
            return; 
          ImageNode.create(6.0D, getHeight() / 2.0D - 15.0D).resource(XP_BADGE).size(30.0D, 30.0D).attach((Node)this);
          TextNode.create(42.0D, dh(2.0D)).text(Text.create(Integer.valueOf(this.EOindex), this.numberInfo)).anchorY(Align.CENTER).attach((Node)this);
          TextNode.create(70.0D, 10.0D).mode(TextMode.OVERFLOW).text(Text.create(this.eOption.getLines().get(0), this.galactifInfo, TextOverflow.DOT)).width(230.0D).attach((Node)this);
          TextNode.create(70.0D, 30.0D).mode(TextMode.OVERFLOW).text(Text.create(this.eOption.getLines().get(1), this.galactifInfo, TextOverflow.DOT)).width(230.0D).attach((Node)this);
          TextNode.create(getWidth() - 12.0D, getHeight() - 30.0D).text(Text.create(Integer.valueOf(this.eOption.getLevel()), this.numberInfo)).anchorX(Align.END).attach((Node)this);
        });
  }
  
  public static EnchantmentOptionNode create(double x, double y, double width, double height) {
    return new EnchantmentOptionNode(x, y, width, height);
  }
  
  public <T extends EnchantmentOptionNode> T option(EnchantmentOption eOption) {
    this.eOption = eOption;
    return (T)this;
  }
  
  public <T extends EnchantmentOptionNode> T index(int i) {
    this.EOindex = i;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\EnchantmentOptionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */