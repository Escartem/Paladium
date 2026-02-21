package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto;

public class WearableCosmeticHideProperty {
  private WearableCosmeticHidePartProperty part;
  
  private WearableCosmeticHideArmorProperty armor;
  
  public void setPart(WearableCosmeticHidePartProperty part) {
    this.part = part;
  }
  
  public void setArmor(WearableCosmeticHideArmorProperty armor) {
    this.armor = armor;
  }
  
  public String toString() {
    return "WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty(part=" + getPart() + ", armor=" + getArmor() + ")";
  }
  
  public WearableCosmeticHideProperty() {
    this.part = new WearableCosmeticHidePartProperty();
    this.armor = new WearableCosmeticHideArmorProperty();
  }
  
  public WearableCosmeticHideProperty(WearableCosmeticHidePartProperty part, WearableCosmeticHideArmorProperty armor) {
    this.part = new WearableCosmeticHidePartProperty();
    this.armor = new WearableCosmeticHideArmorProperty();
    this.part = part;
    this.armor = armor;
  }
  
  public WearableCosmeticHidePartProperty getPart() {
    return this.part;
  }
  
  public WearableCosmeticHideArmorProperty getArmor() {
    return this.armor;
  }
  
  public static class WearableCosmeticHidePartProperty {
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
  
  public static class WearableCosmeticHideArmorProperty {
    private boolean helmet;
    
    private boolean chestplate;
    
    private boolean leggings;
    
    private boolean boots;
    
    public void setHelmet(boolean helmet) {
      this.helmet = helmet;
    }
    
    public void setChestplate(boolean chestplate) {
      this.chestplate = chestplate;
    }
    
    public void setLeggings(boolean leggings) {
      this.leggings = leggings;
    }
    
    public void setBoots(boolean boots) {
      this.boots = boots;
    }
    
    public String toString() {
      return "WearableCosmetic.WearableCosmeticProperties.WearableCosmeticHideProperty.WearableCosmeticHideArmorProperty(helmet=" + isHelmet() + ", chestplate=" + isChestplate() + ", leggings=" + isLeggings() + ", boots=" + isBoots() + ")";
    }
    
    public WearableCosmeticHideArmorProperty() {}
    
    public WearableCosmeticHideArmorProperty(boolean helmet, boolean chestplate, boolean leggings, boolean boots) {
      this.helmet = helmet;
      this.chestplate = chestplate;
      this.leggings = leggings;
      this.boots = boots;
    }
    
    public boolean isHelmet() {
      return this.helmet;
    }
    
    public boolean isChestplate() {
      return this.chestplate;
    }
    
    public boolean isLeggings() {
      return this.leggings;
    }
    
    public boolean isBoots() {
      return this.boots;
    }
    
    public void hideAll() {
      this.helmet = true;
      this.chestplate = true;
      this.leggings = true;
      this.boots = true;
    }
    
    public void showAll() {
      this.helmet = false;
      this.chestplate = false;
      this.leggings = false;
      this.boots = false;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\server\dto\WearableCosmetic$WearableCosmeticProperties$WearableCosmeticHideProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */