package fr.paladium.palashop.client.ui.impl.store;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.common.shop.network.page.BBPacketGetPageData;
import fr.paladium.palashop.common.shop.network.page.BBPacketGetPages;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.page.ShopPageData;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import lombok.NonNull;

@UIData(backgroundColor = "#17181B")
public class UIShopStorePage<T extends ShopPageData> extends UIShopPage {
  private static final ListSignal<ShopPage> PAGE_LIST_SIGNAL = new ListSignal();
  
  private final ShopPage page;
  
  private final Class<T> clazz;
  
  private final Signal<T> dataSignal;
  
  public ShopPage getPage() {
    return this.page;
  }
  
  public Class<T> getClazz() {
    return this.clazz;
  }
  
  public Signal<T> getDataSignal() {
    return this.dataSignal;
  }
  
  public UIShopStorePage() {
    this(null, null);
  }
  
  public UIShopStorePage(ShopPage page, Class<T> clazz) {
    super((ShopNavbarElement)ShopNavbarManager.PAGE_STORE);
    this.page = page;
    this.clazz = clazz;
    this.dataSignal = new Signal();
  }
  
  public void init() {
    super.init();
    AtomicBoolean initialized = new AtomicBoolean(false);
    if (PAGE_LIST_SIGNAL.getOrDefault() != null && !PAGE_LIST_SIGNAL.isEmpty()) {
      initPage();
      initialized.set(true);
      if (this.page == null)
        return; 
    } 
    (new BBPacketGetPages()).subscribe(result -> {
          PAGE_LIST_SIGNAL.set(result.getPageList().stream().sorted(Comparator.comparing(ShopPage::getName)).collect(Collectors.toList()));
          if (!initialized.getAndSet(true))
            initPage(); 
        }).send();
    RectNode.create(0.0D, 115.0D, 1920.0D, 59.0D)
      .color(ColorConstant.LIGHT_DARK)
      .onInit(container -> {
          if (!container.isMounted() || PAGE_LIST_SIGNAL.size() == 0)
            return; 
          double width = container.dw(PAGE_LIST_SIGNAL.size());
          FlexNode.horizontal(0.0D, 0.0D, container.getHeight()).body(()).attach(container);
        }).wait((ISignal)PAGE_LIST_SIGNAL)
      .watch((Signal)PAGE_LIST_SIGNAL, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach((UI)this);
  }
  
  private void initPage() {
    if (PAGE_LIST_SIGNAL.isEmpty()) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les pages d'achat", "palashop")).send();
      ZUI.close((UI)this);
      return;
    } 
    if (this.page == null) {
      ZUI.open((UI)((ShopPage)PAGE_LIST_SIGNAL.get(0)).create());
      return;
    } 
    (new BBPacketGetPageData(this.page.getId(), this.clazz.getName())).subscribe(result -> {
          if (result == null || result.getData() == null) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger la page d'achat", "palashop")).send();
            ZUI.close((UI)this);
            return;
          } 
          this.dataSignal.set(result.getData());
        }).send();
    init(this.dataSignal);
  }
  
  public void init(@NonNull Signal<T> dataSignal) {
    if (dataSignal == null)
      throw new NullPointerException("dataSignal is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\store\UIShopStorePage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */