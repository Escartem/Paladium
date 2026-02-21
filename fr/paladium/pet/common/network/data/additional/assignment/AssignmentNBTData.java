package fr.paladium.pet.common.network.data.additional.assignment;

import fr.paladium.pet.common.network.data.additional.INbtData;
import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AssignmentNBTData implements INbtData {
  public static final double NO_PROGRESS = -1.0D;
  
  public static final String TAG_ASSIGNMENT_ID = "assignmentId";
  
  public static final String TAG_PROGRESS = "progress";
  
  public static final String TAG_COMPLETED = "completed";
  
  private static final String TAG_ASSIGNMENTS = "assignments";
  
  public void setAssignments(HashMap<String, AssignmentData> assignments) {
    this.assignments = assignments;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof AssignmentNBTData))
      return false; 
    AssignmentNBTData other = (AssignmentNBTData)o;
    if (!other.canEqual(this))
      return false; 
    Object<String, AssignmentData> this$assignments = (Object<String, AssignmentData>)getAssignments(), other$assignments = (Object<String, AssignmentData>)other.getAssignments();
    return !((this$assignments == null) ? (other$assignments != null) : !this$assignments.equals(other$assignments));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof AssignmentNBTData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<String, AssignmentData> $assignments = (Object<String, AssignmentData>)getAssignments();
    return result * 59 + (($assignments == null) ? 43 : $assignments.hashCode());
  }
  
  public String toString() {
    return "AssignmentNBTData(assignments=" + getAssignments() + ")";
  }
  
  public HashMap<String, AssignmentData> getAssignments() {
    return this.assignments;
  }
  
  private HashMap<String, AssignmentData> assignments = new HashMap<>();
  
  public void read(NBTTagCompound compound) {
    this.assignments.clear();
    NBTTagList tagList = compound.func_150295_c("assignments", 10);
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound assignmentCompound = tagList.func_150305_b(i);
      String id = assignmentCompound.func_74779_i("assignmentId");
      double progress = assignmentCompound.func_74769_h("progress");
      boolean completed = assignmentCompound.func_74767_n("completed");
      AssignmentData data = new AssignmentData(id);
      data.setProgress(progress);
      data.setCompleted(completed);
      this.assignments.put(id, data);
    } 
  }
  
  public void write(NBTTagCompound compound) {
    NBTTagList tagList = new NBTTagList();
    this.assignments.forEach((id, data) -> {
          NBTTagCompound assignmentCompound = new NBTTagCompound();
          assignmentCompound.func_74778_a("assignmentId", data.getAssignmentId());
          assignmentCompound.func_74780_a("progress", data.getProgress());
          assignmentCompound.func_74757_a("completed", data.isCompleted());
          tagList.func_74742_a((NBTBase)assignmentCompound);
        });
    compound.func_74782_a("assignments", (NBTBase)tagList);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\assignment\AssignmentNBTData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */