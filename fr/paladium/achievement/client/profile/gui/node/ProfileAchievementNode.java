package fr.paladium.achievement.client.profile.gui.node;

import fr.paladium.achievement.client.profile.gui.UIProfileAchievements;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.profile.dto.PlayerAchievement;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
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
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProfileAchievementNode extends Node {
  private Achievement achivement;
  
  private PlayerAchievement progress;
  
  protected ProfileAchievementNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileAchievementNode create(double x, double y, double width, double height) {
    return new ProfileAchievementNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    int count = this.progress.getProgress();
    int total = this.achivement.getNbToValidate();
    if (!this.achivement.getSubAchievements().isEmpty()) {
      count = 0;
      total = this.achivement.getSubAchievements().size();
      Map<String, PlayerAchievement> progresses = ((UIProfileAchievements)getUi()).getAchievements();
      for (Achievement subAchievement : this.achivement.getSubAchievements()) {
        if (!progresses.containsKey(subAchievement.getId()))
          continue; 
        PlayerAchievement subProgress = progresses.get(subAchievement.getId());
        if (!subProgress.isCompleted())
          continue; 
        count++;
      } 
    } 
    boolean completed = (count >= total);
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(completed ? new Color(28, 37, 31) : Color.BLACK.copyAlpha(0.2F))
      .border(completed ? ColorConstant.GREEN : Color.TRANSPARENT, 1.0D, true)
      .attach(this);
    ItemNode.create(34.0D, dh(2.0D) - 24.0D, 48.0D)
      .item((this.achivement.getIcon() == null) ? new ItemStack(Blocks.field_150348_b) : ItemStackSerializer.read64(this.achivement.getIcon()))
      .stackSize(false)
      .color(completed ? Color.WHITE.copyAlpha(0.2F) : Color.WHITE)
      .zlevel(50.0D)
      .attach(this);
    TextNode.create(124.0D, 17.0D)
      .text(Text.create(this.achivement.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE)))
      .attach(this);
    TextNode.create(124.0D, 48.0D)
      .text(Text.create(this.achivement.getDescription().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 13.0F, Color.WHITE)).overflow(TextOverflow.ELLIPSIS))
      .mode(TextMode.OVERFLOW)
      .width(aw(-141.0D))
      .attach(this);
    TextNode.create(aw(-17.0D) - 165.0D - 20.0D, 10.0D)
      .text(Text.create(count + "/" + total, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 13.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach(this);
    ProgressNode.create(aw(-17.0D) - 165.0D, 17.0D, 165.0D, 12.0D)
      .padding(1.0D)
      .color(ColorConstant.GREEN)
      .progress(0.0F, total, count)
      .attach(this);
  }
  
  @NonNull
  public final ProfileAchievementNode data(@NonNull Achievement achivement, @NonNull PlayerAchievement progress) {
    if (achivement == null)
      throw new NullPointerException("achivement is marked non-null but is null"); 
    if (progress == null)
      throw new NullPointerException("progress is marked non-null but is null"); 
    this.achivement = achivement;
    this.progress = progress;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\client\profile\gui\node\ProfileAchievementNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */