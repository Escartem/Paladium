package fr.paladium.palashop.server.shop.dto;

import fr.paladium.palaforgeutils.lib.task.DurationConverter;

public interface IBuyable {
  String getUniqueId();
  
  String getName();
  
  String getDescription();
  
  String getThumbnail();
  
  ShopRarity getRarity();
  
  Long getPrice();
  
  Long getSubscription();
  
  boolean isSubscription();
  
  String[] getConditions();
  
  Boolean isUniquePurchase();
  
  Boolean isAvailable();
  
  default String getSubscriptionFormatted() {
    String duration = DurationConverter.fromMillisToString(getSubscription().longValue());
    String[] parts = duration.split(" ");
    if (parts.length > 2)
      duration = parts[0] + " " + parts[1]; 
    if (duration.startsWith("1 "))
      duration = duration.substring(2); 
    return duration;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\IBuyable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */