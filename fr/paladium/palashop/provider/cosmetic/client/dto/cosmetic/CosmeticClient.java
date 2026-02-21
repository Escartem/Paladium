package fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import lombok.NonNull;

public abstract class CosmeticClient<T extends CosmeticProperties> extends Cosmetic<T> implements ICosmeticClient {
  private final Queue<Consumer<ICosmeticClient>> receivedCallbackList = new ConcurrentLinkedQueue<>();
  
  public Queue<Consumer<ICosmeticClient>> getReceivedCallbackList() {
    return this.receivedCallbackList;
  }
  
  private final Queue<Consumer<ICosmeticClient>> loadedCallbackList = new ConcurrentLinkedQueue<>();
  
  private Resource thumbnail;
  
  private boolean received;
  
  private boolean loaded;
  
  public Queue<Consumer<ICosmeticClient>> getLoadedCallbackList() {
    return this.loadedCallbackList;
  }
  
  public Resource getThumbnail() {
    return this.thumbnail;
  }
  
  public boolean isReceived() {
    return this.received;
  }
  
  public boolean isLoaded() {
    return this.loaded;
  }
  
  public CosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  @NonNull
  public <S extends ICosmeticClient> S receive(@NonNull T properties) {
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    setProperties((CosmeticProperties)properties);
    this.received = true;
    while (!this.receivedCallbackList.isEmpty())
      ((Consumer<CosmeticClient<T>>)this.receivedCallbackList.poll()).accept(this); 
    return (S)this;
  }
  
  @NonNull
  public <S extends ICosmeticClient> S load(Resource thumbnail) {
    this.thumbnail = thumbnail;
    this.loaded = true;
    while (!this.loadedCallbackList.isEmpty())
      ((Consumer<CosmeticClient<T>>)this.loadedCallbackList.poll()).accept(this); 
    return (S)this;
  }
  
  @NonNull
  public <S extends fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement> S onReceived(@NonNull Consumer<S> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    if (isReceived()) {
      callback.accept((S)this);
    } else {
      this.receivedCallbackList.add(callback);
    } 
    return (S)this;
  }
  
  @NonNull
  public <S extends fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement> S onLoaded(@NonNull Consumer<S> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    if (isLoaded()) {
      callback.accept((S)this);
    } else {
      this.loadedCallbackList.add(callback);
    } 
    return (S)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\dto\cosmetic\CosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */