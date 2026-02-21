package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.loader;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.client.utils.loader.CosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.cache.KillAnimationCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class KillAnimationCosmeticClientLoader extends CosmeticClientLoader<KillAnimationCosmetic, KillAnimationCosmeticClient> {
  private static KillAnimationCosmeticClientLoader instance;
  
  private final ResourceBuilder resourceBuilder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  public KillAnimationCosmeticClientLoader() {
    super(KillAnimationCosmeticFactory.ID, (CosmeticClientCache)KillAnimationCosmeticClientCache.getInstance());
    instance = this;
  }
  
  public boolean load(@NonNull KillAnimationCosmetic cosmetic, @NonNull KillAnimationCosmeticClient clientCosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    KillAnimationCosmetic.KillCosmeticData data = cosmetic.getData();
    LindwormModel kill = loadCosmetic(cosmetic.getId(), data.getKill(), this.resourceBuilder, fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.animation.KillAnimationCosmeticAnimatable::new);
    LindwormModel player = loadCosmetic(cosmetic.getId(), data.getPlayer(), fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.animation.KillAnimationCosmeticAnimatable::new);
    Resource thumbnail = loadResource(data.getThumbnail());
    if (kill == null || kill.getTexture() == null || thumbnail == null)
      return false; 
    clientCosmetic.load(kill, player, thumbnail);
    return true;
  }
  
  public static KillAnimationCosmeticClientLoader getInstance() {
    if (instance == null)
      return new KillAnimationCosmeticClientLoader(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\loader\KillAnimationCosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */