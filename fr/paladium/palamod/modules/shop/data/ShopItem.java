package fr.paladium.palamod.modules.shop.data;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShopItem {
  private String item;
  
  private ItemCategory category;
  
  private boolean canSell;
  
  private boolean canBuy;
  
  private double sellPrice;
  
  private double buyPrice;
  
  public ShopItem(String item, ItemCategory category, boolean canSell, boolean canBuy, double sellPrice, double buyPrice) {
    this.item = item;
    this.category = category;
    this.canSell = canSell;
    this.canBuy = canBuy;
    this.sellPrice = sellPrice;
    this.buyPrice = buyPrice;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public ItemCategory getCategory() {
    return this.category;
  }
  
  public boolean isCanSell() {
    return this.canSell;
  }
  
  public boolean isCanBuy() {
    return this.canBuy;
  }
  
  public double getSellPrice() {
    return this.sellPrice;
  }
  
  public double getBuyPrice() {
    return this.buyPrice;
  }
  
  public ItemStack toItemStack() {
    try {
      String mod = this.item.toLowerCase().split(":")[0];
      String itemName = this.item.toLowerCase().split(":")[1].split("/")[0];
      String metadata = this.item.toLowerCase().split("/")[1];
      int mtdt = Integer.parseInt(metadata);
      Item item = GameRegistry.findItem(mod, itemName);
      if (item == null) {
        System.err.println("[ShopItem] Item not found for identifier: " + this.item);
        return null;
      } 
      return new ItemStack(item, 1, mtdt);
    } catch (Exception exception) {
      try {
        ItemStack item = ItemStackSerializer.read64(this.item);
        if (item == null) {
          System.err.println("[ShopItem] Item not found for identifier: " + this.item);
          return null;
        } 
        return item.func_77946_l();
      } catch (Exception e) {
        System.err.println("[ShopItem] Failed to parse item from identifier: " + this.item);
        e.printStackTrace();
        return null;
      } 
    } 
  }
  
  public static ShopItem parseFromBuffer(ByteBuf buffer) {
    String itemName = ByteBufUtils.readUTF8String(buffer);
    ItemCategory category = ItemCategory.fromCategory(ByteBufUtils.readUTF8String(buffer));
    boolean canSell = buffer.readBoolean();
    boolean canBuy = buffer.readBoolean();
    double sellPrice = buffer.readDouble();
    double buyPrice = buffer.readDouble();
    return new ShopItem(itemName, category, canSell, canBuy, sellPrice, buyPrice);
  }
  
  public void writeToBuffer(ByteBuf buffer) {
    ByteBufUtils.writeUTF8String(buffer, this.item);
    ByteBufUtils.writeUTF8String(buffer, this.category.name());
    buffer.writeBoolean(this.canSell);
    buffer.writeBoolean(this.canBuy);
    buffer.writeDouble(this.sellPrice);
    buffer.writeDouble(this.buyPrice);
  }
  
  public boolean canSell() {
    return (this.canSell && this.sellPrice > 0.0D);
  }
  
  public boolean canBuy() {
    return (this.canBuy && this.buyPrice > 0.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\data\ShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */