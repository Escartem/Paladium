package fr.paladium.palaclicker.server.config.building.dto;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import net.minecraft.item.ItemStack;

public class ClickerBuilding {
  private final String id;
  
  private final String image;
  
  private final String item;
  
  private final List<String> categories;
  
  private final double price;
  
  private transient ItemStack itemStack;
  
  private int index;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public void setIndex(int index) {
    this.index = index;
  }
  
  public ClickerBuilding(String id, String image, String item, List<String> categories, double price) {
    this.id = id;
    this.image = image;
    this.item = item;
    this.categories = categories;
    this.price = price;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public List<String> getCategories() {
    return this.categories;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public void make() {
    if (this.item == null || this.item.isEmpty())
      return; 
    this.itemStack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(this.item.getBytes()), StandardCharsets.UTF_8));
  }
  
  public float getRPS() {
    return (float)(0.10000000149011612D * Math.pow(1.7999999523162842D, this.index));
  }
  
  public double getPrice(int count) {
    return this.price * Math.pow(1.100000023841858D, count);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ClickerBuilding other = (ClickerBuilding)obj;
    return Objects.equals(this.id, other.id);
  }
  
  public String getName() {
    return TTT.format("clicker.building." + this.id, new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\config\building\dto\ClickerBuilding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */