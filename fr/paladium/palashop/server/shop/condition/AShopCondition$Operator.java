package fr.paladium.palashop.server.shop.condition;

import lombok.NonNull;

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


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\AShopCondition$Operator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */