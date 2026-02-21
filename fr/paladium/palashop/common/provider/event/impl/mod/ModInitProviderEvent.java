package fr.paladium.palashop.common.provider.event.impl.mod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class ModInitProviderEvent extends ProviderEvent<FMLInitializationEvent> {
  private ModInitProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull FMLInitializationEvent event) {
    super(phase, event);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
  }
  
  @NonNull
  public static ModInitProviderEvent pre(@NonNull FMLInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModInitProviderEvent(ProviderEvent.Phase.PRE, event);
  }
  
  @NonNull
  public static ModInitProviderEvent post(@NonNull FMLInitializationEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    return new ModInitProviderEvent(ProviderEvent.Phase.POST, event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\impl\mod\ModInitProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */