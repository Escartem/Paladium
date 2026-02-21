package fr.paladium.palapass.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palapass.client.ui.node.PalapassIconNode;
import fr.paladium.palapass.client.ui.node.home.PalapassRewardContainerNode;
import fr.paladium.palapass.client.ui.popup.premium.UIPalapassPremiumPopup;
import fr.paladium.palapass.client.ui.popup.quest.UIPalapassQuestPopup;
import fr.paladium.palapass.client.ui.popup.reward.UIPalapassRewardPopup;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.LongSignal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@UIData(backgroundColor = "#1E1E23")
public class UIPalapass extends UI {
  public static final LongSignal PB_SIGNAL = new LongSignal(0L);
  
  private static final Resource GRADIENT_TEXTURE = Resource.of(new ResourceLocation("palapass", "textures/gui/home/gradient.png"));
  
  private static final Resource PREMIUM_TEXTURE = Resource.of(new ResourceLocation("palapass", "textures/gui/home/premium.png"));
  
  public static final Resource LOCK_TEXTURE = Resource.of(new ResourceLocation("palapass", "textures/gui/icons/lock.png"));
  
  public static final Resource UNLOCK_TEXTURE = Resource.of(new ResourceLocation("palapass", "textures/gui/icons/unlock.png"));
  
  private List<RewardLevel> rewardList;
  
  private List<Quest> seasonQuestList;
  
  private List<Quest> dailyQuestList;
  
  private IntegerSignal page;
  
  private int pageCount;
  
  private PalapassPlayer playerData;
  
  private Signal<RewardLevel> selectedReward;
  
  private Node rewardContainer;
  
  public List<RewardLevel> getRewardList() {
    return this.rewardList;
  }
  
  public List<Quest> getSeasonQuestList() {
    return this.seasonQuestList;
  }
  
  public List<Quest> getDailyQuestList() {
    return this.dailyQuestList;
  }
  
  public IntegerSignal getPage() {
    return this.page;
  }
  
  public int getPageCount() {
    return this.pageCount;
  }
  
  public PalapassPlayer getPlayerData() {
    return this.playerData;
  }
  
  public Signal<RewardLevel> getSelectedReward() {
    return this.selectedReward;
  }
  
  public Node getRewardContainer() {
    return this.rewardContainer;
  }
  
  public void init() {
    PBManager.get(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g)).thenAccept(PB_SIGNAL::set).exceptionally(e -> {
          PB_SIGNAL.set(Long.valueOf(0L));
          return null;
        });
    BackgroundNode.create().attach(this);
    this.selectedReward = Signal.of(this.rewardList.get(0));
    for (RewardLevel reward : this.rewardList) {
      if (this.playerData.getRewardsClaimed().contains(reward.getRewardUUID()))
        continue; 
      if (this.playerData.getPoints() >= reward.getNeededPoints())
        this.selectedReward.set(reward); 
    } 
    if (this.selectedReward == null)
      for (RewardLevel reward : this.rewardList) {
        if (this.playerData.getPoints() >= reward.getNeededPoints())
          this.selectedReward.set(reward); 
      }  
    int selectedRewardIndex = this.rewardList.indexOf(this.selectedReward.getOrDefault());
    this.page.set(Integer.valueOf(selectedRewardIndex / 6));
    TextNode.create(79.0D, 50.0D)
      .text(Text.create(PalapassTranslateEnum.PALAPASS.textOrDefault("palapass"), PaladiumText.HEADER))
      .attach(this);
    TextNode.create(92.0D, 203.0D)
      .text(Text.create(PalapassTranslateEnum.SEASON_INFO.textOrDefault("saison " + String.format("%02d", new Object[] { Integer.valueOf(PalapassManager.getMonth()) })), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 33.0F, Color.WHITE)))
      .attach(this);
    LocalDateTime now = TimeUtils.nowZoned().toLocalDateTime();
    LocalDateTime firstDayOfNextMonth = now.withDayOfMonth(1).plusMonths(1L).toLocalDate().atStartOfDay();
    Duration duration = Duration.between(now, firstDayOfNextMonth);
    long days = duration.toDays();
    long hours = duration.minusDays(days).toHours();
    long minutes = duration.minusDays(days).minusHours(hours).toMinutes();
    TextNode.create(92.0D, 248.0D)
      .text(Text.create(PalapassTranslateEnum.REMAINING_TIME.textOrDefault(("temps restant : " + days + "j " + hours + "h " + minutes + "min").toUpperCase(), new Object[] { Long.valueOf(days), Long.valueOf(hours), Long.valueOf(minutes) }), PaladiumText.SUB_TITLE))
      .attach(this);
    TextNode.create(92.0D, 275.0D)
      .text(Text.create(PalapassTranslateEnum.POINTS_INFO.textOrDefault((this.playerData.getPoints() + " point" + ((this.playerData.getPoints() > 1) ? "s" : "")).toUpperCase(), new Object[] { Integer.valueOf(this.playerData.getPoints()) }), PaladiumText.SUB_TITLE))
      .attach(this);
    TextButtonNode.create(92.0D, 346.0D)
      .text(PalapassTranslateEnum.VIEW_QUESTS.textOrDefault("voir les quêtes"))
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.open((UI)new UIPalapassQuestPopup(this.playerData, this.dailyQuestList, this.seasonQuestList)))
      .attach(this);
    CloseButtonNode.create(1840.0D, 63.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    ((PalapassIconNode)PalapassIconNode.create(620.0D, 200.0D, 680.0D)
      .body(node -> node.icon(((RewardLevel)this.selectedReward.getOrDefault()).getIcon())))
      .watch(this.selectedReward, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY, WatchProperty.RELOAD }).attach(this);
    ImageNode.create(0.0D, 0.0D)
      .resource(GRADIENT_TEXTURE)
      .linear(false)
      .size(1920.0D, 910.0D)
      .zlevel(50.0D)
      .attach(this);
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          ((TextNode)TextNode.create(960.0D, 118.0D).mode(TextMode.OVERFLOW).width(890.0D).onInit(())).anchorX(Align.CENTER).watch(this.selectedReward).attach(this);
          ((TextNode)TextNode.create(191.0D, 774.0D).onInit(())).onClick(()).watch((Signal)this.page).attach(container);
          ((TextNode)TextNode.create(1730.0D, 774.0D).onInit(())).anchorX(Align.END).onClick(()).watch((Signal)this.page).attach(container);
          this.rewardContainer = PalapassRewardContainerNode.create(251.0D, 655.0D, 1417.0D, 308.0D).attach(container);
          FlexNode.horizontal(960.0D, 1000.0D, 23.0D).margin(25.0D).align(Align.CENTER).body(()).zlevel(50.0D).attach(this);
        }).zlevel(50.0D)
      .attach(this);
    RectNode.create(1408.0D, 133.0D, 442.0D, 192.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.15F))
      .border(ColorConstant.PRIMARY, 2.0D, true)
      .body(rect -> {
          ImageNode.create(rect.dw(2.0D) - 12.0D, 15.0D).resource(UNLOCK_TEXTURE).linear(false).size(24.0D, 27.0D).attach(rect);
          TextNode.create(rect.dw(2.0D), 48.0D).text(Text.create(PalapassTranslateEnum.NEXT_LEVEL.textOrDefault("niveau suivant"), PaladiumText.HEADER.copy().fontSize(25.0F).shadow(0.0F, 2.0F), Align.CENTER)).anchorX(Align.CENTER).attach(rect);
          TextButtonNode.create(rect.dw(2.0D) - 84.0D, 125.0D).text(PalapassTranslateEnum.UNLOCK.textOrDefault("débloquer")).width(168.0D).onClick(()).attach(rect);
        }).onInit(rect -> {
          int diff = ((RewardLevel)this.selectedReward.getOrDefault()).getNeededPoints() - this.playerData.getPoints();
          rect.visible(());
        }).zlevel(50.0D)
      .watch(this.selectedReward)
      .attach(this);
    RectNode.create(1408.0D, 133.0D, 442.0D, 314.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.15F))
      .border(ColorConstant.PRIMARY, 2.0D, true)
      .body(rect -> {
          TextNode.create(21.0D, 16.0D).mode(TextMode.SPLIT).text(Text.create(PalapassTranslateEnum.BUY_PREMIUM.textOrDefault("acheter le premium"), PaladiumText.HEADER.copy().fontSize(25.0F).shadow(0.0F, 2.0F))).width(197.0D).attach(rect);
          ImageNode.create(165.0D, 11.5D).resource(PREMIUM_TEXTURE).attach(rect);
          TextButtonNode.create(21.0D, 252.0D).text(PalapassTranslateEnum.BUY.textOrDefault("acheter")).onClick(()).attach(rect);
        }).onInit(rect -> {
          int diff = ((RewardLevel)this.selectedReward.getOrDefault()).getNeededPoints() - this.playerData.getPoints();
          rect.visible(());
        }).zlevel(50.0D)
      .watch(this.selectedReward)
      .attach(this);
  }
  
  public UIPalapass data(List<RewardLevel> rewardList, List<Quest> dailyQuestList, List<Quest> seasonQuestList) {
    this.rewardList = rewardList;
    this.dailyQuestList = dailyQuestList;
    this.seasonQuestList = seasonQuestList;
    this.page = IntegerSignal.of(0);
    this.pageCount = (int)Math.ceil(rewardList.size() / 6.0D);
    this.playerData = PalapassPlayer.get((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\UIPalapass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */