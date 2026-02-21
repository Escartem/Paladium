package fr.paladium.palashop.provider.cosmetic.client.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmeticData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import lombok.NonNull;

public abstract class CosmeticClientCache<T extends ICosmeticClient> {
  private static final List<CosmeticClientCache<?>> INSTANCES = new ArrayList<>();
  
  private static final Cache<String, Consumer<ICosmetic>> CALLBACK_MAP = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private final Cache<String, T> cache = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  public Cache<String, T> getCache() {
    return this.cache;
  }
  
  public CosmeticClientCache() {
    INSTANCES.add(this);
  }
  
  @NonNull
  public List<T> getAll() {
    return new ArrayList<>(this.cache.asMap().values());
  }
  
  @NonNull
  public T get(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    AtomicReference<T> model = new AtomicReference<>((T)this.cache.getIfPresent(id));
    if (model.get() != null)
      return model.get(); 
    model.set(build(id));
    this.cache.put(id, model.get());
    CALLBACK_MAP.put(id, data -> {
          if (data == null) {
            this.cache.invalidate(id);
            return;
          } 
          onReceive((T)model.get(), data);
        });
    (new BBPacketGetCosmeticData(((ICosmeticClient)model.get()).getFactory().getId(), id)).send();
    return model.get();
  }
  
  public static void executeCallback(@NonNull String id, ICosmetic cosmetic) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    Consumer<ICosmetic> callback = (Consumer<ICosmetic>)CALLBACK_MAP.getIfPresent(id);
    if (callback != null) {
      callback.accept(cosmetic);
      CALLBACK_MAP.invalidate(id);
    } 
  }
  
  @ClientAction
  @NonNull
  public static ClientActionResult<Void> invalidate(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return ClientActionHook.useClient(() -> INSTANCES.forEach(()), new Object[] { id });
  }
  
  @ClientAction
  @NonNull
  public static ClientActionResult<Void> invalidateAll() {
    return ClientActionHook.useClient(() -> INSTANCES.forEach(()), new Object[0]);
  }
  
  public abstract T build(@NonNull String paramString);
  
  public abstract void onReceive(@NonNull T paramT, @NonNull ICosmetic paramICosmetic);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\utils\cache\CosmeticClientCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */