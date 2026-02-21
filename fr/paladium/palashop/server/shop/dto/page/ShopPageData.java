package fr.paladium.palashop.server.shop.dto.page;

import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public interface ShopPageData {
  void create(@NonNull ShopUser paramShopUser, @NonNull EntityPlayer paramEntityPlayer);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\page\ShopPageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */