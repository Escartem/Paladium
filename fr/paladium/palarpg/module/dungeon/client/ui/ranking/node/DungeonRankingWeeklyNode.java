package fr.paladium.palarpg.module.dungeon.client.ui.ranking.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonRankingConfig;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import lombok.NonNull;

public class DungeonRankingWeeklyNode extends Node {
  private DungeonRankingData data;
  
  private DungeonRankingData previous;
  
  private DungeonRankingConfig config;
  
  protected DungeonRankingWeeklyNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonRankingWeeklyNode create(double x, double y, double width, double height) {
    return new DungeonRankingWeeklyNode(x, y, width, height);
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
          TextNode.create(1530.0D, element.dh(2.0D)).text(Text.create((new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format(this.data.getValue()).replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE)).horizontalAlign(Align.CENTER).overflow(TextOverflow.ELLIPSIS)).mode(TextMode.OVERFLOW).anchorY(Align.CENTER).width(217.0D).attach(element);
          FlexNode.horizontal(1488.0D, element.dh(2.0D) - 27.0D, 55.0D).margin(9.0D).align(Align.CENTER).body(()).anchorX(Align.END).attach(element);
        }).attach(this);
    if (this.previous != null && ((
      this.previous.getIndex() == 9 && this.data.getIndex() == 10) || (this.previous.getValue() >= this.config.getNeededExperience() && this.data.getValue() < this.config.getNeededExperience()))) {
      RectNode.create(-12.0D, -9.5D, aw(24.0D), 4.0D)
        .color(Color.decode("#EF3926"))
        .attach(this);
      RectNode.create(-12.0D, -5.5D, aw(24.0D), 2.0D)
        .color(Color.decode("#871D11"))
        .attach(this);
      RectNode.create(dw(2.0D) - 150.0D, -36.5D, 300.0D, 64.0D)
        .color(Color.decode("#871D12"))
        .attach(this);
      RectNode.create(dw(2.0D) - 150.0D, -36.5D, 300.0D, 60.0D)
        .color(Color.decode("#EF3926"))
        .body(rect -> {
            String text = "";
            if (this.previous.getIndex() == 9 && this.data.getIndex() == 10) {
              text = "palier récompenses top 10";
            } else if (this.previous.getValue() >= this.config.getNeededExperience() && this.data.getValue() < this.config.getNeededExperience()) {
              text = (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format(this.config.getNeededExperience()).replace(" ", ".") + " xp\npalier récompenses";
            } 
            TextNode.create(rect.dw(2.0D), rect.dh(2.0D)).text(Text.create(text, TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 14.0F, Color.WHITE)).horizontalAlign(Align.CENTER)).mode(TextMode.SPLIT).anchor(Align.CENTER).width(280.0D).attach(rect);
          }).attach(this);
    } 
  }
  
  @NonNull
  public DungeonRankingWeeklyNode data(@NonNull DungeonRankingData data, DungeonRankingData previous, @NonNull DungeonRankingConfig config) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    this.data = data;
    this.previous = previous;
    this.config = config;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\ranking\node\DungeonRankingWeeklyNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */