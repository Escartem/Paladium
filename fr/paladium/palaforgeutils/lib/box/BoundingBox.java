package fr.paladium.palaforgeutils.lib.box;

public class BoundingBox {
  private double minX;
  
  private double minY;
  
  private double maxX;
  
  private double maxY;
  
  public double getMinX() {
    return this.minX;
  }
  
  public double getMinY() {
    return this.minY;
  }
  
  public double getMaxX() {
    return this.maxX;
  }
  
  public double getMaxY() {
    return this.maxY;
  }
  
  private BoundingBox(double minX, double minY, double maxX, double maxY) {
    this.minX = minX;
    this.minY = minY;
    this.maxX = maxX;
    this.maxY = maxY;
  }
  
  public static BoundingBox create(double x, double y, double width, double height) {
    return new BoundingBox(x, y, x + width, y + height);
  }
  
  public BoundingBox copy() {
    return new BoundingBox(this.minX, this.minY, this.maxX, this.maxY);
  }
  
  public BoundingBox expand(double value) {
    this.minX -= value;
    this.minY -= value;
    this.maxX += value;
    this.maxY += value;
    return this;
  }
  
  public BoundingBox contract(double value) {
    this.minX += value;
    this.minY += value;
    this.maxX -= value;
    this.maxY -= value;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\box\BoundingBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */