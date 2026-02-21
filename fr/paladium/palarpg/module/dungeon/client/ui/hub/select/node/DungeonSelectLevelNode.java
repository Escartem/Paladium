package fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.select.UIDungeonSelect;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
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
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class DungeonSelectLevelNode extends Node {
  private DungeonLevelConfig level;
  
  private boolean infinite;
  
  protected DungeonSelectLevelNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonSelectLevelNode create(double x, double y, double width, double height) {
    return new DungeonSelectLevelNode(x, y, width, height);
  }
  
  public void init(UI rawUi) {
    if (this.level == null)
      throw new IllegalStateException("Level cannot be null"); 
    UIDungeonSelect ui = (UIDungeonSelect)rawUi;
    if (ui.getSelectedDungeon().getOrDefault() == null || ui.getSelectedLevel().getOrDefault() == null)
      return; 
    ((BackgroundElementNode)BackgroundElementNode.create(0.0D, 0.0D, w(), h())
      .cornerSize(5.0D)
      .onInit(node -> {
          DungeonWorld world = (DungeonWorld)ui.getWorld().getOrDefault();
          if (!ui.getSelectedDungeon().isPresent() || !ui.getSelectedLevel().isPresent())
            return; 
          boolean selected = ((DungeonLevelConfig)ui.getSelectedLevel().getOrDefault()).equals(this.level);
          boolean enabled = (world.getMaxLevel((DungeonConfig)ui.getSelectedDungeon().getOrDefault()) >= this.level.getLevel());
          Color textColor = !enabled ? new Color(31, 31, 31) : (selected ? Color.WHITE : new Color(106, 106, 106));
          node.color(!enabled ? new Color(18, 18, 18) : (selected ? new Color(40, 40, 40) : new Color(31, 31, 31)));
          node.shadow(0.0F, (selected && enabled) ? 4.0F : 0.0F);
          FlexNode.horizontal(21.0D, node.dh(2.0D) - 10.0D, 20.0D).margin(14.0D).align(Align.CENTER).body(()).attach((Node)node);
          TextNode.create(node.aw(-21.0D), node.dh(2.0D)).text(Text.create(RomanNumberFormatter.format(this.level.getLevel() + 1), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, textColor))).anchorX(Align.END).anchorY(Align.CENTER).attach((Node)node);
        })).attach(this);
  }
  
  @NonNull
  public DungeonSelectLevelNode level(@NonNull DungeonLevelConfig level, boolean infinite) {
    if (level == null)
      throw new NullPointerException("level is marked non-null but is null"); 
    this.level = level;
    this.infinite = infinite;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\select\node\DungeonSelectLevelNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */