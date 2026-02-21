package fr.paladium.palashop.common.provider.dto;

import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.NonNull;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShopProvider {
  @NonNull
  String id();
  
  @NonNull
  Class<? extends IShopItem> item();
  
  int priority() default 0;
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\dto\ShopProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */