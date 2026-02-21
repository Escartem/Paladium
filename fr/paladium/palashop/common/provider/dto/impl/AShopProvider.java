package fr.paladium.palashop.common.provider.dto.impl;

import com.google.gson.JsonElement;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import org.bson.Document;

public abstract class AShopProvider<T extends IShopItem> implements IShopProvider<T> {
  private final String id;
  
  private final Class<T> itemClass;
  
  private final int priority;
  
  private final Map<String, ShopElementRenderer<?>> shopRendererMap;
  
  public String getId() {
    return this.id;
  }
  
  public Class<T> getItemClass() {
    return this.itemClass;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public Map<String, ShopElementRenderer<?>> getShopRendererMap() {
    return this.shopRendererMap;
  }
  
  public AShopProvider() {
    ShopProvider data = getClass().<ShopProvider>getAnnotation(ShopProvider.class);
    if (data == null)
      throw new IllegalArgumentException("Missing ShopProvider annotation"); 
    if (data.id().isEmpty())
      throw new IllegalArgumentException("Missing id in ShopProvider annotation"); 
    this.id = data.id();
    this.itemClass = data.item();
    this.priority = data.priority();
    this.shopRendererMap = new HashMap<>();
  }
  
  public <S> ShopElementRenderer<S> getRenderer(@NonNull String type, @NonNull S object) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    ShopElementRenderer<S> renderer = (ShopElementRenderer)this.shopRendererMap.get(type);
    if (renderer == null || !renderer.getElementType().isAssignableFrom(object.getClass()) || !renderer.isValid(object))
      return null; 
    return renderer;
  }
  
  @NonNull
  public <S extends AShopProvider<T>> S registerRenderer(@NonNull String type, @NonNull ShopElementRenderer<?> renderer) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (renderer == null)
      throw new NullPointerException("renderer is marked non-null but is null"); 
    this.shopRendererMap.put(type, renderer);
    return (S)this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Boolean> hasData(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    PalaShopMod.getServer().getProviderAPI().has(this.id, uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Document> getData(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<Document> future = new CompletableFuture<>();
    PalaShopMod.getServer().getProviderAPI().get(this.id, uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Void> setData(@NonNull String uuid, @NonNull Document data) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    CompletableFuture<Void> future = new CompletableFuture<>();
    PalaShopMod.getServer().getProviderAPI().set(this.id, uuid, data).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Void> save(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<Void> future = new CompletableFuture<>();
    PalaShopMod.getServer().getProviderAPI().save(this.id, uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @NonNull
  public <S extends ShopProviderProxy> S createProxy(@NonNull String client, @NonNull String server) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    if (client == null)
      throw new NullPointerException("client is marked non-null but is null"); 
    if (server == null)
      throw new NullPointerException("server is marked non-null but is null"); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return (S)Class.forName(client).newInstance(); 
    return (S)Class.forName(server).newInstance();
  }
  
  @NonNull
  public Class<T> getItemClass(@NonNull JsonElement json) {
    if (json == null)
      throw new NullPointerException("json is marked non-null but is null"); 
    return this.itemClass;
  }
  
  public int compareTo(IShopProvider<?> o) {
    return Integer.compare(o.getPriority(), this.priority);
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof AShopProvider))
      return false; 
    AShopProvider<?> that = (AShopProvider)o;
    return Objects.equals(this.id, that.id);
  }
  
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\dto\impl\AShopProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */