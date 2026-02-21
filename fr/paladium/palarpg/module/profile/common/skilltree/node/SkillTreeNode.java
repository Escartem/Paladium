package fr.paladium.palarpg.module.profile.common.skilltree.node;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palarpg.common.extended.playerdata.INbt;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class SkillTreeNode implements INbt {
  private String name;
  
  private EnumStats stat;
  
  private float value;
  
  private StatMutationType type;
  
  @SerializedName("required")
  private List<String> requiredLevels;
  
  private int cost;
  
  private NodePosition position;
  
  public String toString() {
    return "SkillTreeNode(name=" + getName() + ", stat=" + getStat() + ", value=" + getValue() + ", type=" + getType() + ", requiredLevels=" + getRequiredLevels() + ", cost=" + getCost() + ", position=" + getPosition() + ")";
  }
  
  public String getName() {
    return this.name;
  }
  
  public EnumStats getStat() {
    return this.stat;
  }
  
  public float getValue() {
    return this.value;
  }
  
  public StatMutationType getType() {
    return this.type;
  }
  
  public List<String> getRequiredLevels() {
    return this.requiredLevels;
  }
  
  public int getCost() {
    return this.cost;
  }
  
  public NodePosition getPosition() {
    return this.position;
  }
  
  public boolean equals(Object obj) {
    if (obj == null)
      return false; 
    if (obj instanceof SkillTreeNode) {
      SkillTreeNode node = (SkillTreeNode)obj;
      boolean equals = this.name.equals(node.getName());
      equals = (equals && this.cost == node.getCost());
      equals = (equals && this.stat == node.getStat());
      equals = (equals && this.type == node.getType());
      equals = (equals && this.value == node.getValue());
      equals = (equals && this.position.equals(node.getPosition()));
      if (this.requiredLevels == null) {
        equals = (equals && node.getRequiredLevels() == null);
      } else {
        equals = (equals && this.requiredLevels.equals(node.getRequiredLevels()));
      } 
      return equals;
    } 
    return false;
  }
  
  public void read(NBTTagCompound nbt, boolean saving) {
    this.name = nbt.func_74779_i("name");
    this.stat = EnumStats.fromOrdinal(nbt.func_74762_e("stat"));
    this.value = nbt.func_74760_g("value");
    this.type = StatMutationType.fromOrdinal(nbt.func_74762_e("type"));
    if (nbt.func_74764_b("required")) {
      this.requiredLevels = new ArrayList<>();
      NBTTagList rlArray = nbt.func_150295_c("required", 8);
      for (int i = 0; i < rlArray.func_74745_c(); i++)
        this.requiredLevels.add(rlArray.func_150307_f(i)); 
    } 
    this.cost = nbt.func_74762_e("cost");
    this.position = new NodePosition(nbt.func_74762_e("posX"), nbt.func_74762_e("posY"));
  }
  
  public void write(NBTTagCompound nbt, boolean saving) {
    nbt.func_74778_a("name", this.name);
    nbt.func_74768_a("stat", this.stat.ordinal());
    nbt.func_74776_a("value", this.value);
    nbt.func_74768_a("type", this.type.ordinal());
    if (this.requiredLevels != null) {
      NBTTagList required = new NBTTagList();
      this.requiredLevels.forEach(req -> required.func_74742_a((NBTBase)new NBTTagString(req)));
      nbt.func_74782_a("required", (NBTBase)required);
    } 
    nbt.func_74768_a("cost", this.cost);
    nbt.func_74768_a("posX", this.position.getX());
    nbt.func_74768_a("posY", this.position.getY());
  }
  
  public StatCapabilityMutator<Object> getMutator() {
    return StatCapabilityMutator.create().setId(this.name).setMutationType(this.type).setValue(Float.valueOf(this.value)).setSaved(false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\skilltree\node\SkillTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */