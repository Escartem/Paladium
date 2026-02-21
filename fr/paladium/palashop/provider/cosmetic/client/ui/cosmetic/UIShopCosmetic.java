package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.client.ui.kit.selector.SelectorNode;
import fr.paladium.palashop.client.ui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.CosmeticClientProxy;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.CosmeticWheelSelectorNode;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render.CosmeticElementRenderRendererNode;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render.CosmeticElementThumbnailRendererNode;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmetics;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmeticsCount;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketClearCosmeticPlayerOutfit;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketSetCosmeticPlayerOutfit;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.mouse.NodeMousePressedCallback;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.position.PositionProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIShopCosmetic extends UIShopPage {
  public static final Signal<CosmeticPlayer> cosmeticPlayerSignal = new Signal(CosmeticPlayer.me());
  
  private static final MapSignal<ShopRarity, Integer> cosmeticCountSignal = new MapSignal();
  
  private static final ListSignal<ICosmeticClient> cosmeticsSignal = new ListSignal();
  
  private static final BooleanSignal emptySignal = new BooleanSignal(true);
  
  private final ListSignal<ICosmetic> equippedCosmeticsSignal;
  
  private final Signal<CosmeticNavbarElement> selectedNavbarElementSignal;
  
  private final MapSignal<CosmeticNavbarElement, Node> navbarElementNodeMapSignal;
  
  private final BooleanSignal draggingSignal;
  
  private final StringSignal searchSignal;
  
  private final StringSignal selectorSignal;
  
  private ContainerNode containerNode;
  
  private CosmeticWheelSelectorNode wheelSelectorNode;
  
  public UIShopCosmetic() {
    super((ShopNavbarElement)CosmeticClientProxy.TAB_COSMETIC);
    this.equippedCosmeticsSignal = new ListSignal(new ArrayList());
    this.selectedNavbarElementSignal = new Signal();
    this.navbarElementNodeMapSignal = new MapSignal(new HashMap<>());
    this.draggingSignal = new BooleanSignal(false);
    this.searchSignal = new StringSignal();
    this.selectorSignal = new StringSignal();
  }
  
  public void init() {
    super.init();
    this.searchSignal.set(null);
    this.selectorSignal.set(null);
    if (CosmeticNavbarManager.getElementList().isEmpty()) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les cosmétiques", "palashop")).send();
      ZUI.close((UI)this);
      return;
    } 
    (new BBPacketGetCosmetics()).subscribe(data -> {
          if (data.getData() == null) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send();
            ZUI.close((UI)this);
            return;
          } 
          List<ICosmeticClient> cosmetics = new ArrayList<>();
          for (ShopCosmeticData cosmeticData : data.getData()) {
            Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(cosmeticData.getFactory());
            if (!optionalFactory.isPresent())
              continue; 
            Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(cosmeticData.getCosmetic());
            if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof ICosmeticClient))
              continue; 
            cosmetics.add((ICosmeticClient)optionalCosmetic.get());
          } 
          List<ICosmeticClient> loadedCosmetics = new ArrayList<>();
          if (cosmetics.isEmpty()) {
            cosmeticsSignal.set(loadedCosmetics);
            return;
          } 
          cosmetics.forEach(());
        }).send();
    (new BBPacketGetCosmeticsCount()).subscribe(data -> {
          if (data.getData() == null) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send();
            ZUI.close((UI)this);
            return;
          } 
          cosmeticCountSignal.set(data.getData());
        }).send();
    if (this.selectedNavbarElementSignal.getOrDefault() == null)
      this.selectedNavbarElementSignal.set(CosmeticNavbarManager.getElementList().getFirst()); 
    SearchTextFieldNode.create(152.0D, 136.0D, 504.0D)
      .placeholder("Rechercher")
      .onChange((node, oldText, newText) -> this.searchSignal.set(newText))
      .onEnter((node, text) -> SoundConstant.BUTTON_CLICK.copy().play())
      .attach((UI)this);
    SelectorNode.create(661.0D, 136.0D, 190.0D)
      .onChange((node, selected) -> this.selectorSignal.set(((SelectorNode.SelectorItemNode)selected).getId()))
      .body(selector -> {
          SelectorNode.SelectorItemNode.create().text("rareté").id("rarity").zlevel(500.0D).zindex(500).attach(selector);
          SelectorNode.SelectorItemNode.create().text("équipé").id("equipped").zlevel(500.0D).zindex(500).attach(selector);
          SelectorNode.SelectorItemNode.create().text("nom").id("name").zlevel(500.0D).zindex(500).attach(selector);
        }).zlevel(500.0D)
      .zindex(500)
      .attach((UI)this);
    FlexNode.horizontal(1049.0D, 202.0D, 49.0D)
      .margin(20.0D)
      .align(Align.CENTER)
      .body(flex -> {
          ((TextNode)TextNode.create(0.0D, 0.0D).onInit(())).watch(cosmeticPlayerSignal).attach(flex);
          ((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 29.0D, 29.0D).resourceHeight(10.0D).resource(ResourceConstant.ICON_TRASH).onClick(())).hover(()).attach(flex);
        }).visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .attach((UI)this);
    RectNode.create(1049.0D, 263.0D, 700.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .attach((UI)this);
    FlexNode.horizontal(1749.0D, 202.0D, 49.0D)
      .margin(5.0D)
      .align(Align.CENTER)
      .body(flex -> {
          ((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 29.0D, 29.0D).resourceHeight(10.0D).resource(ResourceConstant.ICON_ARROW_LEFT).onClick(())).hover(()).attach(flex);
          ((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 29.0D, 29.0D).resourceHeight(10.0D).resource(ResourceConstant.ICON_ARROW_RIGHT).onClick(())).hover(()).attach(flex);
        }).visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .anchorX(Align.END)
      .attach((UI)this);
    ContainerNode.create(1049.0D, 265.0D, 700.0D, 815.0D)
      .body(container -> {
          if (this.selectedNavbarElementSignal.getOrDefault() == null)
            return; 
          CosmeticNavbarElement element = (CosmeticNavbarElement)this.selectedNavbarElementSignal.getOrDefault();
          CosmeticPlayer.OutfitCosmetic outfit = ((CosmeticPlayer)cosmeticPlayerSignal.getOrDefault()).getOutfit();
          Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = outfit.get(element.getFactory());
          if (!optionalEquippedCosmetic.isPresent()) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger cette catégorie de cosmétique", "palashop")).send();
            return;
          } 
          CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
          if (equippedCosmetic.getType() == CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.UNIQUE) {
            CosmeticNavbarElement object = (CosmeticNavbarElement)this.selectedNavbarElementSignal.getOrDefault();
            ShopElementRenderer renderer = CosmeticProvider.getInstance().getRenderer("cosmetic_render", object);
            if (container.getChildren().size() == 1 && renderer == null && ((CosmeticElementRenderRendererNode)container.getChild(0, CosmeticElementRenderRendererNode.class)).getRenderer() == null)
              return; 
            container.clearChildren();
            CosmeticElementRenderRendererNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight()).provider(CosmeticProvider.getInstance().getId()).object(object).attach(container);
          } else if (equippedCosmetic.getType() == CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.WHEEL) {
            container.clearChildren();
            TextNode.create(0.0D, 53.0D).text(Text.create(((CosmeticNavbarElement)this.selectedNavbarElementSignal.getOrDefault()).getName(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE)).modifier(TextModifier.CAPITALIZE).horizontalAlign(Align.CENTER)).width(container.getWidth()).attach(container);
            this.wheelSelectorNode = CosmeticWheelSelectorNode.create(container.dw(2.0D) - 251.0D, 121.0D, 502.0D, 502.0D);
            this.wheelSelectorNode.element(element).dragging(this.draggingSignal).attach(container);
          } 
        }).visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .watch(this.selectedNavbarElementSignal, new WatchProperty[] { WatchProperty.BODY }).watch(cosmeticPlayerSignal, new WatchProperty[] { WatchProperty.BODY }).attach((UI)this);
    FlexNode.vertical(50.0D, 202.0D, 48.0D)
      .margin(44.0D)
      .align(Align.CENTER)
      .body(flex -> {
          for (CosmeticNavbarElement element : CosmeticNavbarManager.getElementList()) {
            ((ImageNode)ImageNode.create(0.0D, 0.0D, flex.getWidth(), flex.getWidth()).resource(element.getResource()).onInit(())).hoveredColor(Color.WHITE).onClick(()).enabled(()).hover(()).watch((Signal)this.navbarElementNodeMapSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).watch(this.selectedNavbarElementSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach(flex);
          } 
        }).attach((UI)this);
    this.containerNode = ContainerNode.create(152.0D, 190.0D, 700.0D, 769.0D);
    this.containerNode
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(710.0D, 0.0D, 769.0D))
      .body(container -> {
          if (cosmeticsSignal.getOrDefault() == null)
            return; 
          FlexNode.vertical(0.0D, 12.0D, container.getWidth()).margin(48.0D).body(()).watch((Signal)this.selectorSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container);
        }).onWatch((node, signal, properties) -> {
          if (signal == this.searchSignal)
            node.setScrollY(0.0F).updateScrollY(); 
        }).enabled(node -> !((Boolean)this.draggingSignal.getOrDefault()).booleanValue())
      .watch((Signal)this.searchSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)cosmeticsSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    RectNode.create(152.0D, 971.0D, 700.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .attach((UI)this);
    FlexNode.horizontal(152.0D, 983.0D, 84.0D)
      .margin(27.0D)
      .body(flex -> {
          for (ShopRarity rarity : ShopRarity.values()) {
            ContainerNode.create(0.0D, 0.0D, 54.0D, flex.getHeight()).body(()).hover(()).watch((Signal)cosmeticCountSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(flex);
          } 
        }).visible(node -> !((Boolean)emptySignal.getOrDefault()).booleanValue())
      .attach((UI)this);
    FlexNode.vertical(553.0D, 348.0D, 814.0D)
      .margin(25.0D)
      .body(flex -> {
          ContainerNode.create(0.0D, 0.0D, flex.getWidth(), 333.0D).body(()).attach(flex);
          FlexNode.vertical(0.0D, 0.0D, flex.getWidth()).margin(10.0D).body(()).attach(flex);
        }).visible(node -> ((Boolean)emptySignal.getOrDefault()).booleanValue())
      .attach((UI)this);
  }
  
  private void setSelected(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (this.navbarElementNodeMapSignal.getOrDefault() == null || !this.navbarElementNodeMapSignal.containsKey(element))
      return; 
    this.selectedNavbarElementSignal.set(element);
    this.containerNode.setScrollY(-(((Node)this.navbarElementNodeMapSignal.get(element)).getAbsoluteY() - this.containerNode.getScrollY() - this.containerNode.getAbsoluteY()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\UIShopCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */