package fr.paladium.palamod.modules.luckyblock.structures.utils;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.blocks.IExpirableBlock;
import net.minecraft.block.Block;

public class LocatedBlock {
  private Location location;
  
  private Block block;
  
  private long expirationMillis;
  
  LocatedBlock(Location location, Block block, long expirationMillis) {
    this.location = location;
    this.block = block;
    this.expirationMillis = expirationMillis;
  }
  
  public static LocatedBlockBuilder builder() {
    return new LocatedBlockBuilder();
  }
  
  public static class LocatedBlockBuilder {
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
  
  public void setLocation(Location location) {
    this.location = location;
  }
  
  public void setBlock(Block block) {
    this.block = block;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public void updateExpirationMillis(long expirationMillis) {
    updateExpirationMillis(expirationMillis, null);
  }
  
  public void updateExpirationMillis(long expirationMillis, AbstractStructure structure) {
    this.expirationMillis = expirationMillis;
    if (!(this.block instanceof IExpirableBlock))
      return; 
    IExpirableBlock expirableBlock = (IExpirableBlock)this.block;
    expirableBlock.init(this.location
        .getWorld(), 
        (int)this.location.getX(), (int)this.location.getY(), (int)this.location.getZ(), this.expirationMillis, structure);
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof LocatedBlock))
      return false; 
    LocatedBlock locatedBlock = (LocatedBlock)object;
    if (!locatedBlock.getBlock().equals(this.block))
      return false; 
    return locatedBlock.getLocation().equals(this.location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structure\\utils\LocatedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */