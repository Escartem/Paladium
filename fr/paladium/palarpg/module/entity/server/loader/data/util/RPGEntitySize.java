package fr.paladium.palarpg.module.entity.server.loader.data.util;

public class RPGEntitySize {
  private float width;
  
  private float height;
  
  public void setWidth(float width) {
    this.width = width;
  }
  
  public void setHeight(float height) {
    this.height = height;
  }
  
  public void setScale(float scale) {
    this.scale = scale;
  }
  
  public RPGEntitySize(float width, float height, float scale) {
    this.width = width;
    this.height = height;
    this.scale = scale;
  }
  
  public float getWidth() {
    return this.width;
  }
  
  public float getHeight() {
    return this.height;
  }
  
  private float scale = 1.0F;
  
  public float getScale() {
    return this.scale;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\dat\\util\RPGEntitySize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */