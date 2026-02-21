package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto;

public class WearableCosmeticHidePartProperty {
  private boolean head;
  
  private boolean body;
  
  private boolean leftArm;
  
  private boolean rightArm;
  
  private boolean leftLeg;
  
  private boolean rightLeg;
  
  public void setHead(boolean head) {
    this.head = head;
  }
  
  public void setBody(boolean body) {
    this.body = body;
  }
  
  public void setLeftArm(boolean leftArm) {
    this.leftArm = leftArm;
  }
  
  public void setRightArm(boolean rightArm) {
    this.rightArm = rightArm;
  }
  
  public void setLeftLeg(boolean leftLeg) {
    this.leftLeg = leftLeg;
  }
  
  public void setRightLeg(boolean rightLeg) {
    this.rightLeg = rightLeg;
  }
  
  public String toString() {
    return "WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty.WearableCosmeticHidePartProperty(head=" + isHead() + ", body=" + isBody() + ", leftArm=" + isLeftArm() + ", rightArm=" + isRightArm() + ", leftLeg=" + isLeftLeg() + ", rightLeg=" + isRightLeg() + ")";
  }
  
  public WearableCosmeticHidePartProperty() {}
  
  public WearableCosmeticHidePartProperty(boolean head, boolean body, boolean leftArm, boolean rightArm, boolean leftLeg, boolean rightLeg) {
    this.head = head;
    this.body = body;
    this.leftArm = leftArm;
    this.rightArm = rightArm;
    this.leftLeg = leftLeg;
    this.rightLeg = rightLeg;
  }
  
  public boolean isHead() {
    return this.head;
  }
  
  public boolean isBody() {
    return this.body;
  }
  
  public boolean isLeftArm() {
    return this.leftArm;
  }
  
  public boolean isRightArm() {
    return this.rightArm;
  }
  
  public boolean isLeftLeg() {
    return this.leftLeg;
  }
  
  public boolean isRightLeg() {
    return this.rightLeg;
  }
  
  public void hideAll() {
    this.head = true;
    this.body = true;
    this.leftArm = true;
    this.rightArm = true;
    this.leftLeg = true;
    this.rightLeg = true;
  }
  
  public void showAll() {
    this.head = false;
    this.body = false;
    this.leftArm = false;
    this.rightArm = false;
    this.leftLeg = false;
    this.rightLeg = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\server\dto\WearableCosmetic$WearableCosmeticProperties$WearableCosmeticHideProperty$WearableCosmeticHidePartProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */