package fr.paladium.palamod.modules.pillage.common.blocks;

public enum EnumFacing {
  DOWN(0, 1, 0, -1, 0),
  UP(1, 0, 0, 1, 0),
  NORTH(2, 3, 0, 0, -1),
  SOUTH(3, 2, 0, 0, 1),
  EAST(4, 5, -1, 0, 0),
  WEST(5, 4, 1, 0, 0);
  
  private final int index;
  
  private final int opposite;
  
  private final int frontOffsetX;
  
  private final int frontOffsetY;
  
  private final int frontOffsetZ;
  
  private static final EnumFacing[] faceList;
  
  static {
    faceList = new EnumFacing[6];
    for (EnumFacing facing : values())
      faceList[facing.index] = facing; 
  }
  
  EnumFacing(int index, int opposite, int dirX, int dirY, int dirZ) {
    this.index = index;
    this.opposite = opposite;
    this.frontOffsetX = dirX;
    this.frontOffsetY = dirY;
    this.frontOffsetZ = dirZ;
  }
  
  public int getFrontOffsetX() {
    return this.frontOffsetX;
  }
  
  public int getFrontOffsetY() {
    return this.frontOffsetY;
  }
  
  public int getFrontOffsetZ() {
    return this.frontOffsetZ;
  }
  
  public static EnumFacing getFront(int side) {
    return faceList[Math.abs(side % faceList.length)];
  }
  
  public EnumFacing getOpposite() {
    return getFront(this.opposite);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockCoordinateJammer$EnumFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */