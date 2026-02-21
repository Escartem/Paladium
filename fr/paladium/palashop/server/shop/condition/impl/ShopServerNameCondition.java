package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopServerNameCondition extends AShopCondition {
  public ShopServerNameCondition() {
    super(new String[] { "server_name" });
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
    List<String> serverNames = new ArrayList<>();
    if (condition.startsWith("[") && condition.endsWith("]")) {
      String[] split = condition.substring(1, condition.length() - 1).split(",");
      for (String s : split)
        serverNames.add(s.toLowerCase()); 
    } else {
      serverNames.add(condition.toLowerCase());
    } 
    String current = ServerUtils.getServerName().toLowerCase();
    AShopCondition.Operator.CONTAINS.condition(operator, () -> except((), new ShopServerNameConditionException(this, "Vous n'êtes pas sur le bon serveur.")));
    AShopCondition.Operator.NOT_CONTAINS.condition(operator, () -> except((), new ShopServerNameConditionException(this, "Vous n'êtes pas sur le bon serveur.")));
    AShopCondition.Operator.EQUALS.condition(operator, () -> except((), new ShopServerNameConditionException(this, "Vous n'êtes pas sur le bon serveur.")));
    AShopCondition.Operator.NOT_EQUALS.condition(operator, () -> except((), new ShopServerNameConditionException(this, "Vous n'êtes pas sur le bon serveur.")));
    AShopCondition.Operator.MATCHES.condition(operator, () -> except((), new ShopServerNameConditionException(this, "Vous n'êtes pas sur le bon serveur.")));
  }
  
  public class ShopServerNameConditionException extends ShopConditionException {
    public ShopServerNameConditionException(@NonNull AShopCondition condition, String message) {
      super(condition, message);
      if (condition == null)
        throw new NullPointerException("condition is marked non-null but is null"); 
      if (message == null)
        throw new NullPointerException("message is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopServerNameCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */