package fr.paladium.palashop.client.ui.impl.store.impl;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.impl.store.UIShopStorePage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.item.ShopItemNode;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.page.impl.ShopDefaultPageData;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class UIShopStoreDefaultPage extends UIShopStorePage<ShopDefaultPageData> {
  public UIShopStoreDefaultPage(@NonNull ShopPage page) {
    super(page, ShopDefaultPageData.class);
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
  }
  
  public void init(Signal<ShopDefaultPageData> dataSignal) {
    ContainerNode.create(42.0D, 174.0D, 1836.0D, 906.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(1852.0D, 46.0D, 814.0D))
      .body(body -> FlexNode.vertical(0.0D, 46.0D, body.getWidth()).margin(25.0D).body(()).watch(dataSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(body)).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\store\impl\UIShopStoreDefaultPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */