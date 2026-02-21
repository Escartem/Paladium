package fr.paladium.palaautomation.client.renderer.tile;

import fr.paladium.palaautomation.common.tile.EnumPipeFacing;

public enum PipeConnectionDirection {
  NORTH(false),
  SOUTH(false),
  EAST(false),
  WEST(false),
  UP(false),
  DOWN(false),
  NORTH_EAST(true),
  NORTH_WEST(true),
  SOUTH_EAST(true),
  SOUTH_WEST(true),
  UP_NORTH(true),
  UP_SOUTH(true),
  UP_EAST(true),
  UP_WEST(true),
  DOWN_NORTH(true),
  DOWN_SOUTH(true),
  DOWN_EAST(true),
  DOWN_WEST(true);
  
  private final boolean angle;
  
  public boolean isAngle() {
    return this.angle;
  }
  
  PipeConnectionDirection(boolean angle) {
    this.angle = angle;
  }
  
  public static PipeConnectionDirection fromSimpleFacing(EnumPipeFacing facing) {
    if (facing == null)
      return null; 
    switch (facing) {
      case NORTH:
        return NORTH;
      case SOUTH:
        return SOUTH;
      case EAST:
        return EAST;
      case WEST:
        return WEST;
      case UP:
        return UP;
      case DOWN:
        return DOWN;
    } 
    return null;
  }
  
  public static PipeConnectionDirection prioritizeConnection(EnumPipeFacing parent, EnumPipeFacing child) {
    if (parent == null || child == null)
      return null; 
    if (parent == EnumPipeFacing.NORTH && child == EnumPipeFacing.EAST)
      return NORTH_EAST; 
    if (parent == EnumPipeFacing.NORTH && child == EnumPipeFacing.WEST)
      return NORTH_WEST; 
    if (parent == EnumPipeFacing.SOUTH && child == EnumPipeFacing.EAST)
      return SOUTH_EAST; 
    if (parent == EnumPipeFacing.SOUTH && child == EnumPipeFacing.WEST)
      return SOUTH_WEST; 
    if (parent == EnumPipeFacing.EAST && child == EnumPipeFacing.NORTH)
      return NORTH_EAST; 
    if (parent == EnumPipeFacing.EAST && child == EnumPipeFacing.SOUTH)
      return SOUTH_EAST; 
    if (parent == EnumPipeFacing.WEST && child == EnumPipeFacing.NORTH)
      return NORTH_WEST; 
    if (parent == EnumPipeFacing.WEST && child == EnumPipeFacing.SOUTH)
      return SOUTH_WEST; 
    if (parent == EnumPipeFacing.UP && child == EnumPipeFacing.NORTH)
      return UP_NORTH; 
    if (parent == EnumPipeFacing.UP && child == EnumPipeFacing.SOUTH)
      return UP_SOUTH; 
    if (parent == EnumPipeFacing.UP && child == EnumPipeFacing.EAST)
      return UP_EAST; 
    if (parent == EnumPipeFacing.UP && child == EnumPipeFacing.WEST)
      return UP_WEST; 
    if (parent == EnumPipeFacing.DOWN && child == EnumPipeFacing.NORTH)
      return DOWN_NORTH; 
    if (parent == EnumPipeFacing.DOWN && child == EnumPipeFacing.SOUTH)
      return DOWN_SOUTH; 
    if (parent == EnumPipeFacing.DOWN && child == EnumPipeFacing.EAST)
      return DOWN_EAST; 
    if (parent == EnumPipeFacing.DOWN && child == EnumPipeFacing.WEST)
      return DOWN_WEST; 
    return fromSimpleFacing(parent);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\PipeConnectionDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */