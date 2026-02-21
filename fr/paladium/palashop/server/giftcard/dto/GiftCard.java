package fr.paladium.palashop.server.giftcard.dto;

import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import java.util.Arrays;

public class GiftCard {
  private String code;
  
  private String itemId;
  
  private long usedQuantity;
  
  private long totalQuantity;
  
  private long creation;
  
  private long expiration;
  
  private String[] conditions;
  
  public String toString() {
    return "GiftCard(code=" + getCode() + ", itemId=" + getItemId() + ", usedQuantity=" + getUsedQuantity() + ", totalQuantity=" + getTotalQuantity() + ", creation=" + getCreation() + ", expiration=" + getExpiration() + ", conditions=" + Arrays.deepToString((Object[])getConditions()) + ")";
  }
  
  public GiftCard() {}
  
  public GiftCard(String code, String itemId, long usedQuantity, long totalQuantity, long creation, long expiration, String[] conditions) {
    this.code = code;
    this.itemId = itemId;
    this.usedQuantity = usedQuantity;
    this.totalQuantity = totalQuantity;
    this.creation = creation;
    this.expiration = expiration;
    this.conditions = conditions;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public String getItemId() {
    return this.itemId;
  }
  
  public long getUsedQuantity() {
    return this.usedQuantity;
  }
  
  public long getTotalQuantity() {
    return this.totalQuantity;
  }
  
  public long getCreation() {
    return this.creation;
  }
  
  public long getExpiration() {
    return this.expiration;
  }
  
  public String[] getConditions() {
    return this.conditions;
  }
  
  public boolean isValid() {
    return (!isExpired() && !isUsed());
  }
  
  public boolean isExpired() {
    return (this.expiration > 0L && UniversalTimeUtils.now() > this.expiration);
  }
  
  public boolean isUsed() {
    return (this.totalQuantity > 0L && this.usedQuantity >= this.totalQuantity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\giftcard\dto\GiftCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */