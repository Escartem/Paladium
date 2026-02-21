package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import lombok.NonNull;

public class ShopPlayerUUIDConditionException extends ShopConditionException {
  public ShopPlayerUUIDConditionException(@NonNull AShopCondition condition, String message) {
    super(condition, message);
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopPlayerUUIDCondition$ShopPlayerUUIDConditionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */