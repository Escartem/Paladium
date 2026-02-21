package fr.paladium.palashop.provider.cosmetic.factory;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;

public abstract class CosmeticFactory {
  private final CosmeticProvider provider;
  
  private final String id;
  
  private final Map<String, ShopElementRenderer<?>> shopRendererMap;
  
  public CosmeticProvider getProvider() {
    return this.provider;
  }
  
  public String getId() {
    return this.id;
  }
  
  public Map<String, ShopElementRenderer<?>> getShopRendererMap() {
    return this.shopRendererMap;
  }
  
  public CosmeticFactory(@NonNull CosmeticProvider provider, @NonNull String id) {
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (id.isEmpty())
      throw new IllegalArgumentException("id cannot be empty"); 
    this.provider = provider;
    this.id = id;
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
  public <S extends CosmeticFactory> S registerRenderer(@NonNull String type, @NonNull ShopElementRenderer<?> renderer) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (renderer == null)
      throw new NullPointerException("renderer is marked non-null but is null"); 
    this.shopRendererMap.put(type, renderer);
    return (S)this;
  }
  
  public CosmeticPlayer.EquippedCosmetic getEquippedCosmetic() {
    return null;
  }
  
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> List<T> getCosmetics();
  
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> Optional<T> getCosmetic(@NonNull String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\CosmeticFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */