package fr.paladium.palashop.common.provider.event.impl.mod;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class ModPostInitProviderEvent extends ProviderEvent<FMLPostInitializationEvent> {
  private ModPostInitProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull FMLPostInitializationEvent event) {
    super(phase, event);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
  }
  
  @NonNull
  public static ModPostInitProviderEvent pre(@NonNull FMLPostInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModPostInitProviderEvent(ProviderEvent.Phase.PRE, event);
  }
  
  @NonNull
  public static ModPostInitProviderEvent post(@NonNull FMLPostInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModPostInitProviderEvent(ProviderEvent.Phase.POST, event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\impl\mod\ModPostInitProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */