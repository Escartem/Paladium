package fr.paladium.pet.common.network.data.additional.roll;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.INbtData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SkillRollNBTData implements INbtData {
  public static final String TAG_SLOT = "slot";
  
  public static final String TAG_ID = "id";
  
  private static final String TAG_ROLLS = "rolls";
  
  private List<SkillRollData> rolls = new ArrayList<>();
  
  public List<SkillRollData> getRolls(PetPlayer pet) {
    Collection<SkillData> skills = pet.getSkillData().getSkills().values();
    this.rolls.removeIf(roll -> {
          if (roll.getSlot() < 0 || roll.getSlot() > 5)
            return true; 
          boolean found = false;
          for (SkillData skill : skills) {
            if (skill.getSkillId().equals(roll.getSkillId())) {
              found = true;
              break;
            } 
          } 
          return !found;
        });
    return this.rolls;
  }
  
  public SkillRollData get(PetPlayer pet, int slot) {
    for (SkillRollData roll : getRolls(pet)) {
      if (roll.getSlot() == slot)
        return roll; 
    } 
    return null;
  }
  
  public void read(NBTTagCompound compound) {
    this.rolls.clear();
    NBTTagList tagList = compound.func_150295_c("rolls", 10);
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound nbt = tagList.func_150305_b(i);
      int slot = nbt.func_74762_e("slot");
      String id = nbt.func_74779_i("id");
      this.rolls.add(new SkillRollData(slot, id));
    } 
  }
  
  public void write(NBTTagCompound compound) {
    NBTTagList tagList = new NBTTagList();
    for (SkillRollData roll : this.rolls) {
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74768_a("slot", roll.getSlot());
      nbt.func_74778_a("id", roll.getSkillId());
      tagList.func_74742_a((NBTBase)nbt);
    } 
    compound.func_74782_a("rolls", (NBTBase)tagList);
  }
  
  public boolean removeSkillRoll(int slot) {
    return this.rolls.removeIf(roll -> (roll.getSlot() == slot));
  }
  
  public void setSkillRoll(int slot, Skill skill) {
    this.rolls.removeIf(roll -> (roll.getSlot() == slot));
    this.rolls.add(new SkillRollData(slot, skill.getId()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\roll\SkillRollNBTData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */