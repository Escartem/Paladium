package fr.paladium.palarpg.module.dungeon.server.config.room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.List;
import lombok.NonNull;

public class DungeonRoomConfig {
  public String toString() {
    return "DungeonRoomConfig(weight=" + getWeight() + ", size=" + getSize() + ", behaviors=" + getBehaviors() + ", data=" + getData() + ", id=" + getId() + ", name=" + getName() + ", type=" + getType() + ", schematic=" + getSchematic() + ")";
  }
  
  public DungeonRoomConfig(JsonElement weight, DungeonRoomSizeConfig size, List<String> behaviors, JsonObject data, String id, String name, DungeonRoomType type, File schematic) {
    this.weight = weight;
    this.size = size;
    this.behaviors = behaviors;
    this.data = data;
    this.id = id;
    this.name = name;
    this.type = type;
    this.schematic = schematic;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private final JsonElement weight;
  
  private final DungeonRoomSizeConfig size;
  
  private final List<String> behaviors;
  
  private final JsonObject data;
  
  private String id;
  
  private String name;
  
  private DungeonRoomType type;
  
  private transient File schematic;
  
  public JsonElement getWeight() {
    return this.weight;
  }
  
  public DungeonRoomSizeConfig getSize() {
    return this.size;
  }
  
  public List<String> getBehaviors() {
    return this.behaviors;
  }
  
  public JsonObject getData() {
    return this.data;
  }
  
  public String getId() {
    return this.id;
  }
  
  public DungeonRoomType getType() {
    return this.type;
  }
  
  public File getSchematic() {
    return this.schematic;
  }
  
  @NonNull
  public DungeonRoomConfig build(@NonNull String id, @NonNull DungeonRoomType type, @NonNull File schematic) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (schematic == null)
      throw new NullPointerException("schematic is marked non-null but is null"); 
    this.id = id;
    this.type = type;
    this.schematic = schematic;
    return this;
  }
  
  public boolean hasData(@NonNull String key) {
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    return this.data.has(key);
  }
  
  public <T> T getData(@NonNull String key, @NonNull Class<T> clazz) {
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (this.data == null || !this.data.has(key))
      return null; 
    return (T)GSON.fromJson(this.data.get(key), clazz);
  }
  
  @NonNull
  public String getName() {
    if (this.name != null && !this.name.isEmpty())
      return this.name; 
    return this.type.getTitle();
  }
  
  public class DungeonRoomSizeConfig {
    private final int x;
    
    private final int z;
    
    public String toString() {
      return "DungeonRoomConfig.DungeonRoomSizeConfig(x=" + getX() + ", z=" + getZ() + ")";
    }
    
    public DungeonRoomSizeConfig(int x, int z) {
      this.x = x;
      this.z = z;
    }
    
    public int getX() {
      return this.x;
    }
    
    public int getZ() {
      return this.z;
    }
  }
  
  public enum DungeonRoomType {
    CLASSIC("salle combat", "Tuez tous les monstres pour compléter la salle et récupérer du butin."),
    PUZZLE("salle énigme", "Résolvez l'énigme pour compléter la salle et récupérer du butin."),
    BOOST("salle bonus", "Profitez de cette salle pour récupérer des bonus avant de continuer l'aventure."),
    MERCHANT("salle commerce", "Profitez d'échanges avantageux avec le marchand avant de continuer l'aventure."),
    MINIBOSS("miniboss", "Vainquez le miniboss pour compléter la salle et récupérer du butin."),
    BOSS("boss", "Vainquez le boss pour compléter la salle et récupérer du butin.");
    
    DungeonRoomType(String title, String description) {
      this.title = title;
      this.description = description;
    }
    
    private final String title;
    
    private final String description;
    
    public String getTitle() {
      return this.title;
    }
    
    public String getDescription() {
      return this.description;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\room\DungeonRoomConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */