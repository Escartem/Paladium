package fr.paladium.palashop.server.shop.condition.impl;

import fr.paladium.palashop.server.shop.condition.AShopCondition;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShopSlotCondition extends AShopCondition {
  public ShopSlotCondition() {
    super(new String[] { "slot" });
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
    int space = getInventorySpace(player);
    int needed = Double.valueOf(condition).intValue();
    AShopCondition.Operator.EQUALS.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
    AShopCondition.Operator.NOT_EQUALS.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
    AShopCondition.Operator.GREATER_THAN.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
    AShopCondition.Operator.GREATER_THAN_OR_EQUALS.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
    AShopCondition.Operator.LESS_THAN.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
    AShopCondition.Operator.LESS_THAN_OR_EQUALS.condition(operator, () -> except((), new ShopSlotConditionException(this, "Vous n'avez pas assez de place dans votre inventaire.")));
  }
  
  private int getInventorySpace(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    int count = 0;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      ItemStack stack = player.field_71071_by.field_70462_a[i];
      if (stack == null || stack.func_77973_b() == Item.func_150898_a(Blocks.field_150350_a))
        count++; 
    } 
    return count;
  }
  
  public class ShopSlotConditionException extends ShopConditionException {
    public ShopSlotConditionException(@NonNull AShopCondition condition, String message) {
      super(condition, message);
      if (condition == null)
        throw new NullPointerException("condition is marked non-null but is null"); 
      if (message == null)
        throw new NullPointerException("message is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\condition\impl\ShopSlotCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */