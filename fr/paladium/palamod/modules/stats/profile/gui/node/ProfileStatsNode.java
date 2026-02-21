package fr.paladium.palamod.modules.stats.profile.gui.node;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ProfileStatsNode extends Node {
  private ResourceLocation icon;
  
  private String text;
  
  private Number value;
  
  protected ProfileStatsNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileStatsNode create(double x, double y, double width, double height) {
    return new ProfileStatsNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ImageNode.create(getWidth() * 0.2D, 0.0D, getWidth() * 0.6D, getWidth() * 0.6D)
      .resource(this.icon)
      .linear(false)
      .attach(this);
    TextNode.create(dw(2.0D), getWidth() * 0.68D)
      .text(Text.create(this.text.toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(dw(2.0D), getWidth() * 0.85D)
      .text(Text.create(this.value.toString(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, ColorConstant.PRIMARY)))
      .anchorX(Align.CENTER)
      .attach(this);
  }
  
  @NonNull
  public final ProfileStatsNode data(@NonNull ResourceLocation icon, @NonNull String text, @NonNull Number value) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    this.icon = icon;
    this.text = text;
    this.value = value;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\profile\gui\node\ProfileStatsNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */