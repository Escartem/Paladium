package fr.paladium.palamod.modules.luckyblock.pathfinding;

import com.google.common.collect.AbstractIterator;

class null extends AbstractIterator<Object> {
  private BlockPos lastReturned = null;
  
  protected BlockPos computeNext0() {
    if (this.lastReturned == null) {
      this.lastReturned = blockpos2;
      return this.lastReturned;
    } 
    if (this.lastReturned.equals(blockpos3))
      return (BlockPos)endOfData(); 
    int i = this.lastReturned.getX();
    int j = this.lastReturned.getY();
    int k = this.lastReturned.getZ();
    if (i < blockpos3.getX()) {
      i++;
    } else if (j < blockpos3.getY()) {
      i = blockpos2.getX();
      j++;
    } else if (k < blockpos3.getZ()) {
      i = blockpos2.getX();
      j = blockpos2.getY();
      k++;
    } 
    this.lastReturned = new BlockPos(i, j, k);
    return this.lastReturned;
  }
  
  protected Object computeNext() {
    return computeNext0();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\BlockPos$1$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */