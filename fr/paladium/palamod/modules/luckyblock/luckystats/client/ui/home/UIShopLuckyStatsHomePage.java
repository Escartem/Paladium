package fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.home;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.item.UIShopLuckyStatsItemPage;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarTab;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.concurrent.CompletableFuture;
import net.minecraft.util.ResourceLocation;

public class UIShopLuckyStatsHomePage extends UIShopPage {
  public static final ShopNavbarTab TAB = (ShopNavbarTab)(new ShopNavbarTab("luckystats", "Lucky Stats", Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/navbar/luckyblock.png")), UIShopLuckyStatsHomePage.class)).condition(() -> CompletableFuture.completedFuture(Boolean.valueOf((CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE))));
  
  public UIShopLuckyStatsHomePage() {
    super((ShopNavbarElement)TAB);
  }
  
  public void init() {
    super.init();
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(ShopNavbarManager.PAGE_HOME.getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    TextNode.create(42.0D, 159.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    GridNode.create(42.0D, 278.0D, 1836.0D, 0.0D)
      .horizontalMargin(93.5D)
      .verticalMargin(80.0D)
      .body(grid -> {
          int i = 0;
          while (i < Integer.MAX_VALUE && grid.getChildren().size() < 21) {
            LuckyType type = ((LuckyType.values()).length > i) ? LuckyType.values()[i] : null;
            if (type == null || !LuckyType.disabledLuckyType.contains(type))
              ContainerNode.create(0.0D, 0.0D, 182.0D, 200.0D).body(()).onClick(()).hover(()).enabled(()).attach(grid); 
            i++;
          } 
        }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\clien\\ui\home\UIShopLuckyStatsHomePage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */