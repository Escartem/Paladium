package fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node;

import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class DungeonSelectLevelRewardsNode extends Node {
  private DungeonLevelConfig level;
  
  private boolean infinite;
  
  private ListSignal<DungeonLevelConfig> rewardsSignal;
  
  protected DungeonSelectLevelRewardsNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonSelectLevelRewardsNode create(double x, double y, double width, double height) {
    return new DungeonSelectLevelRewardsNode(x, y, width, height);
  }
  
  public void init(UI rawUi) {
    if (this.level == null)
      throw new IllegalStateException("Level cannot be null"); 
    ((BackgroundElementNode)BackgroundElementNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .cornerSize(5.0D)
      .shadow(0.0F, 4.0F)
      .body(element -> {
          TextNode.create(21.0D, element.dh(2.0D)).text(Text.create("palier", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, Color.WHITE))).anchorY(Align.CENTER).attach((Node)element);
          if (this.infinite) {
            ImageNode.create(124.0D, element.dh(2.0D)).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/select/infinite.png")).linear()).height(14.0D).hover(()).anchorY(Align.CENTER).attach((Node)element);
          } else {
            TextNode.create(124.0D, element.dh(2.0D)).text(Text.create(RomanNumberFormatter.format(this.level.getLevel() + 1), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, Color.WHITE))).anchorY(Align.CENTER).attach((Node)element);
          } 
          ((IconButtonNode)IconButtonNode.create(element.aw(-21.0D) - 15.0D, element.dh(2.0D) - 4.0D - 7.0D, 15.0D).onInit(())).onClick(()).hover(()).watch((Signal)this.rewardsSignal).attach((Node)element);
        })).attach(this);
  }
  
  @NonNull
  public DungeonSelectLevelRewardsNode level(@NonNull DungeonLevelConfig level, boolean infinite) {
    if (level == null)
      throw new NullPointerException("level is marked non-null but is null"); 
    this.level = level;
    this.infinite = infinite;
    return this;
  }
  
  @NonNull
  public DungeonSelectLevelRewardsNode rewards(@NonNull ListSignal<DungeonLevelConfig> rewardsSignal) {
    if (rewardsSignal == null)
      throw new NullPointerException("rewardsSignal is marked non-null but is null"); 
    this.rewardsSignal = rewardsSignal;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\select\node\DungeonSelectLevelRewardsNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */