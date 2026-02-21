package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import java.util.regex.Pattern;
import lombok.NonNull;

public class KillMessageCosmetic extends Cosmetic<KillMessageCosmetic.KillCosmeticProperties> {
  public String toString() {
    return "KillMessageCosmetic()";
  }
  
  private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
  
  public KillMessageCosmetic(@NonNull String id, @NonNull KillCosmeticProperties properties) {
    super(id, properties);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)KillMessageCosmeticFactory.getInstance();
  }
  
  @NonNull
  public String getName() {
    return COLOR_PATTERN.matcher(getFormattedMessage("{killer}", "{victim}")).replaceAll("");
  }
  
  @NonNull
  public String getMessage() {
    return ((KillCosmeticProperties)getProperties()).getMessage();
  }
  
  @NonNull
  public String getFormattedMessage(@NonNull String killer, @NonNull String victim) {
    if (killer == null)
      throw new NullPointerException("killer is marked non-null but is null"); 
    if (victim == null)
      throw new NullPointerException("victim is marked non-null but is null"); 
    return getMessage().replace("{killer}", killer).replace("%killer%", killer).replace("{victim}", victim).replace("%victim%", victim);
  }
  
  public static class KillCosmeticProperties extends CosmeticProperties {
    private String message;
    
    public void setMessage(String message) {
      this.message = message;
    }
    
    public String toString() {
      return "KillMessageCosmetic.KillCosmeticProperties(message=" + getMessage() + ")";
    }
    
    public KillCosmeticProperties() {}
    
    public KillCosmeticProperties(String message) {
      this.message = message;
    }
    
    public String getMessage() {
      return this.message;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\server\dto\KillMessageCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */