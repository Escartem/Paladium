package fr.paladium.palamod.util;

import java.util.Objects;
import net.minecraft.block.Block;

public class IBlockState {
  private final Block block;
  
  private final int meta;
  
  public String toString() {
    return "IBlockState(block=" + getBlock() + ", meta=" + getMeta() + ")";
  }
  
  public IBlockState(Block block, int meta) {
    this.block = block;
    this.meta = meta;
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public int getMeta() {
    return this.meta;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.block, Integer.valueOf(this.meta) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    IBlockState other = (IBlockState)obj;
    if (!Objects.equals(this.block, other.block) || this.meta != other.meta)
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\IBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */