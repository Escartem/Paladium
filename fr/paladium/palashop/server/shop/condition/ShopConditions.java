package fr.paladium.palashop.server.shop.condition;

import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.condition.impl.ShopItemsCondition;
import fr.paladium.palashop.server.shop.condition.impl.ShopPermissionCondition;
import fr.paladium.palashop.server.shop.condition.impl.ShopServerNameCondition;
import fr.paladium.palashop.server.shop.condition.impl.ShopServerTypeCondition;
import fr.paladium.palashop.server.shop.condition.impl.ShopSlotCondition;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopConditions {
  public static List<AShopCondition> getConditionList() {
    return conditionList;
  }
  
  private static final List<AShopCondition> conditionList = new ArrayList<>();
  
  static {
    register((Class)ShopSlotCondition.class);
    register((Class)ShopItemsCondition.class);
    register((Class)ShopPermissionCondition.class);
    register((Class)ShopServerTypeCondition.class);
    register((Class)ShopServerNameCondition.class);
  }
  
  public static void register(@NonNull Class<? extends AShopCondition> condition) {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    try {
      conditionList.add(condition.newInstance());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public static void register(@NonNull AShopCondition condition) {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    conditionList.add(condition);
  }
  
  public static ShopConditionException validate(ShopUser user, EntityPlayer player, IBuyable buyable) {
    return validate(user, player, buyable, true);
  }
  
  public static ShopConditionException validate(ShopUser user, EntityPlayer player, IBuyable buyable, boolean throwException) {
    if (buyable == null || buyable.getConditions() == null || (buyable.getConditions()).length == 0)
      return null; 
    for (String condition : buyable.getConditions()) {
      ShopConditionException exception = validate(user, player, condition, throwException);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
  
  public static ShopConditionException validate(ShopUser user, EntityPlayer player, String value) {
    return validate(user, player, value, true);
  }
  
  public static ShopConditionException validate(ShopUser user, EntityPlayer player, String value, boolean throwException) {
    if (player == null || value == null || value.isEmpty())
      return null; 
    for (AShopCondition condition : conditionList) {
      ShopConditionException exception = condition.throwException(throwException).parse(user, player, value);
      if (exception != null)
        return exception; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\ShopConditions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */