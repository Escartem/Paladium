package fr.paladium.palamod.modules.homefinder.common.utils;

public enum RadiusEnum {
  ONE_FLOOR(8),
  TWO_FLOORS(16),
  THREE_FLOORS(32),
  FOUR_FLOORS(64),
  FIVE_FLOORS(128);
  
  private int radius;
  
  public int getRadius() {
    return this.radius;
  }
  
  RadiusEnum(int radius) {
    this.radius = radius;
  }
  
  public static int getRadiusFromFloor(int floor) {
    if (floor <= 0)
      return 0; 
    floor--;
    if (floor < 0)
      floor = 0; 
    if (floor > 4)
      floor = 4; 
    return (values()[floor]).radius;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\commo\\utils\RadiusEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */