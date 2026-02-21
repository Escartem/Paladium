package fr.paladium.palashop.common;

import com.google.gson.Gson;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palashop.api.common.tebex.ITebexAPI;
import fr.paladium.palashop.common.blackmarket.network.BBPacketGetBlackMarket;
import fr.paladium.palashop.common.pb.network.SCPacketBuyPB;
import fr.paladium.palashop.common.provider.ProviderManager;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.common.shop.data.ShopPlayer;
import fr.paladium.palashop.common.shop.network.home.BBPacketGetDaily;
import fr.paladium.palashop.common.shop.network.home.BBPacketGetOffers;
import fr.paladium.palashop.common.shop.network.item.BBPacketBuyItem;
import fr.paladium.palashop.common.shop.network.item.BBPacketGetItem;
import fr.paladium.palashop.common.shop.network.page.BBPacketGetPageData;
import fr.paladium.palashop.common.shop.network.page.BBPacketGetPages;
import fr.paladium.palashop.common.shop.network.subscription.BBPacketCancelSubscription;
import fr.paladium.palashop.common.shop.network.subscription.BBPacketGetSubscriptions;
import fr.paladium.palashop.common.shop.network.subscription.BBPacketRenewSubscription;
import fr.paladium.palashop.common.tebex.network.BBPacketGetTebex;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.gson.ShopItemGsonAdapter;
import fr.paladium.palashop.server.shop.dto.page.ShopPageData;
import fr.paladium.palashop.server.shop.dto.page.gson.ShopPageDataGsonAdapter;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public abstract class CommonProxy extends AModProxy {
  private ITebexAPI tebexAPI;
  
  public ITebexAPI getTebexAPI() {
    return this.tebexAPI;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    ProviderManager.load();
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPreInitProviderEvent.pre(event) });
    super.onPreInit(event);
    initAPI();
    initNetwork();
    initExtended();
  }
  
  private void initAPI() {
    this.tebexAPI = createAPI("https://headless.tebex.io/", ITebexAPI.class, new Gson());
  }
  
  private void initNetwork() {
    initNetwork("palashop");
    PacketSerialUtils.getGsonBuilder().registerTypeAdapter(IShopItem.class, new ShopItemGsonAdapter()).registerTypeAdapter(ShopPageData.class, new ShopPageDataGsonAdapter());
    PacketUtils.registerPacket(getNetwork(), SCPacketBuyPB.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetTebex.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetOffers.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetDaily.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetItem.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketBuyItem.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetSubscriptions.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketCancelSubscription.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketRenewSubscription.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetPages.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetPageData.class);
    PacketUtils.registerPacket(getNetwork(), BBPacketGetBlackMarket.class);
  }
  
  private void initExtended() {
    ExtendedUtils.registerExtended(EntityPlayer.class, ShopPlayer.class, "palashop_DATA", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT });
  }
  
  protected <T> T createAPI(@NonNull String url, @NonNull Class<T> service, @NonNull Gson gson) {
    if (url == null)
      throw new NullPointerException("url is marked non-null but is null"); 
    if (service == null)
      throw new NullPointerException("service is marked non-null but is null"); 
    if (gson == null)
      throw new NullPointerException("gson is marked non-null but is null"); 
    OkHttpClient okHttpClient = (new OkHttpClient.Builder()).readTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).build();
    Retrofit retrofit = (new Retrofit.Builder()).baseUrl(url).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create(gson)).client(okHttpClient).build();
    return (T)retrofit.create(service);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */