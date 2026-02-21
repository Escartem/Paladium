package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.WearableCosmeticClientProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.WearableCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.equipped.WearableEquippedCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.WearableCosmeticServerProxy;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class WearableCosmeticFactory extends CosmeticFactory {
  public static String ID = "wearable";
  
  private static WearableCosmeticFactory instance;
  
  private static WearableCosmeticCommonProxy proxy;
  
  public WearableCosmeticFactory(@NonNull CosmeticProvider provider) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    super(provider, ID);
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    instance = this;
    proxy = (WearableCosmeticCommonProxy)provider.createProxy("fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.WearableCosmeticClientProxy", "fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.WearableCosmeticServerProxy");
    ProviderDispatcher.register(proxy);
  }
  
  @ProviderListener
  public void onEvent(ProviderEvent event) {
    dispatch(event);
  }
  
  public void dispatch(@NonNull ProviderEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    if (event.isGlobal())
      return; 
    ProviderDispatcher.dispatch(proxy, event);
  }
  
  @NonNull
  public static WearableCosmeticCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static WearableCosmeticClientProxy getClient() {
    return (WearableCosmeticClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static WearableCosmeticServerProxy getServer() {
    return (WearableCosmeticServerProxy)proxy;
  }
  
  @NonNull
  public <T extends ICosmetic> List<T> getCosmetics() {
    return proxy.getCosmetics();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return proxy.getCosmetic(id);
  }
  
  public CosmeticPlayer.EquippedCosmetic getEquippedCosmetic() {
    return (CosmeticPlayer.EquippedCosmetic)new WearableEquippedCosmetic();
  }
  
  @NonNull
  public static WearableCosmeticFactory getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\WearableCosmeticFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */