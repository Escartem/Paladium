package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.ServerType;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopServerTypeCondition extends AShopCondition {
  public ShopServerTypeCondition() {
    super(new String[] { "server_type" });
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
    List<ServerType> types = new ArrayList<>();
    if (condition.startsWith("[") && condition.endsWith("]")) {
      String[] split = condition.substring(1, condition.length() - 1).split(",");
      for (String s : split)
        types.add(ServerType.valueOf(s.toUpperCase())); 
    } else {
      types.add(ServerType.valueOf(condition.toUpperCase()));
    } 
    if (types.isEmpty())
      throw new ShopServerTypeConditionException(this, "Le type de serveur n'est pas valide."); 
    ServerType current = ServerUtils.getServerType();
    AShopCondition.Operator.CONTAINS.condition(operator, () -> except((), new ShopServerTypeConditionException(this, "Vous n'êtes pas sur le bon type de serveur.")));
    AShopCondition.Operator.EQUALS.condition(operator, () -> except((), new ShopServerTypeConditionException(this, "Vous n'êtes pas sur le bon type de serveur.")));
    AShopCondition.Operator.NOT_CONTAINS.condition(operator, () -> except((), new ShopServerTypeConditionException(this, "Vous n'êtes pas sur le bon type de serveur.")));
    AShopCondition.Operator.NOT_EQUALS.condition(operator, () -> except((), new ShopServerTypeConditionException(this, "Vous n'êtes pas sur le bon type de serveur.")));
  }
  
  public class ShopServerTypeConditionException extends ShopConditionException {
    public ShopServerTypeConditionException(@NonNull AShopCondition condition, String message) {
      super(condition, message);
      if (condition == null)
        throw new NullPointerException("condition is marked non-null but is null"); 
      if (message == null)
        throw new NullPointerException("message is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopServerTypeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */