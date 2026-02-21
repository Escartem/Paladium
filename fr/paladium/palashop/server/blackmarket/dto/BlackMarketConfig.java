package fr.paladium.palashop.server.blackmarket.dto;

import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import java.util.Map;

public class BlackMarketConfig {
  private final String id;
  
  private final boolean active;
  
  private final long expiration;
  
  public String toString() {
    return "BlackMarketConfig(id=" + getId() + ", active=" + isActive() + ", expiration=" + getExpiration() + ", minDiscount=" + getMinDiscount() + ", maxDiscount=" + getMaxDiscount() + ", totalDiscount=" + getTotalDiscount() + ", probabilities=" + getProbabilities() + ")";
  }
  
  public BlackMarketConfig(String id, boolean active, long expiration, float minDiscount, float maxDiscount, float totalDiscount, Map<String, Integer> probabilities) {
    this.id = id;
    this.active = active;
    this.expiration = expiration;
    this.minDiscount = minDiscount;
    this.maxDiscount = maxDiscount;
    this.totalDiscount = totalDiscount;
    this.probabilities = probabilities;
  }
  
  public String getId() {
    return this.id;
  }
  
  public long getExpiration() {
    return this.expiration;
  }
  
  private float minDiscount = -1.0F;
  
  public float getMinDiscount() {
    return this.minDiscount;
  }
  
  private float maxDiscount = -1.0F;
  
  public float getMaxDiscount() {
    return this.maxDiscount;
  }
  
  private float totalDiscount = -1.0F;
  
  private final Map<String, Integer> probabilities;
  
  public float getTotalDiscount() {
    return this.totalDiscount;
  }
  
  public Map<String, Integer> getProbabilities() {
    return this.probabilities;
  }
  
  public boolean isActive() {
    return (this.id != null && !this.id.isEmpty() && this.active && this.expiration >= UniversalTimeUtils.now());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\blackmarket\dto\BlackMarketConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */