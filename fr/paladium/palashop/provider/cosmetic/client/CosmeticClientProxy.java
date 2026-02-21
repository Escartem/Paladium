package fr.paladium.palashop.provider.cosmetic.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import fr.paladium.palashop.client.ui.impl.inventory.manager.ShopInventoryManager;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryInteraction;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.ShopInventoryHolder;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarTab;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.inventory.CosmeticShopInventoryHolder;
import fr.paladium.palashop.provider.cosmetic.client.inventory.interaction.CosmeticEquipShopInventoryInteraction;
import fr.paladium.palashop.provider.cosmetic.client.inventory.interaction.CosmeticOpenShopInventoryInteraction;
import fr.paladium.palashop.provider.cosmetic.client.inventory.interaction.CosmeticUnEquipShopInventoryInteraction;
import fr.paladium.palashop.provider.cosmetic.client.listener.CosmeticKeybindListener;
import fr.paladium.palashop.provider.cosmetic.client.listener.CosmeticRenderPlayerListener;
import fr.paladium.palashop.provider.cosmetic.client.render.ui.inventory.CosmeticInventoryThumbnailRenderer;
import fr.paladium.palashop.provider.cosmetic.client.render.ui.shop.CosmeticShopRenderer;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.UIShopCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.CosmeticCommonProxy;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

public class CosmeticClientProxy extends CosmeticCommonProxy {
  public static ShopNavbarTab TAB_COSMETIC;
  
  private KeyBinding selectKey;
  
  public KeyBinding getSelectKey() {
    return this.selectKey;
  }
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initNavbar();
    initRender();
    initKeybind();
    initListener();
  }
  
  private void initNavbar() {
    ShopNavbarManager.registerTab(TAB_COSMETIC = (ShopNavbarTab)(new ShopNavbarTab("cosmetic", "Cosmétique", Resource.of(new ResourceLocation("palashop", "textures/ui/navbar/tab/cosmetic.png")), UIShopCosmetic.class)).index(-2147483647));
    ShopInventoryManager.registerHolder((ShopInventoryHolder)new CosmeticShopInventoryHolder());
    ShopInventoryManager.registerInteraction((IShopInventoryInteraction)new CosmeticOpenShopInventoryInteraction());
    ShopInventoryManager.registerInteraction((IShopInventoryInteraction)new CosmeticEquipShopInventoryInteraction());
    ShopInventoryManager.registerInteraction((IShopInventoryInteraction)new CosmeticUnEquipShopInventoryInteraction());
  }
  
  private void initRender() {
    CosmeticProvider.getInstance().registerRenderer("shop_thumbnail", (ShopElementRenderer)new CosmeticShopRenderer());
    CosmeticProvider.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new CosmeticShopRenderer());
    CosmeticProvider.getInstance().registerRenderer("inventory_thumbnail", (ShopElementRenderer)new CosmeticInventoryThumbnailRenderer());
  }
  
  private void initKeybind() {
    this.selectKey = new KeyBinding("key.cosmetic.select", 34, "key.categories.cosmetic");
    if (this.selectKey instanceof IKeyBinding)
      ((IKeyBinding)this.selectKey).setSubCategory("global"); 
    ClientRegistry.registerKeyBinding(this.selectKey);
  }
  
  private void initListener() {
    addListener(new Class[] { CosmeticKeybindListener.class });
    addListener(new Class[] { CosmeticRenderPlayerListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\CosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */