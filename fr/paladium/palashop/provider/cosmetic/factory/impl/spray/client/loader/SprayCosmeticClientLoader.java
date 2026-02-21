package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.loader;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.client.utils.loader.CosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.cache.SprayCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.dto.SprayCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto.SprayCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public class SprayCosmeticClientLoader extends CosmeticClientLoader<SprayCosmetic, SprayCosmeticClient> {
  private static SprayCosmeticClientLoader instance;
  
  private final ResourceBuilder resourceBuilder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  public SprayCosmeticClientLoader() {
    super(SprayCosmeticFactory.ID, (CosmeticClientCache)SprayCosmeticClientCache.getInstance());
    instance = this;
  }
  
  public boolean load(@NonNull SprayCosmetic cosmetic, @NonNull SprayCosmeticClient clientCosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    SprayCosmetic.SprayCosmeticData data = cosmetic.getData();
    Resource texture = loadResource(data.getTexture(), data.getTextureAnimation(), this.resourceBuilder);
    if (texture == null)
      return false; 
    clientCosmetic.load(texture);
    return true;
  }
  
  public static SprayCosmeticClientLoader getInstance() {
    if (instance == null)
      return new SprayCosmeticClientLoader(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\loader\SprayCosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */