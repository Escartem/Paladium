package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import net.minecraft.block.Block;

class BlockReplacement {
  private final BlockLocation location;
  
  private final Block originBlock;
  
  private final int originMetadata;
  
  public BlockLocation getLocation() {
    return this.location;
  }
  
  public Block getOriginBlock() {
    return this.originBlock;
  }
  
  public int getOriginMetadata() {
    return this.originMetadata;
  }
  
  public BlockReplacement(BlockLocation loc) {
    this.location = loc;
    this.originBlock = loc.getBlock();
    this.originMetadata = loc.getBlockMetadata();
  }
  
  public int hashCode() {
    return this.location.hashCode();
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj instanceof BlockReplacement) {
      BlockReplacement object = (BlockReplacement)obj;
      return (object.getLocation() != null && object.getLocation().equals(getLocation()));
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGDashThroughAttack$BlockReplacement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */