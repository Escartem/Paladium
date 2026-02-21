package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

public enum VentilatorDirection {
  NORTH(0, 0, -1),
  SOUTH(0, 0, 1),
  EAST(1, 0, 0),
  WEST(-1, 0, 0);
  
  private int xOffset;
  
  private int yOffset;
  
  private int zOffset;
  
  public int getXOffset() {
    return this.xOffset;
  }
  
  public int getYOffset() {
    return this.yOffset;
  }
  
  public int getZOffset() {
    return this.zOffset;
  }
  
  VentilatorDirection(int xOffset, int yOffset, int zOffset) {
    this.xOffset = xOffset;
    this.yOffset = yOffset;
    this.zOffset = zOffset;
  }
  
  public static VentilatorDirection fromMetadata(int l) {
    if (l == 0)
      return NORTH; 
    if (l == 1)
      return EAST; 
    if (l == 2)
      return SOUTH; 
    if (l == 3)
      return WEST; 
    return NORTH;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\VentilatorDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */