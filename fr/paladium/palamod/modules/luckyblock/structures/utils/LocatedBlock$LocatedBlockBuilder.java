package fr.paladium.palamod.modules.luckyblock.structures.utils;

import fr.paladium.palajobs.utils.forge.location.Location;
import net.minecraft.block.Block;

public class LocatedBlockBuilder {
  private Location location;
  
  private Block block;
  
  private long expirationMillis;
  
  public LocatedBlockBuilder location(Location location) {
    this.location = location;
    return this;
  }
  
  public LocatedBlockBuilder block(Block block) {
    this.block = block;
    return this;
  }
  
  public LocatedBlockBuilder expirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
    return this;
  }
  
  public LocatedBlock build() {
    return new LocatedBlock(this.location, this.block, this.expirationMillis);
  }
  
  public String toString() {
    return "LocatedBlock.LocatedBlockBuilder(location=" + this.location + ", block=" + this.block + ", expirationMillis=" + this.expirationMillis + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structure\\utils\LocatedBlock$LocatedBlockBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */