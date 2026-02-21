package fr.paladium.palashop.server.shop.dto.offer;

import lombok.NonNull;

public enum ShopOfferType {
  HORIZONTAL, DEFAULT;
  
  public boolean equals(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return name().equalsIgnoreCase(name);
  }
  
  public static boolean is(@NonNull String name, @NonNull ShopOfferType value) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    return value.equals(name);
  }
  
  public static ShopOfferType from(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    for (ShopOfferType type : values()) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\offer\ShopOffer$ShopOfferType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */