package fr.paladium.palarpg.module.dungeon.client.ui.hub;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.client.ui.kit.separator.GradientSeparatorNode;
import fr.paladium.palarpg.client.ui.kit.textfield.TextFieldNode;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.select.UIDungeonSelect;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubInvitePlayer;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubKickPlayer;
import fr.paladium.palarpg.module.dungeon.common.network.hub.BBPacketRPGDungeonHubSearchPlayer;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

@UIData(anchorX = Align.END)
public class UIDungeonHub extends UI {
  private final boolean leader;
  
  private final List<DungeonConfig> dungeons;
  
  private final Signal<DungeonWorld> world;
  
  private final ListSignal<BBPacketRPGDungeonHubSearchPlayer.BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement> searchResultSignal;
  
  private long lastSearchUpdate;
  
  private String lastSearchText;
  
  public boolean isLeader() {
    return this.leader;
  }
  
  public List<DungeonConfig> getDungeons() {
    return this.dungeons;
  }
  
  public Signal<DungeonWorld> getWorld() {
    return this.world;
  }
  
  public ListSignal<BBPacketRPGDungeonHubSearchPlayer.BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement> getSearchResultSignal() {
    return this.searchResultSignal;
  }
  
  public long getLastSearchUpdate() {
    return this.lastSearchUpdate;
  }
  
  public String getLastSearchText() {
    return this.lastSearchText;
  }
  
  public UIDungeonHub(@NonNull UIDungeonHubData data) {
    this(data.leader, data.dungeons);
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
  }
  
  public UIDungeonHub(boolean leader, @NonNull List<DungeonConfig> dungeons) {
    if (dungeons == null)
      throw new NullPointerException("dungeons is marked non-null but is null"); 
    this.leader = leader;
    this.dungeons = dungeons;
    this.world = Signal.of(DungeonWorld.getClient().get());
    this.searchResultSignal = new ListSignal(new ArrayList());
  }
  
  public void init() {
    setTransition(new UIDungeonHubTransition());
    BackgroundNode.create(1039.0D, 28.0D, 909.0D, 1024.0D)
      .attach(this);
    TextNode.create(1084.0D, 38.0D)
      .text(Text.create("gestion du hub", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.decode("#F6F9E8"))))
      .attach(this);
    CloseButtonNode.create(1861.0D, 88.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    TextNode.create(1089.0D, 172.0D)
      .text(Text.create("donjon choisi", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 27.0F, Color.WHITE)))
      .attach(this);
    ((ImageNode)ImageNode.create(1089.0D, 234.0D, 797.0D, 209.0D)
      .stretch(ImageNode.StretchType.CONTAIN)
      .onInit(node -> {
          DungeonConfig dungeon = ((DungeonWorld)this.world.getOrDefault()).getDungeon();
          if (dungeon == null) {
            node.resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/hub/default_banner.png")).linear());
            return;
          } 
          node.resource(Resource.of(dungeon.getBanner()).linear());
        })).body(rect -> {
          ((GradientSeparatorNode)GradientSeparatorNode.horizontal(0.0D, 0.0D, rect.getWidth(), 3.0D).body(())).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY, WatchProperty.RELOAD }).attach(rect);
          ((GradientSeparatorNode)GradientSeparatorNode.horizontal(0.0D, rect.ah(-3.0D), rect.getWidth(), 3.0D).body(())).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY, WatchProperty.RELOAD }).attach(rect);
          ((BackgroundElementNode)BackgroundElementNode.create(-18.0D, 16.0D, 452.0D, 64.0D).cornerSize(6.0D).shadowY(4.0F).onInit(())).visible(()).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach(rect);
          BackgroundElementNode.create(rect.aw(-159.0D), 22.0D, 170.0D, 52.0D).cornerSize(5.0D).shadowY(4.0F).body(()).visible(()).attach(rect);
          ((TextButtonNode)TextButtonNode.create(23.0D, 139.0D).onInit(())).onClick(()).watch(this.world).visible(()).attach(rect);
        }).watch(this.world)
      .attach(this);
    TextNode.create(1089.0D, 486.0D)
      .text(Text.create("hub actuel", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 27.0F, Color.WHITE)))
      .attach(this);
    FlexNode.vertical(1089.0D, 555.0D, 797.0D)
      .margin(11.0D)
      .body(flex -> {
          for (int i = 0; i < Math.min(4, 4); i++) {
            int index = i;
            RectNode.create(0.0D, 0.0D, flex.getWidth(), 39.0D).color(new Color(23, 23, 23)).body(()).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(flex);
          } 
        }).attach(this);
    ((TextNode)TextNode.create(1886.0D, 486.0D)
      .onInit(node -> node.text(Text.create(((DungeonWorld)this.world.getOrDefault()).getPlayers().size() + "/" + '\004', TextInfo.create((IFont)PaladiumFont.MINECRAFT, 27.0F, Color.WHITE)))))
      .anchorX(Align.END)
      .watch(this.world)
      .attach(this);
    TextNode.create(1089.0D, 787.0D)
      .text(Text.create("inviter", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 27.0F, Color.WHITE)))
      .visible(node -> this.leader)
      .attach(this);
    ((TextFieldNode)TextFieldNode.create(1479.0D, 792.0D, 407.0D, 34.0D)
      .marginLeft(35.0D)
      .maxTextLength(16)
      .placeholder("Joueur")
      .onUpdate(node -> {
          String newText = node.getText();
          if ((this.lastSearchText != null && this.lastSearchText.equals(newText)) || System.currentTimeMillis() - this.lastSearchUpdate < 500L)
            return; 
          this.lastSearchText = newText;
          this.lastSearchUpdate = System.currentTimeMillis();
          ((HeadNode)node.getChild(0, HeadNode.class)).player(newText.isEmpty() ? "0" : newText);
          if (newText.isEmpty()) {
            this.searchResultSignal.clear();
            return;
          } 
          (new BBPacketRPGDungeonHubSearchPlayer(newText)).subscribe(()).send();
        })).body(field -> HeadNode.create(10.0D, field.dh(2.0D) - 8.0D - 1.0D, 17.0D).player("0").attach(field))
      
      .visible(node -> this.leader)
      .attach(this);
    FlexNode.vertical(1089.0D, 855.0D, 797.0D)
      .margin(9.0D)
      .body(flex -> {
          for (int i = 0; i < ((List)this.searchResultSignal.getOrDefault()).size(); i++) {
            BBPacketRPGDungeonHubSearchPlayer.BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement element = ((List<BBPacketRPGDungeonHubSearchPlayer.BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement>)this.searchResultSignal.getOrDefault()).get(i);
            if (element == null || element.getUsername() == null || element.getUsername().isEmpty() || ((DungeonWorld)this.world.getOrDefault()).isMember(element.getUuid()))
              return; 
            RectNode.create(0.0D, 0.0D, flex.getWidth(), 39.0D).color(new Color(23, 23, 23)).body(()).attach(flex);
          } 
        }).watch(this.world, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.searchResultSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).visible(node -> this.leader)
      .attach(this);
  }
  
  public void update() {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent()) {
      ZUI.close(this);
      return;
    } 
    this.world.set(optionalDungeonWorld.get());
  }
  
  private static class UIDungeonHubTransition extends Transition {
    public UIDungeonHubTransition() {
      super(new UIDungeonHubInTransition(null), new UIDungeonHubOutTransition(null));
    }
    
    private static class UIDungeonHubInTransition extends Transition.In {
      private UIDungeonHubInTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated((1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
    
    private static class UIDungeonHubOutTransition extends Transition.Out {
      private UIDungeonHubOutTransition() {}
      
      public void init(@NonNull UI ui) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
      }
      
      public void start() {
        start(getAnimator().sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_IN).getTimeline());
      }
      
      public void pre(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPushMatrix();
        GL11.glTranslated((1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
      }
      
      public void post(@NonNull UI ui, double mouseX, double mouseY) {
        if (ui == null)
          throw new NullPointerException("ui is marked non-null but is null"); 
        GL11.glPopMatrix();
      }
    }
  }
  
  public static class UIDungeonHubData {
    private final boolean leader;
    
    private final List<DungeonConfig> dungeons;
    
    public UIDungeonHubData(boolean leader, List<DungeonConfig> dungeons) {
      this.leader = leader;
      this.dungeons = dungeons;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\UIDungeonHub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */