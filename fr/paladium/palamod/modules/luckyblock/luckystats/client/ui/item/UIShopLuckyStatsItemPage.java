package fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.item;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.client.utils.ChatColor;
import fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.home.UIShopLuckyStatsHomePage;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.BBPacketShopLuckyStatsData;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.CSPacketLuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsRewardLevel;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIShopLuckyStatsItemPage extends UIShopPage implements UIShopPage.IBackableUI {
  private final UIShopPage back;
  
  private final LuckyType type;
  
  private final Signal<PlayerLuckyStats> dataSignal;
  
  public UIShopPage getBack() {
    return this.back;
  }
  
  public LuckyType getType() {
    return this.type;
  }
  
  public Signal<PlayerLuckyStats> getDataSignal() {
    return this.dataSignal;
  }
  
  public UIShopLuckyStatsItemPage(@NonNull LuckyType type) {
    super((ShopNavbarElement)UIShopLuckyStatsHomePage.TAB);
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.back = (UIShopPage)ZUI.getUI(UIShopLuckyStatsHomePage.class);
    this.type = type;
    this.dataSignal = new Signal();
  }
  
  public void init() {
    super.init();
    PalaMod.getNetHandler().sendToServer((IMessage)new BBPacketShopLuckyStatsData());
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(this.type.getText(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    TextNode.create(42.0D, 159.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(1756.0D, 162.0D, 122.0D, 44.0D)
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(6.0F))
      .body(rect -> {
          ((TextNode)TextNode.create(10.0D, 0.0D, rect.aw(-20.0D), rect.getHeight()).text(Text.create("0/" + LuckyEvents.values(this.type).size(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, Color.WHITE)).verticalAlign(Align.CENTER)).onInit(())).watch(this.dataSignal).attach(rect);
          ItemNode.create(rect.aw(-5.0D) - 39.0D, rect.dh(2.0D) - 19.0D, 39.0D).item(LuckyType.Util.getIconFrom(this.type)).attach(rect);
        }).attach((UI)this);
    ContainerNode.create(42.0D, 278.0D, 1836.0D, 208.0D)
      .body(container -> {
          PlayerLuckyStats data = (PlayerLuckyStats)this.dataSignal.getOrDefault();
          float percentage = (data != null) ? ((float)((PlayerLuckyStats)this.dataSignal.getOrDefault()).getStats().keySet().stream().filter(()).count() / LuckyEvents.values(this.type).size()) : 0.0F;
          RectNode.create(0.0D, container.dh(2.0D) - 17.0D, container.getWidth(), 34.0D).color(ColorConstant.GRAY).body(()).attach(container);
          if (data == null) {
            for (int i = 0; i < 4; i++) {
              ImageNode.create(container.getWidth() * (i + 1) * 0.25D - 182.0D, container.dh(2.0D) - 100.0D, 182.0D, 200.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckystats/hexa/background.png")).linear()).color(ColorConstant.DARK).attach(container);
              ImageNode.create(container.getWidth() * (i + 1) * 0.25D - 182.0D, container.dh(2.0D) - 100.0D, 182.0D, 200.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckystats/hexa/foreground.png")).linear()).color(ColorConstant.GRAY).attach(container);
            } 
            return;
          } 
          RectNode.create(0.0D, container.dh(2.0D) - 17.0D, container.getWidth() * percentage, 34.0D).color(ColorConstant.PRIMARY).attach(container);
          if (LuckyStatsRewardManager.getRewards().get(this.type) == null)
            return; 
          for (LuckyStatsRewardLevel rewardLevel : LuckyStatsRewardManager.getRewards().get(this.type)) {
            boolean available = (percentage >= (rewardLevel.getLevel() + 1) * 0.25D);
            boolean claimed = (((Integer)((List<Integer>)data.getRewards().get(this.type)).get(rewardLevel.getLevel())).intValue() == 1);
            ImageNode.create(container.getWidth() * (rewardLevel.getLevel() + 1) * 0.25D - 182.0D, container.dh(2.0D) - 100.0D, 182.0D, 200.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckystats/hexa/background.png")).linear()).color(claimed ? ColorConstant.PRIMARY : (available ? ColorConstant.GRAY : ColorConstant.DARK)).hoveredColor(claimed ? ColorConstant.PRIMARY : (available ? ColorConstant.GRAY.darker(0.3F) : ColorConstant.DARK)).attach(container);
            ImageNode.create(container.getWidth() * (rewardLevel.getLevel() + 1) * 0.25D - 182.0D, container.dh(2.0D) - 100.0D, 182.0D, 200.0D).resource(Resource.of(new ResourceLocation("palamod", "textures/gui/palashop/luckystats/hexa/foreground.png")).linear()).color((available || claimed) ? ColorConstant.PRIMARY : ColorConstant.GRAY).body(()).hover(()).onClick(()).attach(container);
          } 
        }).watch(this.dataSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    RectNode.create(42.0D, 540.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ContainerNode.create(42.0D, 542.0D, 1836.0D, 538.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(1850.0D, 0.0D, 530.0D))
      .body(container -> FlexNode.vertical(0.0D, 53.0D, container.getWidth()).margin(12.0D).body(()).attach(container))
      
      .watch(this.dataSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\clien\\ui\item\UIShopLuckyStatsItemPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */