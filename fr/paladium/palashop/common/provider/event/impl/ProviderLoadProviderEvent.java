package fr.paladium.palashop.common.provider.event.impl;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class ProviderLoadProviderEvent extends ProviderEvent<Void> {
  private ProviderLoadProviderEvent(@NonNull ProviderEvent.Phase phase) {
    super(phase);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
  }
  
  @NonNull
  public static ProviderLoadProviderEvent pre() {
    return new ProviderLoadProviderEvent(ProviderEvent.Phase.PRE);
  }
  
  @NonNull
  public static ProviderLoadProviderEvent post() {
    return new ProviderLoadProviderEvent(ProviderEvent.Phase.POST);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\impl\ProviderLoadProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */