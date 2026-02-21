package fr.paladium.palacommunityevent.common.pojo;

import net.minecraft.nbt.NBTTagCompound;

public class CommonCommunityStep extends CommunityStep {
  public String id;
  
  public int requiredPlayerPercent;
  
  public CommonCommunityStep() {}
  
  public static CommonCommunityStepBuilder builder() {
    return new CommonCommunityStepBuilder();
  }
  
  public static class CommonCommunityStepBuilder {
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
  
  public CommonCommunityStep(String id, int requiredPlayerPercent, int percentToReach) {
    super(percentToReach);
    this.id = id;
    this.requiredPlayerPercent = requiredPlayerPercent;
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = super.writeToNBT();
    compound.func_74778_a("id", this.id);
    compound.func_74768_a("requiredPlayerPercent", this.requiredPlayerPercent);
    return compound;
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.id = compound.func_74779_i("id");
    this.requiredPlayerPercent = compound.func_74762_e("requiredPlayerPercent");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\pojo\CommonCommunityStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */