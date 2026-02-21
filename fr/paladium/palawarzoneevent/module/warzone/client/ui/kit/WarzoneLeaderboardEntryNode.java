package fr.paladium.palawarzoneevent.module.warzone.client.ui.kit;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.format.FormatUtils;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class WarzoneLeaderboardEntryNode extends Node {
  private static final Resource FIRST_LOGO_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_first_place.png"));
  
  private static final Resource SECOND_LOGO_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_second_place.png"));
  
  private static final Resource THIRD_LOGO_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_third_place.png"));
  
  private static final Resource FIRST_LONG_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_first_place_long.png"));
  
  private static final Resource SECOND_LONG_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_second_place_long.png"));
  
  private static final Resource THIRD_LONG_BG = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/leaderboard_third_place_long.png"));
  
  private static final TextInfo INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 24.0F, Color.WHITE);
  
  private Color backgroundColor = Color.BLACK.copyAlpha(0.3F);
  
  private int position = 0;
  
  private String factionName = "";
  
  private long points = 0L;
  
  protected WarzoneLeaderboardEntryNode(double x, double y) {
    super(x, y, 508.0D, 53.0D);
  }
  
  public static WarzoneLeaderboardEntryNode create(double x, double y) {
    return new WarzoneLeaderboardEntryNode(x, y);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    Consumer<Node> longConsumer = node -> {
        TextNode.create(18.0D, node.dh(2.0D)).text(Text.create(this.factionName.toUpperCase(), INFO)).anchorY(Align.CENTER).attach(node);
        TextNode.create(node.aw(-25.0D), node.dh(2.0D)).text(Text.create(FormatUtils.formatNumber(this.points), INFO)).anchor(Align.END, Align.CENTER).hover(()).attach(node);
      };
    if (this.position >= 1 && this.position <= 3) {
      ImageNode.create(0.0D, 0.0D, 508.0D, 53.0D)
        .resource((this.position == 1) ? FIRST_LONG_BG : ((this.position == 2) ? SECOND_LONG_BG : THIRD_LONG_BG))
        .body(longConsumer)
        .attach(this);
    } else {
      RectNode.create(0.0D, 0.0D, 508.0D, 53.0D)
        .color(this.backgroundColor)
        .body(longConsumer)
        .attach(this);
    } 
  }
  
  public WarzoneLeaderboardEntryNode position(int position) {
    this.position = position;
    return this;
  }
  
  public WarzoneLeaderboardEntryNode factionName(String factionName) {
    this.factionName = factionName;
    return this;
  }
  
  public WarzoneLeaderboardEntryNode points(long points) {
    this.points = points;
    return this;
  }
  
  public WarzoneLeaderboardEntryNode backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\clien\\ui\kit\WarzoneLeaderboardEntryNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */