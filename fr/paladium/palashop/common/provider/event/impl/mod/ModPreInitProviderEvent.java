package fr.paladium.palashop.common.provider.event.impl.mod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class ModPreInitProviderEvent extends ProviderEvent<FMLPreInitializationEvent> {
  private ModPreInitProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull FMLPreInitializationEvent event) {
    super(phase, event);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
  }
  
  @NonNull
  public static ModPreInitProviderEvent pre(@NonNull FMLPreInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModPreInitProviderEvent(ProviderEvent.Phase.PRE, event);
  }
  
  @NonNull
  public static ModPreInitProviderEvent post(@NonNull FMLPreInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModPreInitProviderEvent(ProviderEvent.Phase.POST, event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\impl\mod\ModPreInitProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */