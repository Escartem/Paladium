package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.loader;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.client.utils.loader.CosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.cache.EmoteCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class EmoteCosmeticClientLoader extends CosmeticClientLoader<EmoteCosmetic, EmoteCosmeticClient> {
  private static EmoteCosmeticClientLoader instance;
  
  private final ResourceBuilder resourceBuilder = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  private String model;
  
  public EmoteCosmeticClientLoader() {
    super(EmoteCosmeticFactory.ID, (CosmeticClientCache)EmoteCosmeticClientCache.getInstance());
    instance = this;
  }
  
  public boolean load(@NonNull EmoteCosmetic cosmetic, @NonNull EmoteCosmeticClient clientCosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (clientCosmetic == null)
      throw new NullPointerException("clientCosmetic is marked non-null but is null"); 
    EmoteCosmetic.EmoteCosmeticData data = cosmetic.getData();
    LindwormModel emote = loadCosmetic(cosmetic.getId(), getModel(), data.getAnimation(), null, fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.animation.EmoteCosmeticAnimatable::new);
    LindwormModel effect = data.getEffect().exists() ? loadCosmetic(cosmetic.getId(), data.getEffect(), this.resourceBuilder, fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.animation.EmoteCosmeticAnimatable::new) : null;
    if (emote == null || !emote.isAnimated())
      return false; 
    clientCosmetic.load(emote, effect);
    return true;
  }
  
  public String getModel() {
    if (this.model == null) {
      ResourceLocation resourceLocation = new ResourceLocation("palashop", "models/cosmetic/emote/model.json");
      try {
        IResource resource = Minecraft.func_71410_x().func_110442_L().func_110536_a(resourceLocation);
        InputStream inputStream = resource.func_110527_b();
        this.model = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        if (this.model == null)
          throw new RuntimeException("Failed to load model: " + resourceLocation); 
      } catch (IOException e) {
        throw new RuntimeException("Failed to find model: " + resourceLocation, e);
      } 
    } 
    return this.model;
  }
  
  public static EmoteCosmeticClientLoader getInstance() {
    if (instance == null)
      return new EmoteCosmeticClientLoader(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\loader\EmoteCosmeticClientLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */