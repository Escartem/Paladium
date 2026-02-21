package fr.paladium.palarpg.module.dungeon.client.ui.choice.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class DungeonChoicePathHoverNode extends Node {
  private String title;
  
  private String description;
  
  private DungeonChoicePathHoverNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonChoicePathHoverNode create(double width, double height) {
    return new DungeonChoicePathHoverNode(20.0D, -10.0D, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    BackgroundNode.create(getX(), getY(), getWidth(), getHeight())
      .innerRadius(4.0D)
      .outerRadius(12.0D)
      .onInit(hover -> {
          TextNode.create(16.0D, 20.0D).text(Text.create(this.title.toUpperCase(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 16.0F, Color.WHITE).shadow(0.0F, 0.0F))).anchorY(Align.CENTER).attach(hover);
          RectNode.create(16.0D, 40.0D, hover.aw(-32.0D), 2.0D).color(new Color(10, 10, 10)).attach(hover);
          TextNode.create(16.0D, 42.0D + (hover.getHeight() - 42.0D) / 2.0D).text(Text.create(this.description, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 12.0F, Color.WHITE).shadow(0.0F, 0.0F).lineHeight(-1.0F))).mode(TextMode.SPLIT).width(hover.aw(-32.0D)).anchorY(Align.CENTER).attach(hover);
        }).attach(this);
  }
  
  @NonNull
  public DungeonChoicePathHoverNode title(@NonNull String title) {
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.title = title;
    return this;
  }
  
  @NonNull
  public DungeonChoicePathHoverNode description(@NonNull String description) {
    if (description == null)
      throw new NullPointerException("description is marked non-null but is null"); 
    this.description = description;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\choice\node\DungeonChoicePathHoverNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */