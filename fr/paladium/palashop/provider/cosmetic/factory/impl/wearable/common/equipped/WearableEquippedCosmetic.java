package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.equipped;

import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import java.util.Optional;
import lombok.NonNull;

public class WearableEquippedCosmetic extends CosmeticPlayer.EquippedCosmetic {
  public WearableEquippedCosmetic() {
    super(CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.UNIQUE, 3);
  }
  
  @NonNull
  public Optional<ICosmetic> get(@NonNull WearableCosmetic.WearableCosmeticType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return get(type.ordinal());
  }
  
  @NonNull
  public WearableEquippedCosmetic set(@NonNull WearableCosmetic.WearableCosmeticType type, @NonNull ICosmetic cosmetic) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    return (WearableEquippedCosmetic)set(type.ordinal(), cosmetic);
  }
  
  @NonNull
  public WearableEquippedCosmetic remove(@NonNull WearableCosmetic.WearableCosmeticType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return (WearableEquippedCosmetic)remove(type.ordinal());
  }
  
  public boolean isValid(int index, @NonNull ICosmetic cosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!cosmetic.getFactory().getId().equals(WearableCosmeticFactory.ID))
      return false; 
    WearableCosmetic.WearableCosmeticType type = null;
    if (cosmetic instanceof WearableCosmetic) {
      type = ((WearableCosmetic)cosmetic).getType();
    } else if (cosmetic instanceof WearableCosmeticClient) {
      type = ((WearableCosmeticClient)cosmetic).getType();
    } 
    if (type == null || index != type.ordinal())
      return false; 
    return super.isValid(index, cosmetic);
  }
  
  @NonNull
  public WearableEquippedCosmetic copy() {
    WearableEquippedCosmetic copy = new WearableEquippedCosmetic();
    for (int i = 0; i < (getCosmetics()).length; i++) {
      ICosmetic cosmetic = getCosmetics()[i];
      copy.getCosmetics()[i] = cosmetic;
    } 
    return copy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\common\equipped\WearableEquippedCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */