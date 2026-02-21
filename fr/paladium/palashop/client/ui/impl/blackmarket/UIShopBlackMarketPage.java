package fr.paladium.palashop.client.ui.impl.blackmarket;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.item.ShopItemNode;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.common.blackmarket.network.BBPacketGetBlackMarket;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UIShopBlackMarketPage extends UIShopPage {
  private static final List<Integer> REVEALED_CARDS = new ArrayList<>();
  
  private final Signal<BBPacketGetBlackMarket.BBPacketGetBlackMarketData> signal;
  
  public UIShopBlackMarketPage() {
    super((ShopNavbarElement)ShopNavbarManager.TAB_BLACKMARKET);
    this.signal = new Signal();
  }
  
  public void init() {
    super.init();
    (new BBPacketGetBlackMarket()).subscribe(result -> this.signal.set(result))
      
      .send();
    TextNode.create(960.0D, 159.0D)
      .text(Text.create("BLACK MARKET", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 64.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    RectNode.create(42.0D, 264.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ((TextNode)TextNode.create(0.0D, 329.0D)
      .onInit(node -> node.text(Text.create((this.signal.getOrDefault() == null) ? "CHARGEMENT" : "TEMPS RESTANT", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE)).horizontalAlign(Align.CENTER))))
      
      .width(1920.0D)
      .watch(this.signal)
      .attach((UI)this);
    ((TextNode)TextNode.create(0.0D, 360.0D)
      .onUpdate(node -> {
          if (this.signal.getOrDefault() == null)
            return; 
          long remaining = Duration.between(UniversalTimeUtils.nowZoned(), UniversalTimeUtils.fromLong(((BBPacketGetBlackMarket.BBPacketGetBlackMarketData)this.signal.getOrDefault()).getExpiration())).getSeconds();
          long days = TimeUnit.SECONDS.toDays(remaining);
          long hours = TimeUnit.SECONDS.toHours(remaining) % 24L;
          long minutes = TimeUnit.SECONDS.toMinutes(remaining) % 60L;
          long seconds = TimeUnit.SECONDS.toSeconds(remaining) % 60L;
          String time = String.format("%02d:%02d:%02d:%02d", new Object[] { Long.valueOf(days), Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
          node.text(Text.create(time, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 50.0F, ColorConstant.PRIMARY)).horizontalAlign(Align.CENTER));
        })).width(1920.0D)
      .visible(node -> (this.signal.getOrDefault() != null))
      .attach((UI)this);
    Text text = Text.create("CLIQUE", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, ColorConstant.PRIMARY)).add(Text.create(" SUR LES CARTES POUR RÉVÉLER TON MARKET", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE)));
    double rectY = 997.0D + text.dh(2.0D) - 1.0D;
    double rectWidth = 960.0D - text.dw(2.0D) - 51.0D - 42.0D;
    TextNode.create(960.0D, 997.0D)
      .text(text)
      .anchorX(Align.CENTER)
      .attach((UI)this);
    RectNode.create(42.0D, rectY, rectWidth, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(960.0D + text.dw(2.0D) + 51.0D, rectY, rectWidth, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    FlexNode.horizontal(960.0D, 534.0D, 331.0D)
      .margin(35.0D)
      .align(Align.CENTER)
      .body(flex -> {
          BBPacketGetBlackMarket.BBPacketGetBlackMarketData data = (BBPacketGetBlackMarket.BBPacketGetBlackMarketData)this.signal.getOrDefault();
          if (data == null) {
            for (int j = 0; j < 6; j++)
              ShopItemNode.create(0.0D, 0.0D, 265.0D, flex.getHeight()).rotate(REVEALED_CARDS.contains(Integer.valueOf(j)) ? 0.0F : 180.0F).attach(flex); 
            return;
          } 
          for (int i = 0; i < data.getItems().size(); i++) {
            int index = i;
            ConditionalBuyableObject<IShopItem> item = data.getItems().get(index);
            ((ShopItemNode)ShopItemNode.create(0.0D, 0.0D, 265.0D, flex.getHeight()).shopItem(item).rotate(REVEALED_CARDS.contains(Integer.valueOf(index)) ? 0.0F : 180.0F).onUpdate(())).attach(flex);
          } 
        }).anchorX(Align.CENTER)
      .watch(this.signal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    keybind(() -> {
          REVEALED_CARDS.clear();
          reload();
        }new Integer[] { Integer.valueOf(29), Integer.valueOf(42), Integer.valueOf(19) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\blackmarket\UIShopBlackMarketPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */