package fr.paladium.palaschematicmod.common.pojo.schematic;

import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.data.EntityData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;

public class Schematic {
  private String name;
  
  private String author;
  
  private int version;
  
  private long date;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAuthor(String author) {
    this.author = author;
  }
  
  public void setVersion(int version) {
    this.version = version;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  public void setBlocks(List<BlockData> blocks) {
    this.blocks = blocks;
  }
  
  public void setEntities(List<EntityData> entities) {
    this.entities = entities;
  }
  
  public void setMin(BlockPos min) {
    this.min = min;
  }
  
  public void setMax(BlockPos max) {
    this.max = max;
  }
  
  public void setCenter(BlockPos center) {
    this.center = center;
  }
  
  public void setChunkedBlocks(Map<Long, List<BlockData>> chunkedBlocks) {
    this.chunkedBlocks = chunkedBlocks;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getAuthor() {
    return this.author;
  }
  
  public int getVersion() {
    return this.version;
  }
  
  public long getDate() {
    return this.date;
  }
  
  private List<BlockData> blocks = new ArrayList<>();
  
  public List<BlockData> getBlocks() {
    return this.blocks;
  }
  
  private List<EntityData> entities = new ArrayList<>();
  
  private transient BlockPos min;
  
  private transient BlockPos max;
  
  private transient BlockPos center;
  
  private transient Map<Long, List<BlockData>> chunkedBlocks;
  
  public List<EntityData> getEntities() {
    return this.entities;
  }
  
  public BlockPos getMin() {
    return this.min;
  }
  
  public BlockPos getMax() {
    return this.max;
  }
  
  public Map<Long, List<BlockData>> getChunkedBlocks() {
    return this.chunkedBlocks;
  }
  
  public Schematic(String name, String author, int version, long date) {
    this.name = name;
    this.author = author;
    this.version = version;
    this.date = date;
    this.blocks = new ArrayList<>();
    this.entities = new ArrayList<>();
  }
  
  public void addEntity(Entity entity, BlockPos center) {
    if (entity instanceof net.minecraft.entity.player.EntityPlayer)
      return; 
    this.entities.add(EntityData.from(entity, center));
  }
  
  public void addBlock(BlockData blockData) {
    this.blocks.add(blockData);
  }
  
  public BlockPos getMinPoint() {
    if (this.min == null)
      updateMinPoint(); 
    return this.min;
  }
  
  public BlockPos getMaxPoint() {
    if (this.max == null)
      updateMaxPoint(); 
    return this.max;
  }
  
  public BlockPos getCenter() {
    if (this.center == null)
      updateCenter(); 
    return this.center;
  }
  
  public Schematic updateMinPoint() {
    if (this.blocks.isEmpty()) {
      this.min = new BlockPos(0, 0, 0);
      return this;
    } 
    int minX = this.blocks.stream().mapToInt(b -> b.getPos().getX()).min().getAsInt();
    int minY = this.blocks.stream().mapToInt(b -> b.getPos().getY()).min().getAsInt();
    int minZ = this.blocks.stream().mapToInt(b -> b.getPos().getZ()).min().getAsInt();
    this.min = new BlockPos(minX, minY, minZ);
    return this;
  }
  
  public Schematic updateMaxPoint() {
    if (this.blocks.isEmpty()) {
      this.max = new BlockPos(0, 0, 0);
      return this;
    } 
    int maxX = this.blocks.stream().mapToInt(b -> b.getPos().getX()).max().getAsInt();
    int maxY = this.blocks.stream().mapToInt(b -> b.getPos().getY()).max().getAsInt();
    int maxZ = this.blocks.stream().mapToInt(b -> b.getPos().getZ()).max().getAsInt();
    this.max = new BlockPos(maxX, maxY, maxZ);
    return this;
  }
  
  public Schematic updateCenter() {
    if (this.blocks.isEmpty()) {
      this.center = new BlockPos(0, 0, 0);
      return this;
    } 
    BlockPos min = getMinPoint();
    BlockPos max = getMaxPoint();
    int centerX = (min.getX() + max.getX()) / 2;
    int centerY = (min.getY() + max.getY()) / 2;
    int centerZ = (min.getZ() + max.getZ()) / 2;
    this.center = new BlockPos(centerX, centerY, centerZ);
    return this;
  }
  
  public Schematic() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\schematic\Schematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */