package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto.CloakCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.loader.CloakCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.dto.CloakCosmetic;
import lombok.NonNull;

public class CloakCosmeticClientCache extends CosmeticClientCache<CloakCosmeticClient> {
  private static CloakCosmeticClientCache instance;
  
  public CloakCosmeticClientCache() {
    instance = this;
  }
  
  public CloakCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new CloakCosmeticClient(id);
  }
  
  public void onReceive(@NonNull CloakCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof CloakCosmetic))
      return; 
    CloakCosmetic data = (CloakCosmetic)cosmetic;
    clientCosmetic.receive(data.getProperties());
    CloakCosmeticClientLoader.getInstance().request((ICosmeticClient)clientCosmetic, (ICosmetic)data);
  }
  
  @NonNull
  public static CloakCosmeticClientCache getInstance() {
    if (instance == null)
      return new CloakCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\cache\CloakCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */