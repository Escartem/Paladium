package fr.paladium.palarpg.module.dungeon.client.ui.ranking.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import lombok.NonNull;

public class DungeonRankingGlobalNode extends Node {
  private DungeonRankingData data;
  
  protected DungeonRankingGlobalNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonRankingGlobalNode create(double x, double y, double width, double height) {
    return new DungeonRankingGlobalNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    BackgroundElementNode.create(0.0D, 0.0D, w(), h())
      .color(Color.decode((this.data.getIndex() == 0) ? "#FFAE3C" : ((this.data.getIndex() == 1) ? "#8E8E8E" : ((this.data.getIndex() == 2) ? "#CF5600" : "#282828"))))
      .cornerSize(8.0D)
      .body(element -> {
          TextNode.create(19.0D, element.dh(2.0D)).text(Text.create(Integer.valueOf(this.data.getIndex() + 1), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 40.0F, Color.WHITE)).horizontalAlign(Align.CENTER).overflow(TextOverflow.DOT)).mode(TextMode.OVERFLOW).width(143.0D).anchorY(Align.CENTER).attach(element);
          HeadNode.create(192.0D, element.dh(2.0D) - 27.0D, 55.0D).player(this.data.getPlayerName()).attach(element);
          TextNode.create(270.0D, element.dh(2.0D)).text(Text.create(this.data.getPlayerName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 36.0F, Color.WHITE))).anchorY(Align.CENTER).attach(element);
          TextNode.create(1747.0D, element.dh(2.0D)).text(Text.create("palier " + (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format(this.data.getValue()).replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE))).anchorX(Align.END).anchorY(Align.CENTER).attach(element);
        }).attach(this);
  }
  
  @NonNull
  public DungeonRankingGlobalNode data(@NonNull DungeonRankingData data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\ranking\node\DungeonRankingGlobalNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */