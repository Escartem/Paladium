package fr.paladium.palaautomation.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.ForgeDirection;

public enum EnumPipeFacing {
  NORTH(EnumFacing.NORTH, 0, 0, -1),
  SOUTH(EnumFacing.SOUTH, 0, 0, 1),
  EAST(EnumFacing.EAST, 1, 0, 0),
  WEST(EnumFacing.WEST, -1, 0, 0),
  DOWN(EnumFacing.DOWN, 0, -1, 0),
  UP(EnumFacing.UP, 0, 1, 0);
  
  public static final EnumPipeFacing[] VALUES;
  
  private final EnumFacing vanillaFacing;
  
  private final int frontOffsetX;
  
  private final int frontOffsetY;
  
  private final int frontOffsetZ;
  
  static {
    VALUES = values();
  }
  
  public EnumFacing getVanillaFacing() {
    return this.vanillaFacing;
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
  
  EnumPipeFacing(EnumFacing vanillaFacing, int frontOffsetX, int frontOffsetY, int frontOffsetZ) {
    this.vanillaFacing = vanillaFacing;
    this.frontOffsetX = frontOffsetX;
    this.frontOffsetY = frontOffsetY;
    this.frontOffsetZ = frontOffsetZ;
  }
  
  public static EnumPipeFacing fromForgeDirection(ForgeDirection direction) {
    if (direction == null)
      return NORTH; 
    switch (direction) {
      case NORTH:
        return NORTH;
      case SOUTH:
        return SOUTH;
      case EAST:
        return EAST;
      case WEST:
        return WEST;
      case DOWN:
        return DOWN;
      case UP:
        return UP;
    } 
    return NORTH;
  }
  
  public static boolean canTransact(EnumPipeFacing facing1, EnumPipeFacing facing2) {
    if (facing1 == null || facing2 == null)
      return false; 
    if (facing1 == facing2)
      return false; 
    return (facing1 != facing2.opposite());
  }
  
  public static boolean isOpposite(EnumPipeFacing facing1, EnumPipeFacing facing2) {
    if (facing1 == null || facing2 == null)
      return false; 
    if (facing1 == facing2)
      return false; 
    return (facing1.frontOffsetX == -facing2.frontOffsetX && facing1.frontOffsetY == -facing2.frontOffsetY && facing1.frontOffsetZ == -facing2.frontOffsetZ);
  }
  
  public static boolean isSameGroup(EnumPipeFacing facing1, EnumPipeFacing facing2) {
    if (facing1 == null || facing2 == null)
      return false; 
    if (facing1 == facing2)
      return true; 
    if (facing1 == NORTH || facing1 == SOUTH)
      return (facing2 == NORTH || facing2 == SOUTH); 
    if (facing1 == EAST || facing1 == WEST)
      return (facing2 == EAST || facing2 == WEST); 
    if (facing1 == UP || facing1 == DOWN)
      return (facing2 == UP || facing2 == DOWN); 
    return false;
  }
  
  public static EnumPipeFacing next(EnumPipeFacing facing) {
    if (facing == null)
      return NORTH; 
    int index = facing.ordinal() + 1;
    if (index >= VALUES.length)
      index = 0; 
    return VALUES[index];
  }
  
  public static EnumPipeFacing fromVanillaFacing(EnumFacing facing) {
    for (EnumPipeFacing pipeFacing : VALUES) {
      if (pipeFacing.vanillaFacing == facing)
        return pipeFacing; 
    } 
    return null;
  }
  
  public static void writeToNBT(EnumPipeFacing facing, NBTTagCompound compound) {
    if (facing == null || compound == null)
      return; 
    compound.func_74768_a("facing", facing.ordinal());
  }
  
  public static EnumPipeFacing fromNBT(NBTTagCompound compound) {
    if (compound == null)
      return NORTH; 
    if (!compound.func_74764_b("facing"))
      return NORTH; 
    return VALUES[compound.func_74762_e("facing")];
  }
  
  public EnumPipeFacing opposite() {
    if (this == NORTH)
      return SOUTH; 
    if (this == SOUTH)
      return NORTH; 
    if (this == EAST)
      return WEST; 
    if (this == WEST)
      return EAST; 
    if (this == DOWN)
      return UP; 
    if (this == UP)
      return DOWN; 
    return NORTH;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\EnumPipeFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */