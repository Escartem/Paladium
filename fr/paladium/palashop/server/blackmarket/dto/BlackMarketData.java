package fr.paladium.palashop.server.blackmarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;

public class BlackMarketData {
  private final String blackMarketId;
  
  private final long expiration;
  
  private final Map<String, Integer> items;
  
  public String toString() {
    return "BlackMarketData(blackMarketId=" + getBlackMarketId() + ", expiration=" + getExpiration() + ", items=" + getItems() + ")";
  }
  
  public BlackMarketData(String blackMarketId, long expiration, Map<String, Integer> items) {
    this.blackMarketId = blackMarketId;
    this.expiration = expiration;
    this.items = items;
  }
  
  public String getBlackMarketId() {
    return this.blackMarketId;
  }
  
  public long getExpiration() {
    return this.expiration;
  }
  
  public Map<String, Integer> getItems() {
    return this.items;
  }
  
  @NonNull
  public static BlackMarketData from(@NonNull Document document) {
    if (document == null)
      throw new NullPointerException("document is marked non-null but is null"); 
    Map<String, Object> documentMap = (Map<String, Object>)document.get("items", Map.class);
    Map<String, Integer> items = (Map<String, Integer>)documentMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> Integer.valueOf(Double.valueOf(entry.getValue().toString()).intValue())));
    return new BlackMarketData(document.getString("blackMarketId"), document.getDouble("expiration").longValue(), items);
  }
  
  @JsonIgnore
  @BsonIgnore
  public boolean isExpired() {
    return (this.expiration < UniversalTimeUtils.now());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\blackmarket\dto\BlackMarketData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */