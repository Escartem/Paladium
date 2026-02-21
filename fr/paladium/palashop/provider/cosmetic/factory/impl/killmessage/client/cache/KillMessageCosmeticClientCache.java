package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto.KillMessageCosmetic;
import lombok.NonNull;

public class KillMessageCosmeticClientCache extends CosmeticClientCache<KillMessageCosmeticClient> {
  private static KillMessageCosmeticClientCache instance;
  
  public KillMessageCosmeticClientCache() {
    instance = this;
  }
  
  public KillMessageCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new KillMessageCosmeticClient(id);
  }
  
  public void onReceive(@NonNull KillMessageCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof KillMessageCosmetic))
      return; 
    KillMessageCosmetic data = (KillMessageCosmetic)cosmetic;
    clientCosmetic.receive((KillMessageCosmetic.KillCosmeticProperties)data.getProperties());
  }
  
  @NonNull
  public static KillMessageCosmeticClientCache getInstance() {
    if (instance == null)
      return new KillMessageCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\cache\KillMessageCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */