package fr.paladium.palaclicker.client.ui;

import fr.paladium.palaclicker.client.ui.node.left.ClickerFallingItemsSectionNode;
import fr.paladium.palaclicker.client.ui.node.left.ClickerNode;
import fr.paladium.palaclicker.client.ui.node.right.ClickerBuildingInfoNode;
import fr.paladium.palaclicker.client.ui.node.right.ClickerBuildingNode;
import fr.paladium.palaclicker.client.ui.node.shop.ClickerShopNode;
import fr.paladium.palaclicker.client.ui.node.upgrade.ClickerUpgradeNode;
import fr.paladium.palaclicker.client.ui.popup.UIClickerPopup;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.common.network.packet.building.BBPacketClickerBuildingData;
import fr.paladium.palaclicker.common.network.packet.shop.BBPacketClickerShopData;
import fr.paladium.palaclicker.common.network.packet.upgrade.BBPacketClickerUpgradeData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeType;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIClicker extends UI {
  private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/background.png");
  
  private static final ResourceLocation LEFT_OVERLAY_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/left/left_overlay.png");
  
  private static final ResourceLocation CLICK_BANNER_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/left/click_banner.png");
  
  private static final ResourceLocation NAMMU_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/shop/namuu.png");
  
  public static final ResourceLocation COIN_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/icons/coin.png");
  
  public static final ResourceLocation ARROW_UP_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/icons/arrow_up.png");
  
  public static final ResourceLocation ARROW_DOWN_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/icons/arrow_down.png");
  
  private static final Color BACKGROUND_BLUE_COLOR = new Color(38, 63, 127);
  
  private static final Color BACKGROUND_BLACK_COLOR = new Color(41, 44, 45);
  
  private static final Color BACKGROUND_BLACK_BORDER_COLOR = new Color(29, 31, 32);
  
  private static final Color BACKGROUND_RED_COLOR = new Color(102, 31, 31);
  
  private static final Color BACKGROUND_RED_BORDER_COLOR = new Color(86, 20, 21);
  
  public static final Color TEXT_YELLOW_COLOR = new Color(239, 195, 38);
  
  public static final Color TEXT_YELLOW_SHADOW_COLOR = new Color(185, 116, 12);
  
  private static final DecimalFormat BIG_NUMBER_FORMATTER = new DecimalFormat("#,###");
  
  private PlayerClickerData playerData;
  
  private ListSignal<ClickerBuilding> buildingList;
  
  private ListSignal<ClickerUpgrade> upgradeList;
  
  private DoubleSignal pointsSignal;
  
  private DoubleSignal rpsSignal;
  
  private Signal<ItemStack> itemSignal;
  
  private MapSignal<ClickerShopType, List<ClickerShopItem>> shopSignal;
  
  private BooleanSignal upgradePageSignal;
  
  private BooleanSignal upgradeHotbarSignal;
  
  private Node shopContainerNode;
  
  private Node upgradeContainerNode;
  
  private ClickerBuildingInfoNode buildingInfoNode;
  
  public PlayerClickerData getPlayerData() {
    return this.playerData;
  }
  
  public ListSignal<ClickerBuilding> getBuildingList() {
    return this.buildingList;
  }
  
  public ListSignal<ClickerUpgrade> getUpgradeList() {
    return this.upgradeList;
  }
  
  public DoubleSignal getPointsSignal() {
    return this.pointsSignal;
  }
  
  public DoubleSignal getRpsSignal() {
    return this.rpsSignal;
  }
  
  public Signal<ItemStack> getItemSignal() {
    return this.itemSignal;
  }
  
  public MapSignal<ClickerShopType, List<ClickerShopItem>> getShopSignal() {
    return this.shopSignal;
  }
  
  public BooleanSignal getUpgradePageSignal() {
    return this.upgradePageSignal;
  }
  
  public BooleanSignal getUpgradeHotbarSignal() {
    return this.upgradeHotbarSignal;
  }
  
  public Node getShopContainerNode() {
    return this.shopContainerNode;
  }
  
  public Node getUpgradeContainerNode() {
    return this.upgradeContainerNode;
  }
  
  public ClickerBuildingInfoNode getBuildingInfoNode() {
    return this.buildingInfoNode;
  }
  
  public void init() {
    this.playerData = PlayerClickerData.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
    this.buildingList = new ListSignal(new ArrayList());
    this.upgradeList = new ListSignal(new ArrayList());
    this.shopSignal = new MapSignal(new HashMap<>());
    this.pointsSignal = new DoubleSignal(this.playerData.getPoints());
    this.rpsSignal = new DoubleSignal(this.playerData.getRPS((List)this.buildingList.getOrDefault(), (List)this.upgradeList.getOrDefault()));
    this.itemSignal = new Signal();
    this.upgradePageSignal = new BooleanSignal(false);
    this.upgradeHotbarSignal = BooleanSignal.of(true);
    (new BBPacketClickerShopData()).subscribe(data -> {
          data.getShopItems().values().forEach(());
          this.shopSignal.set(data.getShopItems());
        }).send();
    (new BBPacketClickerBuildingData()).subscribe(data -> {
          data.getBuildingList().forEach(ClickerBuilding::make);
          this.buildingList.set(data.getBuildingList());
        }).send();
    (new BBPacketClickerUpgradeData()).subscribe(data -> {
          data.getUpgradeList().forEach(ClickerUpgrade::make);
          this.upgradeList.set(data.getUpgradeList());
        }).send();
    if (this.playerData.isFirstUse()) {
      ZUI.open((UI)new UIClickerPopup("règlement", "toute utilisation de solution automatisée pour gagner des points est interdite et sera sanctionnée."));
      this.playerData.setFirstUse(false);
    } 
    BackgroundNode.create(1920.0D, 1080.0D)
      .backgroundColor(BACKGROUND_BLUE_COLOR)
      .resource(Resource.of(BACKGROUND_TEXTURE))
      .attach(this);
    BackgroundNode.create(0.0D, 35.0D, 509.0D, 1010.0D)
      .backgroundColor(BACKGROUND_BLACK_COLOR)
      .borderColor(BACKGROUND_BLACK_BORDER_COLOR)
      .resource(Resource.of(BACKGROUND_TEXTURE))
      .attach(this);
    BackgroundNode.create(1411.0D, 35.0D, 509.0D, 1010.0D)
      .backgroundColor(BACKGROUND_RED_COLOR)
      .borderColor(BACKGROUND_RED_BORDER_COLOR)
      .resource(Resource.of(BACKGROUND_TEXTURE))
      .attach(this);
    ContainerNode.create(0.0D, 0.0D, 509.0D, 1080.0D)
      .body(container -> {
          TextNode.create(34.0D, 28.0D).text(Text.create("clicker", PaladiumText.HEADER.copy().color(TEXT_YELLOW_COLOR).shadow(TEXT_YELLOW_SHADOW_COLOR))).attach(container);
          ImageNode.create(0.0D, 146.0D).resource(CLICK_BANNER_TEXTURE).linear(false).size(container.getWidth(), 176.0D).attach(container);
          ((TextNode)TextNode.create(container.dw(2.0D), 229.0D).onInit(())).watch((Signal)this.pointsSignal).attach(container);
          TextNode.create(container.dw(2.0D), 269.0D).text(Text.create(TTT.format("clicker.gui.persecond", new Object[0]), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 12.0F).color(TEXT_YELLOW_COLOR))).anchorX(Align.CENTER).attach(container);
          ((TextNode)TextNode.create(container.dw(2.0D), 285.0D).onInit(())).anchorX(Align.CENTER).watch((Signal)this.rpsSignal).attach(container);
          ClickerFallingItemsSectionNode.create(0.0D, 321.0D, container.getWidth(), container.ah(-356.0D)).wait((ISignal)this.itemSignal).attach(container);
          ImageNode.create(0.0D, 321.0D).resource(LEFT_OVERLAY_TEXTURE).size(container.getWidth(), container.ah(-356.0D)).zlevel(1.0D).attach(container);
          ClickerNode.create(container.dw(2.0D) - 207.0D, 432.0D, 414.0D, 414.0D).zlevel(1.0D).wait((ISignal)this.itemSignal).attach(container);
        }).attach(this);
    ((TextNode)TextNode.create(560.0D, 878.0D)
      .text(Text.create(TTT.format("clicker.gui.upgrades", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 33.0F).color(Color.WHITE).shadow().shadow(0.0F, 3.0F)))
      .onInit(node -> {
          if (this.upgradePageSignal == null)
            return; 
          node.visible(());
        })).watch((Signal)this.upgradePageSignal)
      .zlevel(1.0D)
      .attach(this);
    this
      
      .shopContainerNode = ContainerNode.create(565.0D, 0.0D, 790.0D, 1080.0D).body(container -> {
          RectNode.create(0.0D, 39.0D, 790.0D, 213.0D).color(new Color(0.0F, 0.0F, 0.0F, 0.3F)).body(()).attach(container);
          GridNode.create(10.0D, 314.0D, 766.0D, 501.0D).horizontalMargin(107.0D).verticalMargin(64.0D).body(()).watch((Signal)this.shopSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container);
        }).onInit(container -> {
          if (this.upgradePageSignal == null)
            return; 
          container.visible(());
        }).watch((Signal)this.upgradePageSignal).attach(this);
    ((TextNode)TextNode.create(580.0D, 129.0D)
      .text(Text.create(TTT.format("clicker.gui.upgrades", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 33.0F).color(Color.WHITE).shadow().shadow(0.0F, 3.0F)))
      .onInit(node -> {
          if (this.upgradePageSignal == null)
            return; 
          node.visible(());
        })).watch((Signal)this.upgradePageSignal)
      .zlevel(1.0D)
      .attach(this);
    this
      
      .upgradeContainerNode = RectNode.create(565.0D, 112.0D, 790.0D, 833.0D).color(new Color(0.0F, 0.0F, 0.0F, 0.3F)).border(Color.WHITE, 1.0D, true).body(rect -> ContainerNode.create(0.0D, 100.0D, rect.getWidth(), rect.ah(-120.0D)).overflow(OverflowProperty.SCROLL).onInit(()).watch((Signal)this.upgradeList).watch((Signal)this.upgradeHotbarSignal).attach(rect)).onInit(rect -> {
          if (this.upgradePageSignal == null)
            return; 
          rect.visible(());
        }).watch((Signal)this.upgradeList).watch((Signal)this.upgradePageSignal).attach(this);
    RectNode.create(565.0D, 945.0D, 790.0D, 115.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .border(Color.WHITE, 1.0D, true)
      .body(rect -> FlexNode.horizontal(14.0D, 14.0D, 87.0D).margin(15.0D).onInit(()).watch((Signal)this.upgradeList).watch((Signal)this.upgradeHotbarSignal).attach(rect))
      
      .attach(this);
    ContainerNode.create(1454.0D, 0.0D, 466.0D, 1080.0D)
      .body(container -> {
          TextNode.create(0.0D, 49.0D).text(Text.create(TTT.format("clicker.gui.buildings", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 33.0F).color(Color.WHITE).shadow().shadow(0.0F, 4.0F))).zlevel(1.0D).attach(container);
          ContainerNode.create(0.0D, 143.0D, 391.0D, 884.0D).scrollbar((ScrollbarNode)ScrollbarNode.create(420.0D, 0.0D, 884.0D)).overflow(OverflowProperty.SCROLL).body(()).attach(container);
        }).attach(this);
    this
      
      .buildingInfoNode = (ClickerBuildingInfoNode)ClickerBuildingInfoNode.create(1056.0D, 0.0D, 391.0D, 408.0D).visible(n -> false).enabled(n -> false).zlevel(100.0D).attach(this);
    CloseButtonNode.create(1885.0D, 15.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
  }
  
  public void update() {
    if (this.playerData == null)
      return; 
    double points = this.playerData.getPoints();
    if (this.pointsSignal != null && ((Double)this.pointsSignal.getOrDefault()).doubleValue() != points)
      this.pointsSignal.set(Double.valueOf(points)); 
    double rps = this.playerData.getRPS((List)this.buildingList.getOrDefault(), (List)this.upgradeList.getOrDefault());
    if (this.rpsSignal != null && ((Double)this.rpsSignal.getOrDefault()).doubleValue() != rps)
      this.rpsSignal.set(Double.valueOf(rps)); 
    if (this.upgradeList != null && this.upgradeList.isPresent()) {
      ItemStack item = this.playerData.getClickItem((List)this.upgradeList.getOrDefault());
      if (this.itemSignal.getOrDefault() == null || !((ItemStack)this.itemSignal.getOrDefault()).func_77969_a(item))
        this.itemSignal.set(item); 
    } 
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), BACKGROUND_BLUE_COLOR);
  }
  
  public static String formatBigNumber(Number number) {
    if ((number instanceof Double && ((Double)number).doubleValue() < 10.0D) || (number instanceof Float && ((Float)number).floatValue() < 10.0F))
      return String.format("%.2f", new Object[] { number }); 
    return BIG_NUMBER_FORMATTER.format(number).replace(" ", ".");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\UIClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */