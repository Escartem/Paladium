package fr.paladium.palashop.provider.box.common.dto.shop;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class BoxShopItem extends ShopItem {
  private final String boxId;
  
  private final int quantity;
  
  public String toString() {
    return "BoxShopItem(boxId=" + getBoxId() + ", quantity=" + getQuantity() + ")";
  }
  
  public String getBoxId() {
    return this.boxId;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public BoxShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Float discount, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ShopItem.ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData, String boxId, int quantity) {
    super(providerInstance, parent, template, id, provider, name, thumbnail, description, rarity, price, discount, subscription, conditions, uniquePurchase, available, buyable, executionType, executions, dailyWeight, additionalData);
    this.boxId = boxId;
    this.quantity = quantity;
  }
  
  @NonNull
  public String getName() {
    if (getItemStack() != null && FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return getItemStack().func_82833_r() + " x" + (getItemStack()).field_77994_a; 
    return super.getName();
  }
  
  public ItemStack getItemStack() {
    Optional<BoxData> optionalBox = BoxManager.getBox(this.boxId);
    return optionalBox.isPresent() ? new ItemStack(((BoxData)optionalBox.get()).getItem(), this.quantity) : null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\shop\BoxShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */