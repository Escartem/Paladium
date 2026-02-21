package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.cache;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.loader.EmoteCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import lombok.NonNull;

public class EmoteCosmeticClientCache extends CosmeticClientCache<EmoteCosmeticClient> {
  private static EmoteCosmeticClientCache instance;
  
  public EmoteCosmeticClientCache() {
    instance = this;
  }
  
  public EmoteCosmeticClient build(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return new EmoteCosmeticClient(id);
  }
  
  public void onReceive(@NonNull EmoteCosmeticClient clientCosmetic, @NonNull ICosmetic cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!(cosmetic instanceof EmoteCosmetic))
      return; 
    EmoteCosmetic data = (EmoteCosmetic)cosmetic;
    clientCosmetic.receive(data.getProperties());
    EmoteCosmeticClientLoader.getInstance().request((ICosmeticClient)clientCosmetic, (ICosmetic)data);
  }
  
  @NonNull
  public static EmoteCosmeticClientCache getInstance() {
    if (instance == null)
      return new EmoteCosmeticClientCache(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\cache\EmoteCosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */