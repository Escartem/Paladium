package fr.paladium.palamod.modules.alchimiste.client.ui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import lombok.NonNull;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;

public class EnchantNode extends RectNode {
  private Enchantment enchant = null;
  
  private final Color textGreen = new Color(94, 212, 42);
  
  private final TextInfo enchantTextInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, Color.WHITE);
  
  protected EnchantNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static EnchantNode create(double x, double y, double width, double height) {
    return new EnchantNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    this.enchantTextInfo.color(Color.WHITE.to(this.textGreen, hoverValue(1.0F)));
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.enchant == null)
      return; 
    TextNode.create(10.0D, dh(2.0D) - this.enchantTextInfo.getHeight() / 2.0D)
      .text(Text.create(I18n.func_135052_a(this.enchant.func_77320_a(), new Object[0]), this.enchantTextInfo).overflow(TextOverflow.DOT))
      .mode(TextMode.OVERFLOW)
      .width(getWidth())
      .attach((Node)this);
  }
  
  public <T extends EnchantNode> T enchant(Enchantment enchant) {
    this.enchant = enchant;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\clien\\ui\node\EnchantNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */