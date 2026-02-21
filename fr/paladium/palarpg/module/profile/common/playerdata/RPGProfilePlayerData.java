package fr.paladium.palarpg.module.profile.common.playerdata;

import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.extended.playerdata.APlayerData;
import fr.paladium.palarpg.common.extended.playerdata.PlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.palarpg.module.profile.server.manager.RPGLevelManager;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

@PlayerData("profile")
public class RPGProfilePlayerData extends APlayerData {
  public static final String ID = "profile";
  
  public void setLevel(int level) {
    this.level = level;
  }
  
  public void setTotalExp(double totalExp) {
    this.totalExp = totalExp;
  }
  
  public void setSkillPoints(int skillPoints) {
    this.skillPoints = skillPoints;
  }
  
  public String toString() {
    return "RPGProfilePlayerData(unlockedSkills=" + getUnlockedSkills() + ", level=" + getLevel() + ", totalExp=" + getTotalExp() + ", skillPoints=" + getSkillPoints() + ")";
  }
  
  public Map<String, List<SkillTreeNode>> getUnlockedSkills() {
    return this.unlockedSkills;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public double getTotalExp() {
    return this.totalExp;
  }
  
  public int getSkillPoints() {
    return this.skillPoints;
  }
  
  private final Map<String, List<SkillTreeNode>> unlockedSkills = new HashMap<>();
  
  private int level = 1;
  
  private double totalExp = 0.0D;
  
  private int skillPoints = 0;
  
  public void read(NBTTagCompound nbt, boolean saving) {
    this.totalExp = nbt.func_74769_h("totalExp");
    this.skillPoints = nbt.func_74762_e("skillPoints");
    NBTTagList keyList = nbt.func_150295_c("skill_tree_keys", 8);
    for (int i = 0; i < keyList.func_74745_c(); i++) {
      String skillTreeKey = keyList.func_150307_f(i);
      List<SkillTreeNode> nodeList = this.unlockedSkills.computeIfAbsent(skillTreeKey, key -> new LinkedList());
      if (nbt.func_74764_b("skill_tree_" + skillTreeKey)) {
        NBTTagList nbtNodeList = nbt.func_150295_c("skill_tree_" + skillTreeKey, 10);
        for (int y = 0; y < nbtNodeList.func_74745_c(); y++) {
          NBTTagCompound tag = nbtNodeList.func_150305_b(y);
          SkillTreeNode nodeObj = new SkillTreeNode();
          nodeObj.read(tag, saving);
          nodeList.add(nodeObj);
        } 
      } 
    } 
    if (getEntity() instanceof EntityPlayer)
      this.level = RPGLevelManager.getLevelFromTotalExp(this.totalExp); 
  }
  
  public void write(NBTTagCompound nbt, boolean saving) {
    nbt.func_74780_a("totalExp", this.totalExp);
    nbt.func_74768_a("skillPoints", this.skillPoints);
    NBTTagList keyList = new NBTTagList();
    this.unlockedSkills.keySet().forEach(key -> keyList.func_74742_a((NBTBase)new NBTTagString(key)));
    nbt.func_74782_a("skill_tree_keys", (NBTBase)keyList);
    this.unlockedSkills.forEach((name, unlocked) -> {
          NBTTagList list = new NBTTagList();
          unlocked.forEach(());
          nbt.func_74782_a("skill_tree_" + name, (NBTBase)list);
        });
  }
  
  public void addExp(double exp) {
    this.totalExp += exp;
    sync();
  }
  
  @NonNull
  public List<SkillTreeNode> getUnlockedSkills(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return this.unlockedSkills.computeIfAbsent(name, k -> new LinkedList());
  }
  
  public double getRemainingExp() {
    return this.totalExp - RPGLevelManager.getTotalExpForLevel(this.level);
  }
  
  public double getProgressionPercentage() {
    return getRemainingExp() / RPGLevelManager.getExpForLevel(this.level + 1);
  }
  
  public void resetSkillTree() {
    RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)getEntity(), "stats");
    if (statData == null)
      return; 
    this.unlockedSkills.forEach((tree, nodes) -> nodes.forEach(()));
    this.unlockedSkills.clear();
  }
  
  public void addSkillPoints(int skillPoint) {
    this.skillPoints += skillPoint;
  }
  
  public void unlockSkillTreeNode(@NonNull String skillTreeName, @NonNull SkillTreeNode node) {
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    addSkillPoints(-node.getCost());
    getUnlockedSkills(skillTreeName).add(node);
    if (getEntity() instanceof EntityPlayer)
      RPGSkillTreeManager.apply((EntityPlayer)getEntity()); 
  }
  
  public boolean isUnlocked(@NonNull String name, @NonNull SkillTreeNode stNode) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (stNode == null)
      throw new NullPointerException("stNode is marked non-null but is null"); 
    List<SkillTreeNode> unlockedNodes = this.unlockedSkills.get(name);
    return (unlockedNodes == null) ? false : unlockedNodes.contains(stNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\playerdata\RPGProfilePlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */