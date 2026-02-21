package fr.paladium.palashop.provider.box.common.dto.box;

public enum Type {
  SHOP_ITEM(100, 7500.0F),
  BOOST_EPIC(300, 9000.0F),
  BOOST_LEGENDARY(600, 10500.0F),
  FREE_SPIN(100, 7500.0F);
  
  Type(int wheelCount, float duration) {
    this.wheelCount = wheelCount;
    this.duration = duration;
  }
  
  private final int wheelCount;
  
  private final float duration;
  
  public int getWheelCount() {
    return this.wheelCount;
  }
  
  public float getDuration() {
    return this.duration;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxReward$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */