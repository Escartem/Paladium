package fr.paladium.palashop.client.ui.kit.render;

import fr.paladium.palashop.common.provider.ProviderManager;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.NonNull;

public class ShopElementRendererNode extends Node {
  private String type;
  
  private String provider;
  
  private Object object;
  
  private transient ShopElementRenderer renderer;
  
  private transient Consumer<ShopElementRendererNode> orElseConsumer;
  
  public String getType() {
    return this.type;
  }
  
  public String getProvider() {
    return this.provider;
  }
  
  public Object getObject() {
    return this.object;
  }
  
  public ShopElementRenderer getRenderer() {
    return this.renderer;
  }
  
  public Consumer<ShopElementRendererNode> getOrElseConsumer() {
    return this.orElseConsumer;
  }
  
  protected ShopElementRendererNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static ShopElementRendererNode create(double x, double y, double width, double height) {
    return new ShopElementRendererNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    clearChildren();
    if (this.type == null || this.object == null)
      return; 
    this.renderer = null;
    if (this.provider != null) {
      Optional<IShopProvider<? extends IShopItem>> provider = ProviderManager.getProvider(this.provider);
      if (provider.isPresent())
        this.renderer = ((IShopProvider)provider.get()).getRenderer(this.type, this.object); 
    } 
    if (this.renderer == null && this.orElseConsumer != null) {
      this.orElseConsumer.accept(this);
    } else if (this.renderer != null && this.renderer.getElementType().isAssignableFrom(this.object.getClass()) && this.renderer.isValid(this.object)) {
      this.renderer.render(this.object, this);
    } 
  }
  
  @NonNull
  public final ShopElementRendererNode type(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.type = type;
    return this;
  }
  
  @NonNull
  public final ShopElementRendererNode object(@NonNull Object object) {
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    this.object = object;
    if (object instanceof IShopItem)
      this.provider = ((IShopItem)object).getProvider(); 
    return this;
  }
  
  @NonNull
  public final ShopElementRendererNode data(@NonNull String type, @NonNull Object object) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    type(type);
    object(object);
    return this;
  }
  
  @NonNull
  public final ShopElementRendererNode provider(@NonNull String provider) {
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    this.provider = provider;
    return this;
  }
  
  @NonNull
  public final ShopElementRendererNode orElse(@NonNull Consumer<ShopElementRendererNode> consumer) {
    if (consumer == null)
      throw new NullPointerException("consumer is marked non-null but is null"); 
    this.orElseConsumer = consumer;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\render\ShopElementRendererNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */