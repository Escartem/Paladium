package fr.paladium.palashop.provider;

import fr.paladium.palashop.common.provider.dto.DefaultShopProvider;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.impl.AShopProvider;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;

@DefaultShopProvider
@ShopProvider(id = "default", item = ShopItem.class)
public class DefaultProvider extends AShopProvider<ShopItem> {}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\DefaultProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */