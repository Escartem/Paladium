package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.loader.WearableCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import lombok.NonNull;

public class WearableCosmeticClientCache extends CosmeticClientCache<WearableCosmeticClient> {
  private static WearableCosmeticClientCache instance;
  
  public WearableCosmeticClientCache() {
    instance = this;
  }
  
  public WearableCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new WearableCosmeticClient(id);
  }
  
  public void onReceive(@NonNull WearableCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof WearableCosmetic))
      return; 
    WearableCosmetic data = (WearableCosmetic)cosmetic;
    clientCosmetic.receive(data.getType(), (WearableCosmetic.WearableCosmeticProperties)data.getProperties());
    WearableCosmeticClientLoader.getInstance().request((ICosmeticClient)clientCosmetic, (ICosmetic)data);
  }
  
  @NonNull
  public static WearableCosmeticClientCache getInstance() {
    if (instance == null)
      return new WearableCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\cache\WearableCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */