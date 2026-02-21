package fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums;

import fr.paladium.palajobs.utils.forge.location.Location;

public enum DanceDirection {
  FRONT, BACK, RIGHT, LEFT, JUMP, FINISHED;
  
  public static int roundYaw(float yaw) {
    double rotation = ((yaw - 90.0F) % 360.0F);
    if (rotation < 0.0D)
      rotation += 360.0D; 
    if (0.0D <= rotation && rotation < 45.0D)
      return 1; 
    if (45.0D <= rotation && rotation < 135.0D)
      return 2; 
    if (135.0D <= rotation && rotation < 225.0D)
      return 3; 
    if (225.0D <= rotation && rotation < 315.0D)
      return 0; 
    if (315.0D <= rotation && rotation < 360.0D)
      return 1; 
    return 2;
  }
  
  public static Location getRelativeLocation(Location location, DanceDirection direction, double value) {
    Location relativeLocation = location.clone();
    float yaw = relativeLocation.getYaw();
    int directionYaw = roundYaw(yaw);
    if (direction == FRONT && directionYaw == 2) {
      relativeLocation.setZ(relativeLocation.getZ() - value);
    } else if (direction == FRONT && directionYaw == 0) {
      relativeLocation.setZ(relativeLocation.getZ() + value);
    } else if (direction == FRONT && directionYaw == 1) {
      relativeLocation.setX(relativeLocation.getX() - value);
    } else if (direction == FRONT && directionYaw == 3) {
      relativeLocation.setX(relativeLocation.getX() + value);
    } else if (direction == BACK && directionYaw == 2) {
      relativeLocation.setZ(relativeLocation.getZ() + value);
    } else if (direction == BACK && directionYaw == 0) {
      relativeLocation.setZ(relativeLocation.getZ() - value);
    } else if (direction == BACK && directionYaw == 1) {
      relativeLocation.setX(relativeLocation.getX() + value);
    } else if (direction == BACK && directionYaw == 3) {
      relativeLocation.setX(relativeLocation.getX() - value);
    } else if (direction == LEFT && directionYaw == 2) {
      relativeLocation.setX(relativeLocation.getX() - value);
    } else if (direction == LEFT && directionYaw == 0) {
      relativeLocation.setX(relativeLocation.getX() + value);
    } else if (direction == LEFT && directionYaw == 1) {
      relativeLocation.setZ(relativeLocation.getZ() + value);
    } else if (direction == LEFT && directionYaw == 3) {
      relativeLocation.setZ(relativeLocation.getZ() - value);
    } else if (direction == RIGHT && directionYaw == 2) {
      relativeLocation.setX(relativeLocation.getX() + value);
    } else if (direction == RIGHT && directionYaw == 0) {
      relativeLocation.setX(relativeLocation.getX() - value);
    } else if (direction == RIGHT && directionYaw == 1) {
      relativeLocation.setZ(relativeLocation.getZ() - value);
    } else if (direction == RIGHT && directionYaw == 3) {
      relativeLocation.setZ(relativeLocation.getZ() + value);
    } else if (direction == JUMP) {
      relativeLocation.setY(relativeLocation.getY() + value);
    } 
    return relativeLocation;
  }
  
  public static Location getRelativeLocation(Location location, DanceDirection direction) {
    return getRelativeLocation(location, direction, 1.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\dancer\enums\DanceDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */