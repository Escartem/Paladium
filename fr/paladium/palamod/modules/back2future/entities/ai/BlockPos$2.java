package fr.paladium.palamod.modules.back2future.entities.ai;

import java.util.Iterator;
import net.minecraft.util.com.google.common.collect.AbstractIterator;

final class null implements Iterable {
  private static final String __OBFID = "CL_00002331";
  
  public Iterator iterator() {
    return (Iterator)new AbstractIterator() {
        private BlockPos.MutableBlockPos theBlockPos = null;
        
        private static final String __OBFID = "CL_00002330";
        
        protected BlockPos.MutableBlockPos computeNext0() {
          if (this.theBlockPos == null) {
            this
              .theBlockPos = new BlockPos.MutableBlockPos(var2.getX(), var2.getY(), var2.getZ(), null);
            return this.theBlockPos;
          } 
          if (this.theBlockPos.equals(var3))
            return (BlockPos.MutableBlockPos)endOfData(); 
          int var1 = this.theBlockPos.getX();
          int var2xx = this.theBlockPos.getY();
          int var3x = this.theBlockPos.getZ();
          if (var1 < var3.getX()) {
            var1++;
          } else if (var2xx < var3.getY()) {
            var1 = var2.getX();
            var2xx++;
          } else if (var3x < var3.getZ()) {
            var1 = var2.getX();
            var2xx = var2.getY();
            var3x++;
          } 
          this.theBlockPos.x = var1;
          this.theBlockPos.y = var2xx;
          this.theBlockPos.z = var3x;
          return this.theBlockPos;
        }
        
        protected Object computeNext() {
          return computeNext0();
        }
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\BlockPos$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */