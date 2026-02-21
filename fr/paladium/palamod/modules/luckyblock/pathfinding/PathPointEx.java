package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.pathfinding.PathPoint;

public class PathPointEx extends PathPoint {
  float field_75836_e;
  
  float field_75833_f;
  
  float field_75834_g;
  
  PathPointEx field_75841_h;
  
  public boolean visited;
  
  public PathPointEx(int x, int y, int z) {
    super(x, y, z);
  }
  
  public float distanceManhattan(PathPoint p_186281_1_) {
    float f = Math.abs(p_186281_1_.field_75839_a - this.field_75839_a);
    float f1 = Math.abs(p_186281_1_.field_75837_b - this.field_75837_b);
    float f2 = Math.abs(p_186281_1_.field_75838_c - this.field_75838_c);
    return f + f1 + f2;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathPointEx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */