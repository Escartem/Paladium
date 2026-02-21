package fr.paladium.pet.common.network.data.additional.skill;

import fr.paladium.pet.common.network.data.PetPlayer;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SkillNBTData {
  public static final String NO_SKILL = "no_skill";
  
  public static final String TAG_ID = "id";
  
  public static final String TAG_LAST_CHANGE = "lastChange";
  
  public static final String TAG_NEXT_USE = "nextUse";
  
  private static final String TAG_SKILLS = "skills";
  
  public void setSkills(HashMap<Integer, SkillData> skills) {
    this.skills = skills;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SkillNBTData))
      return false; 
    SkillNBTData other = (SkillNBTData)o;
    if (!other.canEqual(this))
      return false; 
    Object<Integer, SkillData> this$skills = (Object<Integer, SkillData>)getSkills(), other$skills = (Object<Integer, SkillData>)other.getSkills();
    return !((this$skills == null) ? (other$skills != null) : !this$skills.equals(other$skills));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SkillNBTData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object<Integer, SkillData> $skills = (Object<Integer, SkillData>)getSkills();
    return result * 59 + (($skills == null) ? 43 : $skills.hashCode());
  }
  
  public String toString() {
    return "SkillNBTData(skills=" + getSkills() + ")";
  }
  
  public HashMap<Integer, SkillData> getSkills() {
    return this.skills;
  }
  
  private HashMap<Integer, SkillData> skills = new HashMap<>();
  
  public void read(PetPlayer pet, NBTTagCompound compound) {
    this.skills.clear();
    int slots = pet.getSlots(pet.getLevel());
    NBTTagList tagList = compound.func_150295_c("skills", 10);
    for (int i = 0; i < tagList.func_74745_c() && 
      i < slots; i++) {
      NBTTagCompound assignmentCompound = tagList.func_150305_b(i);
      String id = assignmentCompound.func_74779_i("id");
      long lastChange = assignmentCompound.func_74763_f("lastChange");
      long lastUse = assignmentCompound.func_74763_f("nextUse");
      SkillData data = new SkillData(id, lastChange, lastUse);
      this.skills.put(Integer.valueOf(i), data);
    } 
  }
  
  public void write(PetPlayer pet, NBTTagCompound compound) {
    NBTTagList tagList = new NBTTagList();
    int slots = pet.getSlots(pet.getLevel());
    for (int i = 0; i < slots; i++) {
      SkillData data = null;
      if (!this.skills.containsKey(Integer.valueOf(i))) {
        data = new SkillData("no_skill");
      } else {
        data = this.skills.get(Integer.valueOf(i));
      } 
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74778_a("id", data.getSkillId());
      nbt.func_74772_a("lastChange", data.getLastChangeMillis());
      nbt.func_74772_a("nextUse", data.getNextUseMillis());
      tagList.func_74742_a((NBTBase)nbt);
    } 
    compound.func_74782_a("skills", (NBTBase)tagList);
  }
  
  public SkillData get(String name) {
    for (SkillData data : this.skills.values()) {
      if (data.getSkillId().equals(name))
        return data; 
    } 
    return null;
  }
  
  public void refresh(PetPlayer petPlayer, EntityPlayer player) {
    NBTTagCompound nbt = player.getEntityData();
    if (nbt == null)
      return; 
    write(petPlayer, nbt);
    read(petPlayer, nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\skill\SkillNBTData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */