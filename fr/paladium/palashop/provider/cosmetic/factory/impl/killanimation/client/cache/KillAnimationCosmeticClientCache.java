package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.loader.KillAnimationCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import lombok.NonNull;

public class KillAnimationCosmeticClientCache extends CosmeticClientCache<KillAnimationCosmeticClient> {
  private static KillAnimationCosmeticClientCache instance;
  
  public KillAnimationCosmeticClientCache() {
    instance = this;
  }
  
  public KillAnimationCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new KillAnimationCosmeticClient(id);
  }
  
  public void onReceive(@NonNull KillAnimationCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof KillAnimationCosmetic))
      return; 
    KillAnimationCosmetic data = (KillAnimationCosmetic)cosmetic;
    clientCosmetic.receive(data.getProperties());
    KillAnimationCosmeticClientLoader.getInstance().request((ICosmeticClient)clientCosmetic, (ICosmetic)data);
  }
  
  @NonNull
  public static KillAnimationCosmeticClientCache getInstance() {
    if (instance == null)
      return new KillAnimationCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\cache\KillAnimationCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */