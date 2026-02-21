package fr.paladium.palarpg.module.profile.common.skilltree;

public enum RPGSkillTreePosition {
  UP(0.0D, -125.0D),
  RIGHT(125.0D, 0.0D),
  LEFT(-125.0D, 0.0D),
  DOWN(0.0D, 125.0D);
  
  RPGSkillTreePosition(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  private final double x;
  
  private final double y;
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public boolean isVertical() {
    return (this == UP || this == DOWN);
  }
  
  public boolean isHorizontal() {
    return (this == LEFT || this == RIGHT);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\skilltree\RPGSkillTreePosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */