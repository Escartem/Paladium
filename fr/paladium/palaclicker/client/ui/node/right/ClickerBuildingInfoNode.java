package fr.paladium.palaclicker.client.ui.node.right;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.client.ui.node.upgrade.ClickerUpgradeNode;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.List;

public class ClickerBuildingInfoNode extends Node {
  private ClickerBuildingNode node;
  
  private ClickerBuilding building;
  
  private int count;
  
  private boolean unlocked;
  
  protected ClickerBuildingInfoNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ClickerBuildingInfoNode create(double x, double y, double width, double height) {
    return new ClickerBuildingInfoNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    if (this.building == null)
      return; 
    clearChildren();
    ContainerNode.create(0.0D, 0.0D, getWidth(), getDefaultHeight())
      .body(container -> {
          if (!this.unlocked) {
            ImageNode.create(19.0D, 15.0D).resource(ClickerBuildingNode.UNKOWN_TEXTURE).linear(false).width(50.0D).aspectRatio(1.0D).attach(container);
          } else if (this.building.getImage() != null && !this.building.getImage().isEmpty()) {
            ImageNode.create(19.0D, 15.0D).resource(this.building.getImage()).linear(false).width(50.0D).aspectRatio(1.0D).attach(container);
          } else if (this.building.getItem() != null && !this.building.getItem().isEmpty()) {
            ItemNode.create(19.0D, 15.0D, 50.0D).item(this.building.getItemStack()).stackSize(false).attach(container);
          } else {
            RectNode.create(19.0D, 15.0D, 50.0D, 50.0D).wait((ISignal)new BooleanSignal()).attach(container);
          } 
          TextNode.create(93.0D, 20.0D).text(Text.create(this.unlocked ? this.building.getName() : "???", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F).color(Color.WHITE))).mode(TextMode.SPLIT).width(200.0D).attach(container);
          TextNode.create(aw(-20.0D), -6.0D).text(Text.create(String.format("%02d", new Object[] { Integer.valueOf(this.count) }), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 42.0F).color(Color.WHITE).shadow().shadow(0.0F, 4.0F))).anchorX(Align.END).attach(container);
        }).attach(this);
    if (!this.unlocked)
      return; 
    PlayerClickerData playerData = ((UIClicker)getUi()).getPlayerData();
    ListSignal<ClickerUpgrade> upgradeList = ((UIClicker)getUi()).getUpgradeList();
    MapSignal<ClickerShopType, List<ClickerShopItem>> shopList = ((UIClicker)getUi()).getShopSignal();
    RectNode.create(19.0D, 90.0D, aw(-38.0D), 42.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .body(container -> {
          TextNode.create(13.0D, 14.0D).text(Text.create(TTT.format("clicker.gui.production.base", new Object[0]), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 12.0F).color(Color.WHITE))).attach(container);
          ImageNode.create(container.aw(-34.0D), 14.0D).resource(UIClicker.COIN_TEXTURE).width(15.0D).aspectRatio(1.0D).attach(container);
          double rps = ((UIClicker)getUi()).getPlayerData().getRPSBase(this.building, (List)((UIClicker)getUi()).getBuildingList().getOrDefault(), (List)((UIClicker)getUi()).getUpgradeList().getOrDefault());
          TextNode.create(container.aw(-39.0D), 12.5D).text(Text.create((rps < 10.0D) ? String.format("%.2f", new Object[] { Double.valueOf(rps) }) : UIClicker.formatBigNumber(Double.valueOf(rps)), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 15.0F).color(UIClicker.TEXT_YELLOW_COLOR).shadow(UIClicker.TEXT_YELLOW_SHADOW_COLOR).shadow(0.0F, 1.0F))).anchorX(Align.END).attach(container);
        }).attach(this);
    RectNode.create(19.0D, 135.0D, aw(-38.0D), 42.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .body(container -> {
          TextNode.create(13.0D, 14.0D).text(Text.create(TTT.format("clicker.gui.production.current", new Object[0]), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 12.0F).color(Color.WHITE))).attach(container);
          ImageNode.create(container.aw(-34.0D), 14.0D).resource(UIClicker.COIN_TEXTURE).width(15.0D).aspectRatio(1.0D).attach(container);
          double rps = ((UIClicker)getUi()).getPlayerData().getRPS(this.building, (List)((UIClicker)getUi()).getBuildingList().getOrDefault(), (List)((UIClicker)getUi()).getUpgradeList().getOrDefault());
          TextNode.create(container.aw(-39.0D), 12.5D).text(Text.create((rps < 10.0D) ? String.format("%.2f", new Object[] { Double.valueOf(rps) }) : UIClicker.formatBigNumber(Double.valueOf(rps)), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 15.0F).color(UIClicker.TEXT_YELLOW_COLOR).shadow(UIClicker.TEXT_YELLOW_SHADOW_COLOR).shadow(0.0F, 1.0F))).anchorX(Align.END).attach(container);
        }).attach(this);
    RectNode.create(19.0D, 180.0D, aw(-38.0D), 42.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .body(container -> {
          TextNode.create(13.0D, 14.0D).text(Text.create(TTT.format("clicker.gui.production.total", new Object[0]), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 12.0F).color(Color.WHITE))).attach(container);
          ImageNode.create(container.aw(-34.0D), 14.0D).resource(UIClicker.COIN_TEXTURE).width(15.0D).aspectRatio(1.0D).attach(container);
          double production = ((UIClicker)getUi()).getPlayerData().getBuildingProduction(this.building.getId());
          TextNode.create(container.aw(-39.0D), 12.5D).text(Text.create((production < 10.0D) ? String.format("%.2f", new Object[] { Double.valueOf(production) }) : UIClicker.formatBigNumber(Double.valueOf(production)), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 15.0F).color(UIClicker.TEXT_YELLOW_COLOR).shadow(UIClicker.TEXT_YELLOW_SHADOW_COLOR).shadow(0.0F, 1.0F))).anchorX(Align.END).attach(container);
        }).attach(this);
    TextNode.create(19.0D, 234.0D)
      .text(Text.create(TTT.format("clicker.gui.upgrades", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 12.0F).color(Color.WHITE).shadow().shadow(0.0F, 1.0F)))
      .attach(this);
    RectNode.create(19.0D, 260.0D, aw(-38.0D), 42.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .border(Color.WHITE, 1.0D, true)
      .body(container -> FlexNode.horizontal(5.0D, 4.0D, 40.0D).margin(5.9D).body(()).attach(container))
      
      .attach(this);
    TextNode.create(19.0D, 314.0D)
      .text(Text.create(TTT.format("clicker.gui.purshases", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 12.0F).color(Color.WHITE).shadow().shadow(0.0F, 1.0F)))
      .attach(this);
    RectNode.create(19.0D, 340.0D, aw(-38.0D), 42.0D)
      .color(new Color(0.0F, 0.0F, 0.0F, 0.3F))
      .border(Color.WHITE, 1.0D, true)
      .body(container -> FlexNode.horizontal(5.0D, 4.0D, 40.0D).margin(5.9D).body(()).attach(container))
      
      .attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (!isHovered()) {
      if (mouseY < 113.0D || mouseX > 1858.0D || mouseY > 1040.0D || mouseX < 1832.0D - getWidth() || this.node.getAbsoluteY() + this.node.getHeight() < 143.0D || !this.node.isEnabled() || !getUi().isOnTop())
        visible(node -> false).enabled(node -> false).zindex(-1); 
      double newY = Math.max(145.0D, Math.min(mouseY - 89.0D, Math.min(this.node.getAbsoluteY(), 215.0D + getHeight() + 89.0D)));
      y(newY);
    } 
    Color[] colors = this.node.isAvailable() ? ClickerBuildingNode.COLORS : ClickerBuildingNode.DISABLED_COLORS;
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), colors[0]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), 5.0D, colors[1]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), 5.0D, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX() + aw(-5.0D), getY(), 5.0D, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-12.0D), getWidth(), 5.0D, colors[3]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-7.0D), getWidth(), 7.0D, colors[4]);
    DrawUtils.SHAPE.drawFilledBorder(getX() + 2.0D, ((int)getY() + 2), getX() + getWidth() - 2.0D, getY() + getHeight() - 2.0D, ClickerBuildingNode.BORDER_COLOR, 2.0D);
  }
  
  public ClickerBuildingInfoNode data(ClickerBuildingNode node) {
    this.node = node;
    this.building = node.getBuilding();
    this.count = node.getCount();
    this.unlocked = node.isUnlocked();
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\right\ClickerBuildingInfoNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */