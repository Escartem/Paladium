package fr.paladium.palaschematicmod.common.pojo.data;

import java.util.ArrayList;
import java.util.List;

public class SchematicChunk {
  private List<BlockData> blockData;
  
  public void setBlockData(List<BlockData> blockData) {
    this.blockData = blockData;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SchematicChunk))
      return false; 
    SchematicChunk other = (SchematicChunk)o;
    if (!other.canEqual(this))
      return false; 
    Object<BlockData> this$blockData = (Object<BlockData>)getBlockData(), other$blockData = (Object<BlockData>)other.getBlockData();
    return !((this$blockData == null) ? (other$blockData != null) : !this$blockData.equals(other$blockData));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SchematicChunk;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<BlockData> $blockData = (Object<BlockData>)getBlockData();
    return result * 59 + (($blockData == null) ? 43 : $blockData.hashCode());
  }
  
  public String toString() {
    return "SchematicChunk(blockData=" + getBlockData() + ")";
  }
  
  public SchematicChunk() {
    this.blockData = new ArrayList<>();
  }
  
  public List<BlockData> getBlockData() {
    return this.blockData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\data\SchematicChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */