package fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.NonNull;

public class ShopCosmeticData {
  private final String factory;
  
  private final String cosmetic;
  
  private final Set<String> variants;
  
  private int quantity;
  
  public String toString() {
    return "ShopCosmeticData(factory=" + getFactory() + ", cosmetic=" + getCosmetic() + ", variants=" + getVariants() + ", quantity=" + getQuantity() + ")";
  }
  
  public String getFactory() {
    return this.factory;
  }
  
  public String getCosmetic() {
    return this.cosmetic;
  }
  
  public Set<String> getVariants() {
    return this.variants;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public ShopCosmeticData(@NonNull String factory, @NonNull String cosmetic) {
    if (factory == null)
      throw new NullPointerException("factory is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    this.factory = factory;
    this.cosmetic = cosmetic;
    this.variants = new HashSet<>();
    this.quantity = 1;
  }
  
  @NonNull
  public ShopCosmeticData addVariant(@NonNull String variant) {
    if (variant == null)
      throw new NullPointerException("variant is marked non-null but is null"); 
    this.variants.add(variant);
    return this;
  }
  
  @NonNull
  public ShopCosmeticData removeVariant(@NonNull String variant) {
    if (variant == null)
      throw new NullPointerException("variant is marked non-null but is null"); 
    this.variants.remove(variant);
    return this;
  }
  
  @NonNull
  public ShopCosmeticData setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
  
  @NonNull
  public ShopCosmeticData incrementQuantity() {
    return setQuantity(this.quantity + 1);
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof ShopCosmeticData))
      return false; 
    ShopCosmeticData that = (ShopCosmeticData)o;
    return (Objects.equals(this.factory, that.factory) && Objects.equals(this.cosmetic, that.cosmetic));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.factory, this.cosmetic });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\server\dto\cosmetic\ShopCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */