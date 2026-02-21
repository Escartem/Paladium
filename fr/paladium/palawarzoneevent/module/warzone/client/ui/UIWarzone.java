package fr.paladium.palawarzoneevent.module.warzone.client.ui;

import fr.paladium.chronos.server.runnables.PlannedStatued;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palawarzoneevent.module.largage.common.action.server.LargageServerAction;
import fr.paladium.palawarzoneevent.module.warzone.WarzoneModule;
import fr.paladium.palawarzoneevent.module.warzone.client.ui.handler.ClientPaginationHandler;
import fr.paladium.palawarzoneevent.module.warzone.client.ui.kit.SummerBackgroundNode;
import fr.paladium.palawarzoneevent.module.warzone.client.ui.kit.WarzoneLeaderboardEntryNode;
import fr.paladium.palawarzoneevent.module.warzone.common.action.server.WarzoneServerAction;
import fr.paladium.palawarzoneevent.module.warzone.common.data.CaptureLeaderboardData;
import fr.paladium.palawarzoneevent.module.warzone.common.data.LeaderboardEntry;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import javax.vecmath.Vector4f;
import net.minecraft.util.ResourceLocation;

@UIData(active = true)
@UIDataGuiscale(active = true)
public class UIWarzone extends UI {
  private static final Resource ALL_PLAYER_ICON = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/all_player_icon.png"));
  
  private static final Resource NEXT_LARGAGE_ICON = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/next_largage_icon.png"));
  
  private static final Resource RULE_ICON = Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/gui/warzone/rule_icon.png"));
  
  private static final TextInfo WARZONE_TITLE = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 40.0F, Color.decode("#6ED9FD")).shadow().shadow(Color.decode("#149CF7")).shadow(0.0F, 4.0F);
  
  private static final TextInfo TAB_TITLE = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 26.0F, Color.WHITE);
  
  private static final TextInfo TAB_SUBTITLE = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE);
  
  private static final TextInfo TAB_ITEM = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 24.0F, Color.WHITE);
  
  private static final int MAX_SIZE = 15;
  
  private final ClientPaginationHandler cpHandler = new ClientPaginationHandler();
  
  private final ListSignal<LeaderboardEntry> leaderboardSignal = new ListSignal(new ArrayList());
  
  private final Signal<PlannedStatued> nextLargageSignal = new Signal();
  
  public void init() {
    fetchLeaderBoard();
    SummerBackgroundNode.create(360.0D, 115.0D, 1200.0D, 850.0D)
      .borderSize(20.0D)
      .body(bg -> {
          TextNode.create(bg.dw(2.0D), 10.0D).text(Text.create("WARZONE", WARZONE_TITLE)).anchorX(Align.CENTER).attach(bg);
          RectNode.create(42.0D, 107.0D, 586.0D, 678.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(bg);
          RectNode.create(645.0D, 107.0D, 508.0D, 678.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(bg);
        }).attach(this);
  }
  
  private void fetchLeaderBoard() {
    this.cpHandler.queryData(() -> {
          CompletableFuture<CaptureLeaderboardData> completableLeaderboard = WarzoneServerAction.getLeaderboard(this.cpHandler.getIndex());
          if (completableLeaderboard == null) {
            (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue lors du chargement du classement", "Warzone")).send();
            ZUI.close(getUi());
            return;
          } 
          completableLeaderboard.whenComplete(());
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\clien\\ui\UIWarzone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */