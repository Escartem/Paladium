package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic;

import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.List;
import lombok.NonNull;

public interface ICosmetic {
  @NonNull
  CosmeticFactory getFactory();
  
  @NonNull
  String getId();
  
  @NonNull
  String getName();
  
  @NonNull
  ShopRarity getRarity();
  
  List<String> getBehaviors();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\ICosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */