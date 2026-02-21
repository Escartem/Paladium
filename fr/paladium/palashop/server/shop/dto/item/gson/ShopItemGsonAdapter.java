package fr.paladium.palashop.server.shop.dto.item.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palashop.common.provider.ProviderManager;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.lang.reflect.Type;
import java.util.Optional;

public class ShopItemGsonAdapter implements JsonSerializer<IShopItem>, JsonDeserializer<IShopItem> {
  public IShopItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (json.isJsonPrimitive() && FMLCommonHandler.instance().getSide() == Side.SERVER && ShopManager.getItems() != null) {
      String id = json.getAsString();
      IShopItem item = ShopManager.getItems().stream().filter(availableItem -> availableItem.getUniqueId().equals(id)).findFirst().orElse(null);
      if (item == null)
        throw new JsonParseException("Invalid shop item id: " + id); 
      return item;
    } 
    if (!json.isJsonObject())
      throw new JsonParseException("Invalid shop item json: " + json); 
    JsonObject object = json.getAsJsonObject();
    if (!object.has("provider") || object.get("provider").isJsonNull() || object.get("provider").getAsString() == null || object.get("provider").getAsString().isEmpty()) {
      IShopItem iShopItem = (IShopItem)context.deserialize(json, ProviderManager.getDefaultProvider().getItemClass(json));
      iShopItem.setProviderInstance(ProviderManager.getDefaultProvider());
      return iShopItem;
    } 
    Optional<IShopProvider<? extends IShopItem>> optionalShopProvider = ProviderManager.getProvider(object.get("provider").getAsString());
    if (!optionalShopProvider.isPresent()) {
      IShopItem iShopItem = (IShopItem)context.deserialize(json, ProviderManager.getDefaultProvider().getItemClass(json));
      return iShopItem;
    } 
    IShopProvider<? extends IShopItem> shopProvider = optionalShopProvider.get();
    IShopItem shopItem = (IShopItem)context.deserialize(json, shopProvider.getItemClass(json));
    shopItem.setProviderInstance(shopProvider);
    return shopItem;
  }
  
  public JsonElement serialize(IShopItem src, Type typeOfSrc, JsonSerializationContext context) {
    return context.serialize(src, src.getClass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\item\gson\ShopItemGsonAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */