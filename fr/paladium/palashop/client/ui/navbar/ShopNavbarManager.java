package fr.paladium.palashop.client.ui.navbar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.client.ui.impl.blackmarket.UIShopBlackMarketPage;
import fr.paladium.palashop.client.ui.impl.home.UIShopHomePage;
import fr.paladium.palashop.client.ui.impl.inventory.UIShopInventoryPage;
import fr.paladium.palashop.client.ui.impl.store.UIShopStorePage;
import fr.paladium.palashop.client.ui.impl.subscription.UIShopSubscriptionsPage;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarPage;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarTab;
import fr.paladium.palashop.server.blackmarket.BlackMarketManager;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import fr.paladium.zephyrui.lib.utils.list.IndexedLinkedList;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ShopNavbarManager {
  private static final IndexedLinkedList<ShopNavbarPage> PAGE_LIST = new IndexedLinkedList();
  
  private static final IndexedLinkedList<ShopNavbarTab> TAB_LIST = new IndexedLinkedList();
  
  public static final ShopNavbarPage PAGE_HOME = new ShopNavbarPage("home", "Accueil", UIShopHomePage.class);
  
  public static final ShopNavbarPage PAGE_STORE = new ShopNavbarPage("store", "Boutique", UIShopStorePage.class);
  
  public static final ShopNavbarPage PAGE_INVENTORY = new ShopNavbarPage("inventory", "Inventaire", UIShopInventoryPage.class);
  
  public static final ShopNavbarTab TAB_SUBSCRIPTIONS = new ShopNavbarTab("subscriptions", "Abonnements", Resource.of(new ResourceLocation("palashop", "textures/ui/navbar/tab/subscriptions.png")), UIShopSubscriptionsPage.class);
  
  public static final ShopNavbarTab TAB_BLACKMARKET = (ShopNavbarTab)(new ShopNavbarTab("blackmarket", "Marché noir", Resource.of(new ResourceLocation("palashop", "textures/ui/navbar/tab/blackmarket.png")), UIShopBlackMarketPage.class)).condition(BlackMarketManager::isActive).index(2147483647);
  
  static {
    registerPage(PAGE_HOME);
    registerPage(PAGE_STORE);
    registerPage(PAGE_INVENTORY);
    registerTab(TAB_SUBSCRIPTIONS);
    registerTab(TAB_BLACKMARKET);
  }
  
  public static void registerPage(@NonNull ShopNavbarPage page) {
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
    PAGE_LIST.add((IndexedElement)page);
  }
  
  public static void registerTab(@NonNull ShopNavbarTab tab) {
    if (tab == null)
      throw new NullPointerException("tab is marked non-null but is null"); 
    TAB_LIST.add((IndexedElement)tab);
  }
  
  public static ShopNavbarPage getPage(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return PAGE_LIST.ordered().stream().filter(page -> page.getId().equals(id)).findFirst().orElse(null);
  }
  
  public static ShopNavbarTab getTab(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return TAB_LIST.ordered().stream().filter(tab -> tab.getId().equals(id)).findFirst().orElse(null);
  }
  
  @NonNull
  public static IndexedLinkedList<ShopNavbarPage> getPageList() {
    return PAGE_LIST;
  }
  
  @NonNull
  public static IndexedLinkedList<ShopNavbarTab> getTabList() {
    return TAB_LIST;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\navbar\ShopNavbarManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */