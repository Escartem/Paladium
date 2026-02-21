package fr.paladium.palamod.modules.shop.client.ui.container;

import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.selector.SelectorNode;
import fr.paladium.paladiumui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palamod.modules.shop.client.ui.node.ShopSlotNode;
import fr.paladium.palamod.modules.shop.client.ui.signal.ItemCategorySignal;
import fr.paladium.palamod.modules.shop.data.ItemCategory;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;

public class ShopContainer extends RectNode {
  private ItemShopDisplayContainer itemDisplayContainer = null;
  
  private final StringSignal searchSignal = new StringSignal("");
  
  private final ItemCategorySignal categorySignal = new ItemCategorySignal(ItemCategory.ALL);
  
  private final MapSignal<String, ShopItem> itemsMap = new MapSignal(new LinkedHashMap<>());
  
  protected ShopContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    SelectorNode.create(41.0D, 36.0D, 287.0D)
      .onChange((node, selected) -> this.categorySignal.set(ItemCategory.valueOf(((SelectorNode.SelectorItemNode)selected).getId())))
      
      .zindex(1)
      .zlevel(50.0D)
      .body(selector -> {
          for (ItemCategory category : ItemCategory.values())
            SelectorNode.SelectorItemNode.create().id(category.name()).text(category.name()).attach(selector); 
        }).attach((Node)this);
    SearchTextFieldNode.create(692.0D, 38.0D, 273.0D)
      .placeholder("RECHERCHER...")
      .onChange((node, oldText, newText) -> this.searchSignal.set(newText))
      .attach((Node)this);
    ContainerNode.create(41.0D, 123.0D, 847.0D, 526.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(893.0D, 0.0D, 526.0D))
      .onInit(nodeContainer -> {
          if (!this.itemsMap.isPresent()) {
            FlexNode.vertical(0.0D, 0.0D, 847.0D).margin(9.0D).body(()).attach(nodeContainer);
            return;
          } 
          Map<String, ShopItem> copy = (Map<String, ShopItem>)((Map)this.itemsMap.getOrDefault()).entrySet().stream().filter(()).filter(()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (), LinkedHashMap::new));
          if (copy.isEmpty())
            return; 
          Iterator<Map.Entry<String, ShopItem>> mapIterator = copy.entrySet().iterator();
          FlexNode.vertical(0.0D, 0.0D, 847.0D).margin(9.0D).body(()).attach(nodeContainer);
        }).watch((Signal)this.itemsMap, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).watch((Signal)this.searchSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).watch((Signal)this.categorySignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).wait((ISignal)this.itemsMap)
      .attach((Node)this);
  }
  
  public static ShopContainer create(double x, double y, double width, double height) {
    return new ShopContainer(x, y, width, height);
  }
  
  public ShopContainer setDisplayContainer(ItemShopDisplayContainer itemDisplayContainer) {
    this.itemDisplayContainer = itemDisplayContainer;
    return this;
  }
  
  public ShopContainer setItemsMap(MapSignal<String, ShopItem> itemsMap) {
    this.itemsMap.set(itemsMap.getOrDefault());
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\container\ShopContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */