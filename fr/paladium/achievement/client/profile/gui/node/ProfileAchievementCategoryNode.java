package fr.paladium.achievement.client.profile.gui.node;

import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class ProfileAchievementCategoryNode extends Node {
  private AchievementCategory category;
  
  private int count;
  
  private int total;
  
  private boolean selected;
  
  protected ProfileAchievementCategoryNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileAchievementCategoryNode create(double x, double y, double width, double height) {
    return new ProfileAchievementCategoryNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(Color.BLACK.copyAlpha(0.2F))
      .border(this.selected ? Color.WHITE : Color.TRANSPARENT, 1.0D, true)
      .attach(this);
    ImageNode.create(9.0D, 9.0D, 48.0D, 48.0D)
      .resource(ResourceConstant.SLOT)
      .body(image -> ItemNode.create(5.0D, 5.0D, 38.0D).item(ItemStackSerializer.read64(this.category.getIcon())).stackSize(false).attach(image))
      
      .attach(this);
    TextNode.create(72.0D, 10.0D)
      .text(Text.create(this.category.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 18.0F, Color.WHITE)).overflow(TextOverflow.ELLIPSIS))
      .mode(TextMode.OVERFLOW)
      .width(aw(-150.0D))
      .attach(this);
    TextNode.create(aw(-15.0D), 7.0D)
      .text(Text.create(((this.total == 0) ? "0" : String.format("%.0f", new Object[] { Float.valueOf(this.count / this.total * 100.0F) })) + "%", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 13.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach(this);
    TextNode.create(72.0D, 41.0D)
      .text(Text.create(this.count + "/" + this.total, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE)))
      .attach(this);
    ProgressNode.create(72.0D + TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE).getWidth(this.count + "/" + this.total) + 10.0D, 46.0D, aw(-15.0D) - 72.0D + TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE).getWidth(this.count + "/" + this.total) + 10.0D, 12.0D)
      .padding(1.0D)
      .color(ColorConstant.GREEN)
      .progress(0.0F, this.total, this.count)
      .attach(this);
  }
  
  @NonNull
  public final ProfileAchievementCategoryNode data(@NonNull AchievementCategory category, int count, int total) {
    if (category == null)
      throw new NullPointerException("category is marked non-null but is null"); 
    this.category = category;
    this.count = count;
    this.total = total;
    return this;
  }
  
  @NonNull
  public final ProfileAchievementCategoryNode selected(boolean selected) {
    this.selected = selected;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\client\profile\gui\node\ProfileAchievementCategoryNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */