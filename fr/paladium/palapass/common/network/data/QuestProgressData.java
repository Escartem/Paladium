package fr.paladium.palapass.common.network.data;

import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import java.util.Objects;
import net.minecraft.nbt.NBTTagCompound;

public class QuestProgressData {
  private String uuid;
  
  private EnumQuestsDuration duration;
  
  private boolean completed;
  
  private int progress;
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public void setDuration(EnumQuestsDuration duration) {
    this.duration = duration;
  }
  
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  
  public void setProgress(int progress) {
    this.progress = progress;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public EnumQuestsDuration getDuration() {
    return this.duration;
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
  
  public int getProgress() {
    return this.progress;
  }
  
  public void readToNBT(NBTTagCompound compound) {
    this.uuid = compound.func_74779_i("uuid");
    this.duration = EnumQuestsDuration.valueOf(compound.func_74779_i("duration"));
    this.completed = compound.func_74767_n("completed");
    this.progress = compound.func_74762_e("progress");
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("uuid", this.uuid);
    compound.func_74778_a("duration", this.duration.name());
    compound.func_74757_a("completed", this.completed);
    compound.func_74768_a("progress", this.progress);
    return compound;
  }
  
  public void add(int amount) {
    this.progress += amount;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    QuestProgressData that = (QuestProgressData)o;
    return Objects.equals(this.uuid, that.uuid);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.uuid });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\data\QuestProgressData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */