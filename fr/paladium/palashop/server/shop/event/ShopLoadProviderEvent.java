package fr.paladium.palashop.server.shop.event;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class ShopLoadProviderEvent extends ProviderEvent<Void> {
  private ShopLoadProviderEvent(@NonNull ProviderEvent.Phase phase) {
    super(phase);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
  }
  
  @NonNull
  public static ShopLoadProviderEvent pre() {
    return new ShopLoadProviderEvent(ProviderEvent.Phase.PRE);
  }
  
  @NonNull
  public static ShopLoadProviderEvent post() {
    return new ShopLoadProviderEvent(ProviderEvent.Phase.POST);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\event\ShopLoadProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */