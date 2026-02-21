package fr.paladium.palarpg.module.dungeon.common.world.generator;

import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lombok.NonNull;

public final class DungeonRoomPath {
  private final transient DungeonGenerator generator;
  
  private final int row;
  
  private final int column;
  
  private final DungeonRoomConfig.DungeonRoomType type;
  
  private final Set<DungeonRoomPath> links;
  
  private boolean premium;
  
  private DungeonRoomConfig room;
  
  public DungeonGenerator getGenerator() {
    return this.generator;
  }
  
  public int getRow() {
    return this.row;
  }
  
  public int getColumn() {
    return this.column;
  }
  
  public DungeonRoomConfig.DungeonRoomType getType() {
    return this.type;
  }
  
  public Set<DungeonRoomPath> getLinks() {
    return this.links;
  }
  
  public boolean isPremium() {
    return this.premium;
  }
  
  public DungeonRoomConfig getRoom() {
    return this.room;
  }
  
  public DungeonRoomPath(int row, @NonNull int column, @NonNull DungeonGenerator generator, DungeonRoomConfig.DungeonRoomType type) {
    if (generator == null)
      throw new NullPointerException("generator is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.row = row;
    this.column = column;
    this.generator = generator;
    this.type = type;
    this.links = new HashSet<>();
  }
  
  @Nonnull
  public DungeonRoomPath link(@NonNull DungeonRoomPath path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    if (!this.links.contains(path))
      this.links.add(path); 
    return this;
  }
  
  @Nonnull
  public DungeonRoomPath premium() {
    this.premium = true;
    return this;
  }
  
  @NonNull
  public DungeonRoom create() throws Exception {
    if (this.room != null)
      throw new IllegalStateException("Room has already been created for this path"); 
    Random random = (this.generator.getWorld().getWorld()).field_73012_v;
    List<DungeonRoomConfig> rooms = (List<DungeonRoomConfig>)this.generator.getRoomConfigs().stream().filter(config -> (config.getType() == this.type)).collect(Collectors.toList());
    if (rooms.isEmpty()) {
      rooms = (List<DungeonRoomConfig>)this.generator.getLevel().getRooms().stream().filter(room -> (DungeonLevelConfig.DungeonLevelRoomType.from(room.getType()) == DungeonLevelConfig.DungeonLevelRoomType.ROOM)).filter(config -> (config.getType() == this.type)).collect(Collectors.toList());
      this.room = rooms.get(random.nextInt(rooms.size()));
      return DungeonRoom.create(this.row, this.premium, this.generator.getWorld(), this.room);
    } 
    DungeonRoomConfig roomConfig = rooms.get(random.nextInt(rooms.size()));
    if (!this.generator.getRoomConfigs().remove(roomConfig))
      throw new IllegalStateException("Room config " + roomConfig.getId() + " has already been used"); 
    this.room = roomConfig;
    return DungeonRoom.create(this.row, this.premium, this.generator.getWorld(), this.room);
  }
  
  public String toString() {
    return "DungeonRoomPath[row=" + this.row + ", type=" + this.type + ", links=" + this.links + "]";
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.column), Integer.valueOf(this.row), this.type });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DungeonRoomPath other = (DungeonRoomPath)obj;
    return (this.column == other.column && this.row == other.row && this.type == other.type);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\generator\DungeonGenerator$DungeonRoomPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */