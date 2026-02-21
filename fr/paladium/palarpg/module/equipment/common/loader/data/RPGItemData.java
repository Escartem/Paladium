package fr.paladium.palarpg.module.equipment.common.loader.data;

import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class RPGItemData {
  public void setId(String id) {
    this.id = id;
  }
  
  public void setType(RPGItemType type) {
    this.type = type;
  }
  
  public void setVersion(String version) {
    this.version = version;
  }
  
  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
  
  public void setRequiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  private final Map<String, Object> tags = new HashMap<>();
  
  public Map<String, Object> getTags() {
    return this.tags;
  }
  
  private final Map<String, String> translations = new HashMap<>();
  
  private transient String id;
  
  private RPGItemType type;
  
  private String version;
  
  private String itemName;
  
  public Map<String, String> getTranslations() {
    return this.translations;
  }
  
  public String getId() {
    return this.id;
  }
  
  public RPGItemType getType() {
    return this.type;
  }
  
  public String getVersion() {
    return this.version;
  }
  
  public String getItemName() {
    return this.itemName;
  }
  
  private int requiredLevel = 0;
  
  private String description;
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getTranslation(String lang) {
    if (this.translations != null && !this.translations.isEmpty())
      for (String key : this.translations.keySet()) {
        if (key.equalsIgnoreCase(lang))
          return this.translations.get(key); 
      }  
    return (this.itemName != null) ? this.itemName : (!this.translations.isEmpty() ? this.translations.values().iterator().next() : this.id);
  }
  
  public boolean hasDescription() {
    return (getDescription() != null && !getDescription().trim().isEmpty());
  }
  
  public final int hashCode() {
    return Objects.hash(new Object[] { this.id, this.type.name() });
  }
  
  public final boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    RPGItemData other = (RPGItemData)obj;
    return (Objects.equals(this.id, other.id) && this.type == other.type);
  }
  
  public abstract void onLoad(File paramFile);
  
  public abstract void createDefault(File paramFile);
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\RPGItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */