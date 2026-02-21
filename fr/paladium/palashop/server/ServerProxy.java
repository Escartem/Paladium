package fr.paladium.palashop.server;

import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palashop.api.server.blackmarket.IBlackMarketAPI;
import fr.paladium.palashop.api.server.giftcard.IGiftCardAPI;
import fr.paladium.palashop.api.server.pb.IPBAPI;
import fr.paladium.palashop.api.server.provider.IProviderAPI;
import fr.paladium.palashop.api.server.shop.IShopAPI;
import fr.paladium.palashop.common.CommonProxy;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPostInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.server.blackmarket.BlackMarketManager;
import fr.paladium.palashop.server.blackmarket.command.BlackMarketCommand;
import fr.paladium.palashop.server.config.PalaShopConfig;
import fr.paladium.palashop.server.pb.command.PBCommand;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.command.PalaShopCommand;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.gson.ShopItemGsonAdapter;
import fr.paladium.palashop.server.shop.listener.SubscriptionsPopupListener;
import lombok.NonNull;

public class ServerProxy extends CommonProxy {
  private PalaShopConfig config;
  
  private IPBAPI pbAPI;
  
  private IShopAPI shopAPI;
  
  private IGiftCardAPI giftcardAPI;
  
  private IProviderAPI providerAPI;
  
  private IBlackMarketAPI blackMarketAPI;
  
  public PalaShopConfig getConfig() {
    return this.config;
  }
  
  public IPBAPI getPbAPI() {
    return this.pbAPI;
  }
  
  public IShopAPI getShopAPI() {
    return this.shopAPI;
  }
  
  public IGiftCardAPI getGiftcardAPI() {
    return this.giftcardAPI;
  }
  
  public IProviderAPI getProviderAPI() {
    return this.providerAPI;
  }
  
  public IBlackMarketAPI getBlackMarketAPI() {
    return this.blackMarketAPI;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPreInitProviderEvent.post(event) });
  }
  
  public void onInit(FMLInitializationEvent event) {
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModInitProviderEvent.pre(event) });
    super.onInit(event);
    initConfig();
    initAPI();
    initCommand();
    initListener();
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModInitProviderEvent.post(event) });
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPostInitProviderEvent.pre(event) });
    super.onPostInit(event);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPostInitProviderEvent.post(event) });
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    ShopManager.load();
    BlackMarketManager.load();
  }
  
  private void initConfig() {
    this.config = (PalaShopConfig)ConfigurationManager.getInstance().register(PalaShopConfig.class);
  }
  
  private void initAPI() {
    this.pbAPI = createAPI("pb", IPBAPI.class);
    this.shopAPI = createAPI("shop", IShopAPI.class);
    this.giftcardAPI = createAPI("giftcard", IGiftCardAPI.class);
    this.providerAPI = createAPI("provider", IProviderAPI.class);
    this.blackMarketAPI = createAPI("blackmarket", IBlackMarketAPI.class);
  }
  
  private void initCommand() {
    CommandRegistry.register(new Class[] { PBCommand.class, PalaShopCommand.class, BlackMarketCommand.class });
  }
  
  private void initListener() {
    addListener(new Class[] { SubscriptionsPopupListener.class });
  }
  
  public <T> T createAPI(@NonNull String url, @NonNull Class<T> clazz) {
    if (url == null)
      throw new NullPointerException("url is marked non-null but is null"); 
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (url.startsWith("/"))
      url = url.substring(1); 
    if (url.endsWith("/"))
      url = url.substring(0, url.length() - 1); 
    String api = this.config.getApi().endsWith("/") ? this.config.getApi().substring(0, this.config.getApi().length() - 1) : this.config.getApi();
    return (T)createAPI(api + "/" + url + "/", clazz, (new GsonBuilder()).registerTypeAdapter(IShopItem.class, new ShopItemGsonAdapter()).create());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */