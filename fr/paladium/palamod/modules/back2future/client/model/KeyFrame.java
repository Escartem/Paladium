package fr.paladium.palamod.modules.back2future.client.model;

public class KeyFrame {
  public int frame;
  
  public float posX;
  
  public float posY;
  
  public float posZ;
  
  public float rotX;
  
  public float rotY;
  
  public float rotZ;
  
  public KeyFrame(int frame, float posX, float posY, float posZ, float rotX, float rotY, float rotZ) {
    this.frame = frame;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.rotX = rotX;
    this.rotY = rotY;
    this.rotZ = rotZ;
  }
  
  public void setVars(int frame, float posX, float posY, float posZ, float rotX, float rotY, float rotZ) {
    this.frame = frame;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.rotX = rotX;
    this.rotY = rotY;
    this.rotZ = rotZ;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\KeyFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */