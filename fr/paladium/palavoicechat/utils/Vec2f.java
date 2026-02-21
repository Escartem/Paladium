package fr.paladium.palavoicechat.utils;

public class Vec2f {
  private final float x;
  
  private final float y;
  
  public Vec2f(float x, float y) {
    this.x = x;
    this.y = y;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Vec2f))
      return false; 
    Vec2f other = (Vec2f)o;
    return !other.canEqual(this) ? false : ((Float.compare(getX(), other.getX()) != 0) ? false : (!(Float.compare(getY(), other.getY()) != 0)));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Vec2f;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + Float.floatToIntBits(getX());
    return result * 59 + Float.floatToIntBits(getY());
  }
  
  public String toString() {
    return "Vec2f(x=" + getX() + ", y=" + getY() + ")";
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\Vec2f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */