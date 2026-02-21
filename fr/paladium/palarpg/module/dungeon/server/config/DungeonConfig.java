package fr.paladium.palarpg.module.dungeon.server.config;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class DungeonConfig implements IndexedElement {
  private final int index;
  
  private final String name;
  
  private final String tag;
  
  private final String description;
  
  private final String iconWhite;
  
  private final String iconColor;
  
  private final String banner;
  
  @SerializedName("item")
  private final String rawItem;
  
  @SerializedName("color")
  private final String rawColor;
  
  @SerializedName("shadowColor")
  private final String rawShadowColor;
  
  private String id;
  
  private List<DungeonRoomConfig> rooms;
  
  private List<DungeonLevelConfig> levels;
  
  private DungeonLevelConfig infiniteLevel;
  
  private transient ItemStack item;
  
  private transient Color color;
  
  private transient Color shadowColor;
  
  public String toString() {
    return "DungeonConfig(index=" + getIndex() + ", name=" + getName() + ", tag=" + getTag() + ", description=" + getDescription() + ", iconWhite=" + getIconWhite() + ", iconColor=" + getIconColor() + ", banner=" + getBanner() + ", rawItem=" + getRawItem() + ", rawColor=" + getRawColor() + ", rawShadowColor=" + getRawShadowColor() + ", id=" + getId() + ", rooms=" + getRooms() + ", levels=" + getLevels() + ", infiniteLevel=" + getInfiniteLevel() + ", item=" + getItem() + ", color=" + getColor() + ", shadowColor=" + getShadowColor() + ")";
  }
  
  public DungeonConfig(int index, String name, String tag, String description, String iconWhite, String iconColor, String banner, String rawItem, String rawColor, String rawShadowColor, String id, List<DungeonRoomConfig> rooms, List<DungeonLevelConfig> levels, DungeonLevelConfig infiniteLevel, ItemStack item, Color color, Color shadowColor) {
    this.index = index;
    this.name = name;
    this.tag = tag;
    this.description = description;
    this.iconWhite = iconWhite;
    this.iconColor = iconColor;
    this.banner = banner;
    this.rawItem = rawItem;
    this.rawColor = rawColor;
    this.rawShadowColor = rawShadowColor;
    this.id = id;
    this.rooms = rooms;
    this.levels = levels;
    this.infiniteLevel = infiniteLevel;
    this.item = item;
    this.color = color;
    this.shadowColor = shadowColor;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getIconWhite() {
    return this.iconWhite;
  }
  
  public String getIconColor() {
    return this.iconColor;
  }
  
  public String getBanner() {
    return this.banner;
  }
  
  public String getRawItem() {
    return this.rawItem;
  }
  
  public String getRawColor() {
    return this.rawColor;
  }
  
  public String getRawShadowColor() {
    return this.rawShadowColor;
  }
  
  public String getId() {
    return this.id;
  }
  
  public List<DungeonRoomConfig> getRooms() {
    return this.rooms;
  }
  
  public List<DungeonLevelConfig> getLevels() {
    return this.levels;
  }
  
  public DungeonLevelConfig getInfiniteLevel() {
    return this.infiniteLevel;
  }
  
  @NonNull
  public DungeonConfig build(@NonNull String id, @NonNull List<DungeonRoomConfig> rooms, @NonNull List<DungeonLevelConfig> levels, DungeonLevelConfig infiniteLevel) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (rooms == null)
      throw new NullPointerException("rooms is marked non-null but is null"); 
    if (levels == null)
      throw new NullPointerException("levels is marked non-null but is null"); 
    this.id = id;
    this.rooms = rooms;
    this.levels = (List<DungeonLevelConfig>)levels.stream().sorted(Comparator.comparing(DungeonLevelConfig::getLevel)).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
    this.infiniteLevel = infiniteLevel;
    return this;
  }
  
  @NonNull
  public Optional<DungeonRoomConfig> getRoom(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.rooms.stream().filter(room -> room.getId().equalsIgnoreCase(id)).findFirst();
  }
  
  @NonNull
  public List<DungeonRoomConfig> getRooms(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return (List<DungeonRoomConfig>)this.rooms.stream().filter(room -> (room.getType() == type)).collect(Collectors.toList());
  }
  
  @NonNull
  public Optional<DungeonLevelConfig> getLevel(int level) {
    return this.levels.stream().filter(lvl -> (lvl.getLevel() == level)).findFirst();
  }
  
  @NonNull
  public Optional<DungeonLevelConfig> getLevelOrInfinite(int level) {
    Optional<DungeonLevelConfig> optionalDungeonLevel = Optional.empty();
    int maxDungeonLevel = this.levels.stream().mapToInt(DungeonLevelConfig::getLevel).max().orElse(2147483647);
    if (isInfinite() && level > maxDungeonLevel) {
      optionalDungeonLevel = getInfiniteLevel(level);
    } else {
      for (int i = level; i >= 0; i--) {
        Optional<DungeonLevelConfig> optionalLevel = getLevel(i);
        if (optionalLevel.isPresent()) {
          optionalDungeonLevel = optionalLevel;
          break;
        } 
      } 
    } 
    return optionalDungeonLevel;
  }
  
  public boolean isInfinite() {
    return (this.infiniteLevel != null);
  }
  
  @NonNull
  public Optional<DungeonLevelConfig> getInfiniteLevel(int level) {
    if (this.infiniteLevel == null)
      return Optional.empty(); 
    return Optional.of(new DungeonLevelConfig(this.infiniteLevel.getRawRooms(), this.infiniteLevel.getRoomCount(), this.infiniteLevel.getGeneration(), this.infiniteLevel.getRewards(), level, this.infiniteLevel.getEntities(), this.infiniteLevel.getLoots(), this.infiniteLevel.getRooms(), null));
  }
  
  public ItemStack getItem() {
    if (this.rawItem == null || this.rawItem.isEmpty())
      return null; 
    if (this.item == null)
      this.item = ItemUtils.parse(this.rawItem); 
    return this.item;
  }
  
  @NonNull
  public Color getColor() {
    if (this.color == null)
      this.color = Color.decode(this.rawColor); 
    return this.color;
  }
  
  @NonNull
  public Color getShadowColor() {
    if (this.shadowColor == null)
      this.shadowColor = Color.decode(this.rawShadowColor); 
    return this.shadowColor;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonConfig other = (DungeonConfig)obj;
    return Objects.equals(this.id, other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\DungeonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */