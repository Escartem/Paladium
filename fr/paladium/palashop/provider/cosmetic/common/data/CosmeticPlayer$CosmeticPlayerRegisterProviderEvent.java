package fr.paladium.palashop.provider.cosmetic.common.data;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import lombok.NonNull;

public class CosmeticPlayerRegisterProviderEvent extends ProviderEvent<CosmeticPlayer> {
  private CosmeticPlayerRegisterProviderEvent(@NonNull ProviderEvent.Phase phase, @NonNull CosmeticPlayer player) {
    super(phase, player);
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
  }
  
  @NonNull
  public static CosmeticPlayerRegisterProviderEvent pre(@NonNull CosmeticPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new CosmeticPlayerRegisterProviderEvent(ProviderEvent.Phase.PRE, player);
  }
  
  @NonNull
  public static CosmeticPlayerRegisterProviderEvent post(@NonNull CosmeticPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new CosmeticPlayerRegisterProviderEvent(ProviderEvent.Phase.POST, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\data\CosmeticPlayer$CosmeticPlayerRegisterProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */