package fr.paladium.palashop.provider.cosmetic.client.utils.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part.GeckoCosmeticData;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import fr.paladium.zephyrui.lib.resource.dto.animation.AnimationMetadata;
import fr.paladium.zephyrui.lib.resource.dto.decoder.ResourceDecoder;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import javax.imageio.ImageIO;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.LindwormBridge;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public abstract class CosmeticClientLoader<COSMETIC extends ICosmetic, CLIENT_COSMETIC extends ICosmeticClient> {
  public CosmeticClientLoader(String factory, CosmeticClientCache<CLIENT_COSMETIC> cache) {
    this.loadingQueue = new ConcurrentHashMap<>();
    this.lock = new Object();
    this.factory = factory;
    this.cache = cache;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private final String factory;
  
  private final CosmeticClientCache<CLIENT_COSMETIC> cache;
  
  private final Map<CLIENT_COSMETIC, COSMETIC> loadingQueue;
  
  private final Object lock;
  
  private Thread thread;
  
  public String getFactory() {
    return this.factory;
  }
  
  public CosmeticClientCache<CLIENT_COSMETIC> getCache() {
    return this.cache;
  }
  
  public Map<CLIENT_COSMETIC, COSMETIC> getLoadingQueue() {
    return this.loadingQueue;
  }
  
  public Object getLock() {
    return this.lock;
  }
  
  public Thread getThread() {
    return this.thread;
  }
  
  public void start() {
    this.thread = new Thread(() -> {
          while (true) {
            while (this.loadingQueue.isEmpty()) {
              synchronized (this.lock) {
                try {
                  this.lock.wait();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } 
              } 
            } 
            for (Map.Entry<CLIENT_COSMETIC, COSMETIC> entry : this.loadingQueue.entrySet()) {
              ICosmeticClient iCosmeticClient = (ICosmeticClient)entry.getKey();
              ICosmetic iCosmetic = (ICosmetic)entry.getValue();
              if (!iCosmeticClient.isReceived())
                continue; 
              boolean loaded = false;
              try {
                loaded = load((COSMETIC)iCosmetic, (CLIENT_COSMETIC)iCosmeticClient);
              } catch (Exception e) {
                loaded = false;
                System.err.println("Failed to load cosmetic " + iCosmetic.getId());
                e.printStackTrace();
              } 
              if (!loaded)
                this.cache.getCache().invalidate(iCosmetic.getId()); 
              this.loadingQueue.remove(iCosmeticClient);
            } 
          } 
        }"Cosmetic-" + this.factory + "/Loader");
    this.thread.start();
  }
  
  public void request(@NonNull CLIENT_COSMETIC clientCosmetic, @NonNull COSMETIC cosmetic) {
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    this.loadingQueue.put(clientCosmetic, cosmetic);
    synchronized (this.lock) {
      this.lock.notify();
    } 
  }
  
  protected Resource loadResource(String encodedTexture) {
    return loadResource(encodedTexture, null, null);
  }
  
  protected Resource loadResource(String encodedTexture, String textureAnimation) {
    return loadResource(encodedTexture, textureAnimation, null);
  }
  
  protected Resource loadResource(String encodedTexture, ResourceBuilder resourceBuilder) {
    return loadResource(encodedTexture, null, resourceBuilder);
  }
  
  protected Resource loadResource(String encodedTexture, String textureAnimation, ResourceBuilder resourceBuilder) {
    try {
      if (encodedTexture == null)
        return null; 
      BufferedImage bufferedTexture = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(encodedTexture)));
      if (bufferedTexture == null)
        return null; 
      Resource texture = (resourceBuilder != null) ? resourceBuilder.of(bufferedTexture) : Resource.of(bufferedTexture);
      if (textureAnimation != null)
        texture.decoder(ResourceDecoder.animated(bufferedTexture, ((AnimationMetadata)GSON.fromJson(textureAnimation, AnimationMetadata.class)).getMetadata(bufferedTexture))); 
      return texture;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  protected <T extends software.bernie.geckolib3.core.IAnimatable> LindwormModel<T> loadCosmetic(String id, GeckoCosmeticData data, @NonNull BiFunction<LindwormModel<T>, Entity, T> animatableBuilder) {
    if (animatableBuilder == null)
      throw new NullPointerException("animatableBuilder is marked non-null but is null"); 
    return loadCosmetic(id, data, null, animatableBuilder);
  }
  
  protected <T extends software.bernie.geckolib3.core.IAnimatable> LindwormModel<T> loadCosmetic(String id, GeckoCosmeticData data, ResourceBuilder resourceBuilder, @NonNull BiFunction<LindwormModel<T>, Entity, T> animatableBuilder) {
    if (animatableBuilder == null)
      throw new NullPointerException("animatableBuilder is marked non-null but is null"); 
    return loadCosmetic(id, data.getModel(), data.getAnimation(), loadResource(data.getTexture(), data.getTextureAnimation(), resourceBuilder), animatableBuilder);
  }
  
  protected <T extends software.bernie.geckolib3.core.IAnimatable> LindwormModel<T> loadCosmetic(String id, String model, String animation, Resource resource, @NonNull BiFunction<LindwormModel<T>, Entity, T> animatableBuilder) {
    if (animatableBuilder == null)
      throw new NullPointerException("animatableBuilder is marked non-null but is null"); 
    if (id == null || model == null)
      return null; 
    GeoModel geoModel = LindwormBridge.MODEL_LOADER.loadModel(id, model);
    if (geoModel == null)
      return null; 
    AnimationFile animationFile = null;
    if (animation != null)
      animationFile = LindwormBridge.ANIMATION_LOADER.loadAllAnimations(LindwormBridge.MOLANG_PARSER, (JsonObject)GSON.fromJson(animation, JsonObject.class)); 
    LindwormModel<T> cosmetic = new LindwormModel(geoModel, animationFile, resource, animatableBuilder);
    return cosmetic;
  }
  
  public abstract boolean load(@NonNull COSMETIC paramCOSMETIC, @NonNull CLIENT_COSMETIC paramCLIENT_COSMETIC);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\utils\loader\CosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */