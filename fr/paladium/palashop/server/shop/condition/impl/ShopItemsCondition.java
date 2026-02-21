package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.Set;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopItemsCondition extends AShopCondition {
  public ShopItemsCondition() {
    super(new String[] { "items" });
  }
  
  public void validate(@NonNull ShopUser user, @NonNull EntityPlayer player, @NonNull AShopCondition.Operator operator, @NonNull String condition) {
    if (user == null)
      throw new NullPointerException("user is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (operator == null)
      throw new NullPointerException("operator is marked non-null but is null"); 
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    Set<String> items = user.getOwnedItems();
    AShopCondition.Operator.CONTAINS.condition(operator, () -> except((), new ShopItemsConditionException(this, "Vous n'avez pas ce produit.")));
    AShopCondition.Operator.NOT_CONTAINS.condition(operator, () -> except((), new ShopItemsConditionException(this, "Vous avez déjà ce produit.")));
  }
  
  public class ShopItemsConditionException extends ShopConditionException {
    public ShopItemsConditionException(@NonNull AShopCondition condition, String message) {
      super(condition, message);
      if (condition == null)
        throw new NullPointerException("condition is marked non-null but is null"); 
      if (message == null)
        throw new NullPointerException("message is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopItemsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */