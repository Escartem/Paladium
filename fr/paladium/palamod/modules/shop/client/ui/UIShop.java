package fr.paladium.palamod.modules.shop.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.shop.client.ui.container.ItemShopDisplayContainer;
import fr.paladium.palamod.modules.shop.client.ui.container.ShopContainer;
import fr.paladium.palamod.modules.shop.client.ui.node.ShopBannerNode;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.format.FormatUtils;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;

@UIData(background = false)
public class UIShop extends UI {
  private final DoubleSignal money = new DoubleSignal(-10.0D);
  
  private final MapSignal<String, ShopItem> itemsMap = new MapSignal(new LinkedHashMap<>());
  
  public void init() {
    BackgroundNode.create()
      .attach(this);
    FlexNode.horizontal(113.0D, 0.0D, 96.0D)
      .body(flex -> {
          Color red = new Color(239, 57, 38);
          Color shadowRed = new Color(146, 19, 12);
          for (int i = 0; i < 14; i++) {
            if (i % 2 == 0) {
              ShopBannerNode.create(0.0D, 0.0D, 121.0D, 82.0D).color(red).shadowColor(shadowRed).bottomOffset(14.0D).shadowheight(4.0D).attach(flex);
            } else {
              ShopBannerNode.create(0.0D, 0.0D, 121.0D, 82.0D).bottomOffset(14.0D).shadowheight(4.0D).attach(flex);
            } 
          } 
        }).attach(this);
    CloseButtonNode.create(1846.0D, 57.0D)
      .onClick((node, mouseX, mouseY, clickType) -> Minecraft.func_71410_x().func_147108_a(null))
      .attach(this);
    TextNode.create(960.0D, 119.0D)
      .text(Text.create("ADMIN SHOP", PaladiumText.HEADER))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(960.0D, 223.0D)
      .text(Text.create("VENDEZ OU ACHETEZ DES ITEMS", PaladiumText.SUB_TITLE))
      .anchorX(Align.CENTER)
      .attach(this);
    Color rectBackgroundColor = new Color(0.0F, 0.0F, 0.0F, 0.25F);
    RectNode.create(1081.0D, 332.0D, 786.0D, 103.0D)
      .color(rectBackgroundColor)
      .border(Color.GREEN, 2.0D)
      .body(moneyContainer -> {
          TextNode.create(37.0D, 43.5D).text(Text.create("VOTRE ARGENT", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).attach(moneyContainer);
          RectNode.create(253.0D, 27.0D, 1.0D, 56.0D).color(Color.WHITE).attach(moneyContainer);
          ImageNode.create(295.0D, 38.0D).resource(ResourceConstant.MONEY).linear(false).size(56.0D, 32.0D).attach(moneyContainer);
          ((TextNode)TextNode.create(375.0D, 36.5D).text(Text.create("", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F, Color.WHITE))).width(335.0D).onMount(())).wait((ISignal)this.money).attach(moneyContainer);
          TextNode.create(726.0D, 36.5D).text(Text.create("$", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F, Color.WHITE))).attach(moneyContainer);
        }).attach(this);
    ItemShopDisplayContainer shopDisplayContainer = (ItemShopDisplayContainer)ItemShopDisplayContainer.create(1081.0D, 453.0D, 786.0D, 575.0D).border(Color.RED, 2.0D).color(rectBackgroundColor).attach(this);
    ((ShopContainer)ShopContainer.create(59.0D, 330.0D, 1004.0D, 700.0D)
      .onInit(node -> {
          node.setDisplayContainer(shopDisplayContainer);
          node.setItemsMap(this.itemsMap);
        })).color(rectBackgroundColor)
      .watch((Signal)this.itemsMap, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), ColorConstant.BACKGROUND);
  }
  
  public void putItems(Map<String, ShopItem> itemsMap) {
    this.itemsMap.set(itemsMap);
  }
  
  public void setMoney(double money) {
    this.money.set(Double.valueOf(money));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\UIShop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */