package fr.paladium.palarpg.module.dungeon.common.world.generator;

import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lombok.NonNull;

public final class DungeonGenerator {
  private final transient DungeonWorld world;
  
  private final transient DungeonLevelConfig level;
  
  private final transient List<DungeonRoomConfig> roomConfigs;
  
  private final transient Map<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration> roomGenerations;
  
  private final transient Map<DungeonRoomConfig.DungeonRoomType, Integer> roomGenerationCounts;
  
  private final int roomCount;
  
  private final List<DungeonRoomPath>[] roomPaths;
  
  public DungeonWorld getWorld() {
    return this.world;
  }
  
  public DungeonLevelConfig getLevel() {
    return this.level;
  }
  
  public List<DungeonRoomConfig> getRoomConfigs() {
    return this.roomConfigs;
  }
  
  public Map<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration> getRoomGenerations() {
    return this.roomGenerations;
  }
  
  public Map<DungeonRoomConfig.DungeonRoomType, Integer> getRoomGenerationCounts() {
    return this.roomGenerationCounts;
  }
  
  public int getRoomCount() {
    return this.roomCount;
  }
  
  public List<DungeonRoomPath>[] getRoomPaths() {
    return this.roomPaths;
  }
  
  public DungeonGenerator(@NonNull DungeonWorld world, @NonNull DungeonLevelConfig level) throws Exception {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (level == null)
      throw new NullPointerException("level is marked non-null but is null"); 
    this.world = world;
    this.level = level;
    this.roomPaths = (List<DungeonRoomPath>[])new ArrayList[3];
    this.roomCount = Double.valueOf(this.world.getParser().parseJson(level.getRoomCount()).get()).intValue() + ((this.level.has(DungeonRoomConfig.DungeonRoomType.MINIBOSS) || this.level.has(DungeonRoomConfig.DungeonRoomType.BOSS)) ? 1 : 0);
    this.roomConfigs = (List<DungeonRoomConfig>)level.getRooms().stream().collect(Collectors.toList());
    this.roomGenerations = new HashMap<>();
    this.roomGenerationCounts = new HashMap<>();
    for (Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonLevelConfig.DungeonLevelGenerationConfig> entry : (Iterable<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonLevelConfig.DungeonLevelGenerationConfig>>)level.getGeneration().entrySet()) {
      long min = (((DungeonLevelConfig.DungeonLevelGenerationConfig)entry.getValue()).getMin() == null) ? 0L : Double.valueOf(this.world.getParser().parseJson(((DungeonLevelConfig.DungeonLevelGenerationConfig)entry.getValue()).getMin()).get()).longValue();
      long max = (((DungeonLevelConfig.DungeonLevelGenerationConfig)entry.getValue()).getMax() == null) ? Long.MAX_VALUE : Double.valueOf(this.world.getParser().parseJson(((DungeonLevelConfig.DungeonLevelGenerationConfig)entry.getValue()).getMax()).get()).longValue();
      long weight = Double.valueOf(this.world.getParser().parseJson(((DungeonLevelConfig.DungeonLevelGenerationConfig)entry.getValue()).getWeight()).get()).longValue();
      this.roomGenerations.put(entry.getKey(), new DungeonRoomGeneration(min, max, weight));
      this.roomGenerationCounts.put(entry.getKey(), Integer.valueOf(0));
    } 
    if (this.roomGenerations.isEmpty())
      throw new IllegalStateException("No room generation configured for the dungeon"); 
    if (this.roomConfigs.size() < this.roomCount)
      throw new IllegalStateException("Not enough rooms for the dungeon"); 
  }
  
  @Nonnull
  public DungeonGenerator generate() throws Exception {
    this.roomGenerationCounts.clear();
    boolean hasPremium = false;
    Random random = (this.world.getWorld()).field_73012_v;
    this.roomPaths[1] = new ArrayList<>();
    this.roomPaths[1].add(new DungeonRoomPath(0, 1, this, DungeonRoomConfig.DungeonRoomType.CLASSIC));
    for (int row = 1; row < this.roomCount; row++)
      this.roomPaths[1].add(new DungeonRoomPath(row, 1, this, getRandomType())); 
    int lastRoom = this.roomPaths[1].size() - 1;
    if (this.level.has(DungeonRoomConfig.DungeonRoomType.MINIBOSS)) {
      this.roomPaths[1].set(lastRoom, new DungeonRoomPath(lastRoom, 1, this, DungeonRoomConfig.DungeonRoomType.MINIBOSS));
      this.roomGenerationCounts.put(DungeonRoomConfig.DungeonRoomType.MINIBOSS, Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(DungeonRoomConfig.DungeonRoomType.MINIBOSS, Integer.valueOf(0))).intValue() + 1));
    } else if (this.level.has(DungeonRoomConfig.DungeonRoomType.BOSS)) {
      this.roomPaths[1].set(lastRoom, new DungeonRoomPath(lastRoom, 1, this, DungeonRoomConfig.DungeonRoomType.BOSS));
      this.roomGenerationCounts.put(DungeonRoomConfig.DungeonRoomType.BOSS, Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(DungeonRoomConfig.DungeonRoomType.BOSS, Integer.valueOf(0))).intValue() + 1));
    } else {
      this.roomGenerationCounts.put(DungeonRoomConfig.DungeonRoomType.CLASSIC, Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(DungeonRoomConfig.DungeonRoomType.CLASSIC, Integer.valueOf(0))).intValue() + 1));
      this.roomPaths[1].set(lastRoom, new DungeonRoomPath(lastRoom, 1, this, DungeonRoomConfig.DungeonRoomType.CLASSIC));
    } 
    this.roomPaths[0] = new ArrayList<>();
    this.roomPaths[0].add(null);
    int consecutive = 0;
    int k;
    for (k = 1; k < this.roomPaths[1].size() - 1; k++) {
      boolean hasRoom = (consecutive >= (int)Math.floor((this.roomPaths[1].size() / 2.0F))) ? false : ((k == 1 || k == this.roomPaths[1].size() - 2) ? true : ((random.nextDouble() < 0.75D)));
      if (hasRoom) {
        consecutive++;
        this.roomPaths[0].add(new DungeonRoomPath(k, 0, this, getRandomType()));
      } else if (!hasPremium && random.nextDouble() < 0.1D && consecutive > 0) {
        consecutive = Integer.MAX_VALUE;
        hasPremium = true;
        this.roomPaths[0].add((new DungeonRoomPath(k, 0, this, DungeonRoomConfig.DungeonRoomType.CLASSIC)).premium());
      } else {
        consecutive = 0;
        this.roomPaths[0].add(null);
      } 
    } 
    this.roomPaths[0].add(null);
    this.roomPaths[2] = new ArrayList<>();
    this.roomPaths[2].add(null);
    consecutive = 0;
    for (k = 1; k < this.roomPaths[1].size() - 1; k++) {
      boolean hasRoom = (consecutive >= (int)Math.floor((this.roomPaths[1].size() / 2.0F))) ? false : ((k == 1 || k == this.roomPaths[1].size() - 2) ? true : ((random.nextDouble() < 0.75D)));
      if (hasRoom) {
        consecutive++;
        this.roomPaths[2].add(new DungeonRoomPath(k, 2, this, getRandomType()));
      } else if (!hasPremium && random.nextDouble() < 0.1D && consecutive > 0) {
        consecutive = Integer.MAX_VALUE;
        hasPremium = true;
        this.roomPaths[2].add((new DungeonRoomPath(k, 2, this, DungeonRoomConfig.DungeonRoomType.CLASSIC)).premium());
      } else {
        consecutive = 0;
        this.roomPaths[2].add(null);
      } 
    } 
    this.roomPaths[2].add(null);
    for (int i = 0; i < 3; i++) {
      List<DungeonRoomPath> path = this.roomPaths[i];
      for (int m = 0; m < path.size() - 1; m++) {
        DungeonRoomPath current = path.get(m);
        DungeonRoomPath next = path.get(m + 1);
        if (current != null && next != null)
          current.link(next); 
        if (i != 1 && current != null && (
          next == null || next.isPremium()))
          current.link(this.roomPaths[1].get(m + 1)); 
        if (i == 1 && current != null && next != null) {
          DungeonRoomPath currentLeft = this.roomPaths[0].get(m);
          DungeonRoomPath currentRight = this.roomPaths[2].get(m);
          DungeonRoomPath nextLeft = this.roomPaths[0].get(m + 1);
          DungeonRoomPath nextRight = this.roomPaths[2].get(m + 1);
          if (currentLeft == null && nextLeft != null)
            current.link(nextLeft); 
          if (currentRight == null && nextRight != null)
            current.link(nextRight); 
        } 
      } 
    } 
    List<DungeonRoomPath> leftPath = this.roomPaths[0];
    List<DungeonRoomPath> centerPath = this.roomPaths[1];
    List<DungeonRoomPath> rightPath = this.roomPaths[2];
    for (int j = 0; j < centerPath.size() - 1; j++) {
      DungeonRoomPath current = centerPath.get(j);
      DungeonRoomPath next = centerPath.get(j + 1);
      if (current != null && next != null) {
        DungeonRoomPath currentLeft = leftPath.get(j);
        DungeonRoomPath currentRight = rightPath.get(j);
        DungeonRoomPath nextLeft = leftPath.get(j + 1);
        DungeonRoomPath nextRight = rightPath.get(j + 1);
        if (nextLeft != null || nextRight != null) {
          double randomValue = random.nextDouble();
          if (randomValue <= 0.33D && nextLeft != null && !nextLeft.isPremium()) {
            if (currentLeft != null && random.nextBoolean()) {
              currentLeft.link(next);
            } else {
              current.link(nextLeft);
            } 
          } else if (randomValue <= 0.66D && nextRight != null && !nextRight.isPremium()) {
            if (currentRight != null && random.nextBoolean()) {
              currentRight.link(next);
            } else {
              current.link(nextRight);
            } 
          } else if (randomValue <= 1.0D && nextLeft != null && nextRight != null && !nextLeft.isPremium() && !nextRight.isPremium()) {
            current.link(nextLeft);
            current.link(nextRight);
          } 
        } 
      } 
    } 
    return this;
  }
  
  @NonNull
  public List<DungeonRoomPath> getPath(int column) {
    if (column < 0 || column >= this.roomPaths.length)
      throw new IllegalArgumentException("Index must be between 0 and " + (this.roomPaths.length - 1)); 
    if (this.roomPaths[column] == null)
      throw new IllegalStateException("Path " + column + " has not been generated yet"); 
    return this.roomPaths[column];
  }
  
  public boolean hasPath(int column, int row) {
    return (getPath(column).get(row) != null);
  }
  
  @NonNull
  public DungeonRoomPath getRoom(int column, int row) {
    if (!hasPath(column, row))
      throw new IllegalStateException("Room at " + column + "," + row + " has not been generated yet"); 
    return getPath(column).get(row);
  }
  
  @NonNull
  public DungeonGenerator printTree() {
    int cols = 3;
    int widthPerCol = 2;
    int totalWidth = 6;
    int rows = this.roomPaths[1].size();
    Function<DungeonRoomPath, Integer> findColumn = path -> {
        if (path == null)
          return Integer.valueOf(-1); 
        for (int c = 0; c < 3; c++) {
          List<DungeonRoomPath> p = this.roomPaths[c];
          for (int r = 0; r < p.size(); r++) {
            if (p.get(r) == path)
              return Integer.valueOf(c); 
          } 
        } 
        return Integer.valueOf(-1);
      };
    DungeonModule.logger.debug("===== DUNGEON TREE =====", new Object[0]);
    for (int r = 0; r < rows; r++) {
      char[] nodeLine = new char[6];
      for (int i = 0; i < 6; i++)
        nodeLine[i] = ' '; 
      for (int c = 0; c < 3; c++) {
        List<DungeonRoomPath> colPath = this.roomPaths[c];
        if (r < colPath.size() && colPath.get(r) != null) {
          DungeonRoomPath cur = colPath.get(r);
          int center = c * 2 + 1;
          nodeLine[center] = cur.isPremium() ? '★' : Character.toLowerCase(cur.getType().name().charAt(0));
        } 
      } 
      DungeonModule.logger.debug("         " + new String(nodeLine), new Object[0]);
      if (r != rows - 1) {
        char[] connLine = new char[6];
        for (int k = 0; k < 6; k++)
          connLine[k] = ' '; 
        for (int j = 0; j < 3; j++) {
          List<DungeonRoomPath> colPath = this.roomPaths[j];
          if (r < colPath.size()) {
            DungeonRoomPath cur = colPath.get(r);
            if (cur != null)
              for (DungeonRoomPath linked : cur.getLinks()) {
                int colLinked = ((Integer)findColumn.apply(linked)).intValue();
                if (colLinked == -1)
                  continue; 
                int rowLinked = this.roomPaths[colLinked].indexOf(linked);
                if (rowLinked != r + 1)
                  continue; 
                int centerCur = j * 2 + 1;
                int centerLinked = colLinked * 2 + 1;
                if (centerCur == centerLinked) {
                  connLine[centerCur] = '|';
                  continue;
                } 
                if (centerLinked < centerCur) {
                  connLine[centerCur - 1] = '/';
                  continue;
                } 
                connLine[centerCur + 1] = '\\';
              }  
          } 
        } 
        DungeonModule.logger.debug("         " + new String(connLine), new Object[0]);
      } 
    } 
    DungeonModule.logger.debug("", new Object[0]);
    DungeonModule.logger.debug("", new Object[0]);
    DungeonModule.logger.debug("★: PREMIUM", new Object[0]);
    for (DungeonRoomConfig.DungeonRoomType type : DungeonRoomConfig.DungeonRoomType.values())
      DungeonModule.logger.debug(Character.toLowerCase(type.name().charAt(0)) + ": " + type.name() + " (" + this.roomGenerationCounts.getOrDefault(type, Integer.valueOf(0)) + ")", new Object[0]); 
    DungeonModule.logger.debug("", new Object[0]);
    DungeonModule.logger.debug("Room Count: " + this.roomCount, new Object[0]);
    DungeonModule.logger.debug("========================", new Object[0]);
    return this;
  }
  
  @NonNull
  private DungeonRoomConfig.DungeonRoomType getRandomType() {
    Random random = (this.world.getWorld()).field_73012_v;
    List<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration>> mustGenerate = (List<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration>>)this.roomGenerations.entrySet().stream().filter(entry -> (((Integer)this.roomGenerationCounts.getOrDefault(entry.getKey(), Integer.valueOf(0))).intValue() < ((DungeonRoomGeneration)entry.getValue()).getMin())).filter(entry -> this.level.has((DungeonRoomConfig.DungeonRoomType)entry.getKey())).collect(Collectors.toList());
    if (!mustGenerate.isEmpty()) {
      long totalWeight = mustGenerate.stream().mapToLong(e -> ((DungeonRoomGeneration)e.getValue()).getWeight()).sum();
      long randomValue = (long)(random.nextDouble() * totalWeight);
      long current = 0L;
      for (Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration> entry : mustGenerate) {
        current += ((DungeonRoomGeneration)entry.getValue()).getWeight();
        if (randomValue < current) {
          this.roomGenerationCounts.put(entry.getKey(), Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(entry.getKey(), Integer.valueOf(0))).intValue() + 1));
          return entry.getKey();
        } 
      } 
    } 
    List<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration>> candidates = (List<Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration>>)this.roomGenerations.entrySet().stream().filter(entry -> (((Integer)this.roomGenerationCounts.getOrDefault(entry.getKey(), Integer.valueOf(0))).intValue() < ((DungeonRoomGeneration)entry.getValue()).getMax())).filter(entry -> this.level.has((DungeonRoomConfig.DungeonRoomType)entry.getKey())).collect(Collectors.toList());
    if (!candidates.isEmpty()) {
      long totalWeight = candidates.stream().mapToLong(e -> ((DungeonRoomGeneration)e.getValue()).getWeight()).sum();
      long randomValue = (long)(random.nextDouble() * totalWeight);
      long current = 0L;
      for (Map.Entry<DungeonRoomConfig.DungeonRoomType, DungeonRoomGeneration> entry : candidates) {
        current += ((DungeonRoomGeneration)entry.getValue()).getWeight();
        if (randomValue < current) {
          this.roomGenerationCounts.put(entry.getKey(), Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(entry.getKey(), Integer.valueOf(0))).intValue() + 1));
          return entry.getKey();
        } 
      } 
    } 
    this.roomGenerationCounts.put(DungeonRoomConfig.DungeonRoomType.CLASSIC, Integer.valueOf(((Integer)this.roomGenerationCounts.getOrDefault(DungeonRoomConfig.DungeonRoomType.CLASSIC, Integer.valueOf(0))).intValue() + 1));
    return DungeonRoomConfig.DungeonRoomType.CLASSIC;
  }
  
  public final class DungeonRoomGeneration {
    private final long min;
    
    private final long max;
    
    private final long weight;
    
    public DungeonRoomGeneration(long min, long max, long weight) {
      this.min = min;
      this.max = max;
      this.weight = weight;
    }
    
    public long getMin() {
      return this.min;
    }
    
    public long getMax() {
      return this.max;
    }
    
    public long getWeight() {
      return this.weight;
    }
  }
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\generator\DungeonGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */