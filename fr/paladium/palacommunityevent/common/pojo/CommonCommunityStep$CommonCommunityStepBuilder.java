package fr.paladium.palacommunityevent.common.pojo;

public class CommonCommunityStepBuilder {
  private String id;
  
  private int requiredPlayerPercent;
  
  private int percentToReach;
  
  public CommonCommunityStepBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public CommonCommunityStepBuilder requiredPlayerPercent(int requiredPlayerPercent) {
    this.requiredPlayerPercent = requiredPlayerPercent;
    return this;
  }
  
  public CommonCommunityStepBuilder percentToReach(int percentToReach) {
    this.percentToReach = percentToReach;
    return this;
  }
  
  public CommonCommunityStep build() {
    return new CommonCommunityStep(this.id, this.requiredPlayerPercent, this.percentToReach);
  }
  
  public String toString() {
    return "CommonCommunityStep.CommonCommunityStepBuilder(id=" + this.id + ", requiredPlayerPercent=" + this.requiredPlayerPercent + ", percentToReach=" + this.percentToReach + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\pojo\CommonCommunityStep$CommonCommunityStepBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */