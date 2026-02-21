package fr.paladium.palashop.server.shop.condition;

import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.function.Supplier;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AShopCondition {
  private final String[] keywords;
  
  private boolean throwException;
  
  public String[] getKeywords() {
    return this.keywords;
  }
  
  public boolean isThrowException() {
    return this.throwException;
  }
  
  public AShopCondition(@NonNull String... keywords) {
    if (keywords == null)
      throw new NullPointerException("keywords is marked non-null but is null"); 
    if (keywords.length == 0)
      throw new IllegalArgumentException("The keywords cannot be empty."); 
    this.keywords = keywords;
    for (int i = 0; i < keywords.length; i++) {
      this.keywords[i] = keywords[i].toLowerCase();
      if (this.keywords[i].isEmpty())
        throw new IllegalArgumentException("The keyword cannot be empty."); 
      if (!this.keywords[i].startsWith("%") && !this.keywords[i].endsWith("%"))
        this.keywords[i] = "%" + this.keywords[i] + "%"; 
    } 
  }
  
  public final ShopConditionException parse(ShopUser user, EntityPlayer player, String value) throws ShopConditionException {
    if (this.keywords == null || this.keywords.length == 0)
      throw new IllegalStateException("The condition " + getClass().getSimpleName() + " is not initialized."); 
    if (user == null || player == null || value == null || value.isEmpty())
      return null; 
    String[] split = value.split(" ");
    if (split.length != 3)
      throw new IllegalArgumentException("Invalid condition format: " + value); 
    String keyword = split[0];
    boolean match = false;
    for (String key : this.keywords) {
      if (key.equalsIgnoreCase(keyword)) {
        match = true;
        break;
      } 
    } 
    if (!match)
      return null; 
    Operator operator = Operator.parse(split[1]);
    if (operator == null)
      throw new IllegalArgumentException("Invalid operator: " + split[1]); 
    String condition = split[2];
    try {
      validate(user, player, operator, condition);
    } catch (ShopConditionException e) {
      if (this.throwException)
        throw e; 
      return e;
    } 
    return null;
  }
  
  @NonNull
  public final AShopCondition throwException(boolean throwException) {
    this.throwException = throwException;
    return this;
  }
  
  protected final ShopConditionException except(@NonNull Supplier<Boolean> condition, @NonNull ShopConditionException exception) throws ShopConditionException {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    if (exception == null)
      throw new NullPointerException("exception is marked non-null but is null"); 
    if (!((Boolean)condition.get()).booleanValue())
      throw exception; 
    return null;
  }
  
  protected abstract void validate(@NonNull ShopUser paramShopUser, @NonNull EntityPlayer paramEntityPlayer, @NonNull Operator paramOperator, @NonNull String paramString);
  
  public enum Operator {
    EQUALS, NOT_EQUALS, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUALS, LESS_THAN_OR_EQUALS, CONTAINS, NOT_CONTAINS, STARTS_WITH, ENDS_WITH, MATCHES;
    
    public void condition(@NonNull Operator operator, @NonNull Runnable consumer) {
      if (operator == null)
        throw new NullPointerException("operator is marked non-null but is null"); 
      if (consumer == null)
        throw new NullPointerException("consumer is marked non-null but is null"); 
      if (this == operator)
        consumer.run(); 
    }
    
    public static Operator parse(@NonNull String operator) {
      if (operator == null)
        throw new NullPointerException("operator is marked non-null but is null"); 
      if ("==".equalsIgnoreCase(operator) || "equals".equalsIgnoreCase(operator))
        return EQUALS; 
      if ("!=".equalsIgnoreCase(operator) || "not_equals".equalsIgnoreCase(operator))
        return NOT_EQUALS; 
      if (">".equalsIgnoreCase(operator) || "greater_than".equalsIgnoreCase(operator))
        return GREATER_THAN; 
      if ("<".equalsIgnoreCase(operator) || "less_than".equalsIgnoreCase(operator))
        return LESS_THAN; 
      if (">=".equalsIgnoreCase(operator) || "greater_than_or_equals".equalsIgnoreCase(operator))
        return GREATER_THAN_OR_EQUALS; 
      if ("<=".equalsIgnoreCase(operator) || "less_than_or_equals".equalsIgnoreCase(operator))
        return LESS_THAN_OR_EQUALS; 
      if ("contains".equalsIgnoreCase(operator))
        return CONTAINS; 
      if ("not_contains".equalsIgnoreCase(operator))
        return NOT_CONTAINS; 
      if ("starts_with".equalsIgnoreCase(operator))
        return STARTS_WITH; 
      if ("ends_with".equalsIgnoreCase(operator))
        return ENDS_WITH; 
      if ("matches".equalsIgnoreCase(operator))
        return MATCHES; 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\AShopCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */