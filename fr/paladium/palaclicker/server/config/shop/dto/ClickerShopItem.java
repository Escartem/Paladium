package fr.paladium.palaclicker.server.config.shop.dto;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class ClickerShopItem {
  private final String id;
  
  private final ClickerShopType type;
  
  private final String item;
  
  private final int price;
  
  private final int quantity;
  
  private final int weight;
  
  private final Map<String, Integer> requirement;
  
  private final Map<String, Integer> forbidden;
  
  private transient ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ClickerShopItem(String id, ClickerShopType type, String item, int price, int quantity, int weight, Map<String, Integer> requirement, Map<String, Integer> forbidden) {
    this.id = id;
    this.type = type;
    this.item = item;
    this.price = price;
    this.quantity = quantity;
    this.weight = weight;
    this.requirement = requirement;
    this.forbidden = forbidden;
  }
  
  public String getId() {
    return this.id;
  }
  
  public ClickerShopType getType() {
    return this.type;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public int getPrice() {
    return this.price;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public Map<String, Integer> getRequirement() {
    return this.requirement;
  }
  
  public Map<String, Integer> getForbidden() {
    return this.forbidden;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public double getPrice(int count) {
    return this.price + (this.price * count) / 3.0D;
  }
  
  public void make() {
    this.itemStack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(this.item.getBytes()), StandardCharsets.UTF_8));
  }
  
  public int getRequirement(String building) {
    return (this.requirement == null) ? 0 : ((Integer)this.requirement.getOrDefault(building, Integer.valueOf(0))).intValue();
  }
  
  public boolean hasRequirement(String building, int level) {
    return (level >= getRequirement(building));
  }
  
  public int getForbidden(String building) {
    return (this.forbidden == null) ? 0 : ((Integer)this.forbidden.getOrDefault(building, Integer.valueOf(0))).intValue();
  }
  
  public boolean isForbidden(String building, int level) {
    return (this.forbidden != null && this.forbidden.containsKey(building) && level >= getForbidden(building));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\config\shop\dto\ClickerShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */