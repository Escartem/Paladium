package fr.paladium.palamod.modules.back2future.entities.ai;

import java.util.Iterator;
import net.minecraft.util.com.google.common.collect.AbstractIterator;

final class null implements Iterable {
  private static final String __OBFID = "CL_00002333";
  
  public Iterator iterator() {
    return (Iterator)new AbstractIterator() {
        private BlockPos lastReturned = null;
        
        private static final String __OBFID = "CL_00002332";
        
        protected BlockPos computeNext0() {
          if (this.lastReturned == null) {
            this.lastReturned = var2;
            return this.lastReturned;
          } 
          if (this.lastReturned.equals(var3))
            return (BlockPos)endOfData(); 
          int var1 = this.lastReturned.getX();
          int var2x = this.lastReturned.getY();
          int var3x = this.lastReturned.getZ();
          if (var1 < var3.getX()) {
            var1++;
          } else if (var2x < var3.getY()) {
            var1 = var2.getX();
            var2x++;
          } else if (var3x < var3.getZ()) {
            var1 = var2.getX();
            var2x = var2.getY();
            var3x++;
          } 
          this.lastReturned = new BlockPos(var1, var2x, var3x);
          return this.lastReturned;
        }
        
        protected Object computeNext() {
          return computeNext0();
        }
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\BlockPos$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */