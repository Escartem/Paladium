package fr.paladium.pet.server.skill.handler.impl.active.data;

import java.util.Objects;

public class ChunkPos {
  private final int posX;
  
  private final int posZ;
  
  public ChunkPos(int posX, int posZ) {
    this.posX = posX;
    this.posZ = posZ;
  }
  
  public int getPosX() {
    return this.posX;
  }
  
  public int getPosZ() {
    return this.posZ;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ChunkPos chunkPos = (ChunkPos)obj;
    return (this.posX == chunkPos.posX && this.posZ == chunkPos.posZ);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.posX), Integer.valueOf(this.posZ) });
  }
  
  public static ChunkPos of(int x, int z) {
    return new ChunkPos(x, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\data\ChunkPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */