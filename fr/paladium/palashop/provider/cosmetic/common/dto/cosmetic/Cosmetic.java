package fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;

public abstract class Cosmetic<T extends CosmeticProperties> implements ICosmetic {
  private final String id;
  
  private T properties;
  
  private List<String> behaviors;
  
  public void setProperties(T properties) {
    this.properties = properties;
  }
  
  public void setBehaviors(List<String> behaviors) {
    this.behaviors = behaviors;
  }
  
  public Cosmetic(String id) {
    this.id = id;
  }
  
  public String getId() {
    return this.id;
  }
  
  public T getProperties() {
    return this.properties;
  }
  
  public Cosmetic(@NonNull String id, @NonNull T properties) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    this.id = id;
    this.properties = properties;
  }
  
  @NonNull
  public String getName() {
    if (this.properties == null)
      throw new IllegalStateException("Cosmetic properties are not set for " + this.id); 
    return TTT.format((this.properties.getName() == null) ? this.id : this.properties.getName(), new Object[0]);
  }
  
  @NonNull
  public ShopRarity getRarity() {
    if (this.properties == null)
      throw new IllegalStateException("Cosmetic properties are not set for " + this.id); 
    return this.properties.getRarity();
  }
  
  public List<String> getBehaviors() {
    if (this.properties == null)
      return new ArrayList<>(); 
    if (this.behaviors == null) {
      this.behaviors = new ArrayList<>();
      if (this.properties.getBehaviors() != null && (this.properties.getBehaviors()).length > 0)
        this.behaviors.addAll(Arrays.asList(this.properties.getBehaviors())); 
    } 
    return this.behaviors;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof ICosmetic))
      return false; 
    ICosmetic that = (ICosmetic)o;
    return Objects.equals(this.id, that.getId());
  }
  
  public int hashCode() {
    return Objects.hashCode(this.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\cosmetic\Cosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */