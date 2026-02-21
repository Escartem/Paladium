package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.dto.SprayCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.loader.SprayCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto.SprayCosmetic;
import lombok.NonNull;

public class SprayCosmeticClientCache extends CosmeticClientCache<SprayCosmeticClient> {
  private static SprayCosmeticClientCache instance;
  
  public SprayCosmeticClientCache() {
    instance = this;
  }
  
  public SprayCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new SprayCosmeticClient(id);
  }
  
  public void onReceive(@NonNull SprayCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof SprayCosmetic))
      return; 
    SprayCosmetic data = (SprayCosmetic)cosmetic;
    clientCosmetic.receive(data.getProperties());
    SprayCosmeticClientLoader.getInstance().request((ICosmeticClient)clientCosmetic, (ICosmetic)data);
  }
  
  @NonNull
  public static SprayCosmeticClientCache getInstance() {
    if (instance == null)
      return new SprayCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\cache\SprayCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */