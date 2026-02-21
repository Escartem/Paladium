package fr.paladium.palashop.client.ui.impl.inventory;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.inventory.manager.ShopInventoryManager;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryInteraction;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.ShopInventoryHolder;
import fr.paladium.palashop.client.ui.impl.inventory.node.ShopInventoryItemNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.client.ui.kit.selector.SelectorNode;
import fr.paladium.palashop.client.ui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class UIShopInventoryPage extends UIShopPage {
  private static final MapSignal<String, List<IShopInventoryElement>> PAGE_LIST_SIGNAL = new MapSignal();
  
  private final StringSignal searchSignal;
  
  private final StringSignal selectorSignal;
  
  private final Signal<ShopInventoryHolder> pageSignal;
  
  private final Signal<ShopInventoryItemNode> contextSignal;
  
  public StringSignal getSearchSignal() {
    return this.searchSignal;
  }
  
  public StringSignal getSelectorSignal() {
    return this.selectorSignal;
  }
  
  public Signal<ShopInventoryHolder> getPageSignal() {
    return this.pageSignal;
  }
  
  public Signal<ShopInventoryItemNode> getContextSignal() {
    return this.contextSignal;
  }
  
  public UIShopInventoryPage() {
    super((ShopNavbarElement)ShopNavbarManager.PAGE_INVENTORY);
    this.searchSignal = new StringSignal();
    this.selectorSignal = new StringSignal();
    this.pageSignal = new Signal();
    this.contextSignal = new Signal();
  }
  
  public void init() {
    super.init();
    this.searchSignal.set(null);
    this.selectorSignal.set(null);
    Map<String, List<IShopInventoryElement>> pageList = new HashMap<>();
    for (Iterator<ShopInventoryHolder> iterator = ShopInventoryManager.getHolderList().iterator(); iterator.hasNext(); ) {
      ShopInventoryHolder page = iterator.next();
      page.get().whenComplete((list, error) -> {
            if (error == null) {
              if (list.isEmpty()) {
                pageList.put(page.getId(), new ArrayList());
                if (pageList.size() == ShopInventoryManager.getHolderList().size())
                  PAGE_LIST_SIGNAL.set(pageList); 
                return;
              } 
              List<IShopInventoryElement> elements = new ArrayList<>();
              for (IShopInventoryElement element : list)
                element.onReceived(()); 
            } else {
              pageList.put(page.getId(), new ArrayList());
              error.printStackTrace();
              if (pageList.size() == ShopInventoryManager.getHolderList().size())
                PAGE_LIST_SIGNAL.set(pageList); 
            } 
          });
    } 
    RectNode.create(0.0D, 115.0D, 1920.0D, 59.0D)
      .color(ColorConstant.LIGHT_DARK)
      .body(container -> {
          double width = container.dw((ShopInventoryManager.getHolderList().size() + 1));
          FlexNode.horizontal(0.0D, 0.0D, container.getHeight()).body(()).attach(container);
        }).attach((UI)this);
    TextNode.create(42.0D, 199.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    ((TextNode)TextNode.create(42.0D, 230.0D)
      .text(Text.create("INVENTAIRE", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .onInit(node -> ((this.pageSignal.getOrDefault() == null) ? "inventaire" : ((ShopInventoryHolder)this.pageSignal.getOrDefault()).getName()).toUpperCase()))
      .watch(this.pageSignal)
      .attach((UI)this);
    SearchTextFieldNode.create(1202.0D, 221.0D, 462.0D)
      .placeholder("Rechercher")
      .onChange((node, oldText, newText) -> this.searchSignal.set(newText))
      .onEnter((node, text) -> SoundConstant.BUTTON_CLICK.copy().play())
      .attach((UI)this);
    SelectorNode.create(1669.0D, 221.0D, 208.0D)
      .onChange((node, selected) -> this.selectorSignal.set(((SelectorNode.SelectorItemNode)selected).getId()))
      .body(selector -> {
          SelectorNode.SelectorItemNode.create().text("rareté").id("rarity").attach(selector);
          SelectorNode.SelectorItemNode.create().text("nom").id("name").attach(selector);
        }).zindex(1)
      .attach((UI)this);
    RectNode.create(42.0D, 281.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ContainerNode.create(42.0D, 283.0D, 1836.0D, 797.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(1850.0D, 0.0D, 755.0D).zlevel(1.0D))
      .body(container -> {
          container.setScrollY(0.0F).updateScrollY();
          GridNode.create(0.0D, 18.0D, container.getWidth(), 0.0D).horizontalMargin(24.0D).verticalMargin(25.0D).body(()).onWatch(()).watch((Signal)this.searchSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.selectorSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container);
        }).watch(this.pageSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)PAGE_LIST_SIGNAL, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    ContainerNode.create(0.0D, 114.0D, 1920.0D, 966.0D)
      .body(container -> {
          if (this.contextSignal.getOrDefault() == null)
            return; 
          ShopInventoryItemNode node = (ShopInventoryItemNode)this.contextSignal.getOrDefault();
          IShopInventoryElement element = node.getElement();
          List<IShopInventoryInteraction> interactions = (List<IShopInventoryInteraction>)ShopInventoryManager.getInteractionList().stream().filter(()).collect(Collectors.toList());
          if (interactions.isEmpty())
            return; 
          TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 20.0F, Color.WHITE);
          double maxWidth = 0.0D;
          double maxHeight = 0.0D;
          for (IShopInventoryInteraction interaction : interactions) {
            double d = textInfo.getWidth(interaction.getName()) + 64.0D;
            if (d > maxWidth)
              maxWidth = d; 
            maxHeight += 44.0D;
          } 
          double padding = 10.0D;
          double width = maxWidth + 20.0D;
          double height = maxHeight + 20.0D;
          double x = node.getAbsoluteX() + node.getWidth() + 22.0D;
          double y = node.getAbsoluteY();
          if (x + width > 1920.0D)
            x = node.getAbsoluteX() - width - 22.0D; 
          if (y + height > 1080.0D)
            y = 1080.0D - height - 22.0D; 
          RectNode.create(x, y, width, height).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create(10.0F)).body(()).onDraw(()).attach(container);
        }).watch(this.contextSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).zlevel(500.0D)
      .attach((UI)this);
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (!context.isCancelled() && this.contextSignal.getOrDefault() != null)
      context.cancel(() -> this.contextSignal.set(null)); 
    super.mousePressed(mouseX, mouseY, clickType, context);
  }
  
  @NonNull
  private Map<IShopInventoryElement, String> getElements(ShopInventoryHolder page) {
    if (PAGE_LIST_SIGNAL.getOrDefault() == null)
      return new HashMap<>(); 
    if (page == null) {
      Map<IShopInventoryElement, String> map = new HashMap<>();
      for (Map.Entry<String, List<IShopInventoryElement>> entry : (Iterable<Map.Entry<String, List<IShopInventoryElement>>>)((Map)PAGE_LIST_SIGNAL.getOrDefault()).entrySet()) {
        for (IShopInventoryElement element : entry.getValue())
          map.put(element, ShopInventoryManager.getHolder(entry.getKey()).getName()); 
      } 
      return map;
    } 
    Map<IShopInventoryElement, String> elements = new HashMap<>();
    for (IShopInventoryElement element : ((Map)PAGE_LIST_SIGNAL.getOrDefault()).get(page.getId()))
      elements.put(element, page.getName()); 
    return elements;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\UIShopInventoryPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */