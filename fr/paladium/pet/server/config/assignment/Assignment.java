package fr.paladium.pet.server.config.assignment;

import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import fr.paladium.pet.server.config.assignment.fields.LevelRange;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Assignment {
  private String id;
  
  private AssignmentType type;
  
  private String logo;
  
  private LevelRange levelRange;
  
  private float weight;
  
  private float probability;
  
  private int givenPoints;
  
  private float givenExp;
  
  private double amount;
  
  private ItemStack stack;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setType(AssignmentType type) {
    this.type = type;
  }
  
  public void setLogo(String logo) {
    this.logo = logo;
  }
  
  public void setLevelRange(LevelRange levelRange) {
    this.levelRange = levelRange;
  }
  
  public void setWeight(float weight) {
    this.weight = weight;
  }
  
  public void setProbability(float probability) {
    this.probability = probability;
  }
  
  public void setGivenPoints(int givenPoints) {
    this.givenPoints = givenPoints;
  }
  
  public void setGivenExp(float givenExp) {
    this.givenExp = givenExp;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public void setStack(ItemStack stack) {
    this.stack = stack;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Assignment))
      return false; 
    Assignment other = (Assignment)o;
    if (!other.canEqual(this))
      return false; 
    if (Float.compare(getWeight(), other.getWeight()) != 0)
      return false; 
    if (Float.compare(getProbability(), other.getProbability()) != 0)
      return false; 
    if (getGivenPoints() != other.getGivenPoints())
      return false; 
    if (Float.compare(getGivenExp(), other.getGivenExp()) != 0)
      return false; 
    if (Double.compare(getAmount(), other.getAmount()) != 0)
      return false; 
    Object this$id = getId(), other$id = other.getId();
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id))
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$logo = getLogo(), other$logo = other.getLogo();
    if ((this$logo == null) ? (other$logo != null) : !this$logo.equals(other$logo))
      return false; 
    Object this$levelRange = getLevelRange(), other$levelRange = other.getLevelRange();
    if ((this$levelRange == null) ? (other$levelRange != null) : !this$levelRange.equals(other$levelRange))
      return false; 
    Object this$stack = getStack(), other$stack = other.getStack();
    return !((this$stack == null) ? (other$stack != null) : !this$stack.equals(other$stack));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Assignment;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + Float.floatToIntBits(getWeight());
    result = result * 59 + Float.floatToIntBits(getProbability());
    result = result * 59 + getGivenPoints();
    result = result * 59 + Float.floatToIntBits(getGivenExp());
    long $amount = Double.doubleToLongBits(getAmount());
    result = result * 59 + (int)($amount >>> 32L ^ $amount);
    Object $id = getId();
    result = result * 59 + (($id == null) ? 43 : $id.hashCode());
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $logo = getLogo();
    result = result * 59 + (($logo == null) ? 43 : $logo.hashCode());
    Object $levelRange = getLevelRange();
    result = result * 59 + (($levelRange == null) ? 43 : $levelRange.hashCode());
    Object $stack = getStack();
    return result * 59 + (($stack == null) ? 43 : $stack.hashCode());
  }
  
  public String toString() {
    return "Assignment(id=" + getId() + ", type=" + getType() + ", logo=" + getLogo() + ", levelRange=" + getLevelRange() + ", weight=" + getWeight() + ", probability=" + getProbability() + ", givenPoints=" + getGivenPoints() + ", givenExp=" + getGivenExp() + ", amount=" + getAmount() + ", stack=" + getStack() + ")";
  }
  
  public Assignment() {}
  
  public Assignment(String id, AssignmentType type, String logo, LevelRange levelRange, float weight, float probability, int givenPoints, float givenExp, double amount, ItemStack stack) {
    this.id = id;
    this.type = type;
    this.logo = logo;
    this.levelRange = levelRange;
    this.weight = weight;
    this.probability = probability;
    this.givenPoints = givenPoints;
    this.givenExp = givenExp;
    this.amount = amount;
    this.stack = stack;
  }
  
  public String getId() {
    return this.id;
  }
  
  public AssignmentType getType() {
    return this.type;
  }
  
  public String getLogo() {
    return this.logo;
  }
  
  public LevelRange getLevelRange() {
    return this.levelRange;
  }
  
  public float getWeight() {
    return this.weight;
  }
  
  public float getProbability() {
    return this.probability;
  }
  
  public int getGivenPoints() {
    return this.givenPoints;
  }
  
  public float getGivenExp() {
    return this.givenExp;
  }
  
  public double getAmount() {
    return this.amount;
  }
  
  public ItemStack getStack() {
    return this.stack;
  }
  
  public String getName(EntityPlayer player) {
    return TTT.format(player, getTTTName(player), new Object[0]);
  }
  
  public String getDescription(EntityPlayer player) {
    return TTT.format(player, getTTTDescription(player), new Object[0]);
  }
  
  private String getTTTDescription(EntityPlayer player) {
    return "skill.assignment." + this.id.toLowerCase() + ".description";
  }
  
  private String getTTTName(EntityPlayer player) {
    return "skill.assignment." + this.id.toLowerCase() + ".name";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\assignment\Assignment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */