package fr.paladium.palamod.modules.luckyblock.profile.gui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProfileLuckyBlockNode extends Node {
  private ItemStack item;
  
  private Color color;
  
  private int count;
  
  private int total;
  
  protected ProfileLuckyBlockNode(double x, double y, double size) {
    super(x, y, size, size);
  }
  
  public static ProfileLuckyBlockNode create(double x, double y, double size) {
    return new ProfileLuckyBlockNode(x, y, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.item == null)
      return; 
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckyblock/hexa/background.png")).nearest());
    GLHelper.color((this.color == null) ? Color.WHITE : this.color);
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckyblock/hexa/foreground.png")));
    GLHelper.popColor();
    DrawUtils.ITEM.drawItem(getX() + dw(4.0D), getY() + dh(4.0D), dw(2.0D), this.item);
    DrawUtils.SHAPE.drawRect(getX() + dw(2.0D) - dw(6.0D), getY() + getHeight() * 0.8D, dw(3.0D), getHeight() * 0.23D, ((this.color == null) ? Color.WHITE : this.color).darker());
    DrawUtils.SHAPE.drawRect(getX() + dw(2.0D) - dw(6.0D), getY() + getHeight() * 0.8D, dw(3.0D), getHeight() * 0.2D, (this.color == null) ? Color.WHITE : this.color);
    DrawUtils.TEXT.drawText(getX() + dw(2.0D), getY() + getHeight() * 0.84D, Text.create("§l" + String.format("%.0f", new Object[] { Double.valueOf(Math.floor((this.count / this.total * 100.0F))) }) + "§r%", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE).shadow(Color.BLACK.copyAlpha(0.3F)), Align.CENTER));
  }
  
  @NonNull
  public final ProfileLuckyBlockNode data(@NonNull LuckyType type, int count, int total) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.item = LuckyType.Util.getIconFrom(type);
    this.color = new Color(LuckyType.Util.getColorFrom(type).getRGB());
    this.count = count;
    this.total = total;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\gui\node\ProfileLuckyBlockNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */