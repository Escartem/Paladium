package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.loader;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.client.utils.loader.CosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.impl.TextureCosmeticData;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.cache.CloakCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto.CloakCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.dto.CloakCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public class CloakCosmeticClientLoader extends CosmeticClientLoader<CloakCosmetic, CloakCosmeticClient> {
  private static CloakCosmeticClientLoader instance;
  
  private final ResourceBuilder resourceBuilder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  public CloakCosmeticClientLoader() {
    super(CloakCosmeticFactory.ID, (CosmeticClientCache)CloakCosmeticClientCache.getInstance());
    instance = this;
  }
  
  public boolean load(@NonNull CloakCosmetic cosmetic, @NonNull CloakCosmeticClient clientCosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    TextureCosmeticData data = cosmetic.getData();
    Resource texture = loadResource(data.getTexture(), data.getTextureAnimation(), this.resourceBuilder);
    Resource thumbnail = loadResource(data.getThumbnail());
    if (texture == null || thumbnail == null)
      return false; 
    clientCosmetic.load(texture, thumbnail);
    return true;
  }
  
  public static CloakCosmeticClientLoader getInstance() {
    if (instance == null)
      return new CloakCosmeticClientLoader(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\loader\CloakCosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */