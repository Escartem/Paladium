package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.loader;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.client.utils.loader.CosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.cache.WearableCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class WearableCosmeticClientLoader extends CosmeticClientLoader<WearableCosmetic, WearableCosmeticClient> {
  private static WearableCosmeticClientLoader instance;
  
  private final ResourceBuilder resourceBuilder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  public WearableCosmeticClientLoader() {
    super(WearableCosmeticFactory.ID, (CosmeticClientCache)WearableCosmeticClientCache.getInstance());
    instance = this;
  }
  
  public boolean load(@NonNull WearableCosmetic cosmetic, @NonNull WearableCosmeticClient clientCosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    WearableCosmetic.WearableCosmeticData data = cosmetic.getData();
    LindwormModel model = loadCosmetic(cosmetic.getId(), data.getModel(), this.resourceBuilder, fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.animation.WearableCosmeticAnimatable::new);
    Resource thumbnail = loadResource(data.getThumbnail());
    if (model == null || model.getTexture() == null || thumbnail == null)
      return false; 
    clientCosmetic.load(model, thumbnail);
    return true;
  }
  
  public static WearableCosmeticClientLoader getInstance() {
    if (instance == null)
      return new WearableCosmeticClientLoader(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\loader\WearableCosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */