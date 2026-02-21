package fr.paladium.palashop.server.shop.condition.exception;

import fr.paladium.palashop.server.shop.condition.AShopCondition;
import lombok.NonNull;

public class ShopConditionException extends RuntimeException {
  private final AShopCondition condition;
  
  private final String rawMessage;
  
  public AShopCondition getCondition() {
    return this.condition;
  }
  
  public String getRawMessage() {
    return this.rawMessage;
  }
  
  public ShopConditionException(@NonNull AShopCondition condition, @NonNull String message) {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
    this.condition = condition;
    this.rawMessage = format(message);
  }
  
  @NonNull
  public String format(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    return text;
  }
  
  public String getMessage() {
    return this.rawMessage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\exception\ShopConditionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */