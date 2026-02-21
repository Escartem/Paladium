package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopPlayerNameCondition extends AShopCondition {
  public ShopPlayerNameCondition() {
    super(new String[] { "player_name" });
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
    List<String> playerNames = new ArrayList<>();
    if (condition.startsWith("[") && condition.endsWith("]")) {
      String[] split = condition.substring(1, condition.length() - 1).split(",");
      for (String s : split)
        playerNames.add(s.toLowerCase()); 
    } else {
      playerNames.add(condition.toLowerCase());
    } 
    String current = player.func_70005_c_().toLowerCase();
    AShopCondition.Operator.CONTAINS.condition(operator, () -> except((), new ShopPlayerNameConditionException(this, "Vous n'êtes pas autorisé.")));
    AShopCondition.Operator.NOT_CONTAINS.condition(operator, () -> except((), new ShopPlayerNameConditionException(this, "Vous n'êtes pas autorisé.")));
    AShopCondition.Operator.EQUALS.condition(operator, () -> except((), new ShopPlayerNameConditionException(this, "Vous n'êtes pas autorisé.")));
    AShopCondition.Operator.NOT_EQUALS.condition(operator, () -> except((), new ShopPlayerNameConditionException(this, "Vous n'êtes pas autorisé.")));
    AShopCondition.Operator.MATCHES.condition(operator, () -> except((), new ShopPlayerNameConditionException(this, "Vous n'êtes pas autorisé.")));
  }
  
  public class ShopPlayerNameConditionException extends ShopConditionException {
    public ShopPlayerNameConditionException(@NonNull AShopCondition condition, String message) {
      super(condition, message);
      if (condition == null)
        throw new NullPointerException("condition is marked non-null but is null"); 
      if (message == null)
        throw new NullPointerException("message is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopPlayerNameCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */