package fr.paladium.pet.server.config.skill;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.PetServerProxy;
import fr.paladium.pet.server.commands.skill.SkillSubCommand;
import fr.paladium.pet.server.skill.SkillManager;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@ConfigFile(path = "pet/skill_config.json", blocking = true)
public class SkillConfig implements IConfig {
  public static final String ACTIVE_SKILL_PACKAGE = "fr.paladium.pet.skill.listener.active";
  
  public static final String PASSIVE_SKILL_PACKAGE = "fr.paladium.pet.skill.listener.passive";
  
  private int maxActiveSkills;
  
  private long changeSkillCooldown;
  
  private List<Skill> activeSkills;
  
  private List<Skill> passiveSkills;
  
  private HashMap<String, String> luckyMinerMap;
  
  public void setMaxActiveSkills(int maxActiveSkills) {
    this.maxActiveSkills = maxActiveSkills;
  }
  
  public void setChangeSkillCooldown(long changeSkillCooldown) {
    this.changeSkillCooldown = changeSkillCooldown;
  }
  
  public void setActiveSkills(List<Skill> activeSkills) {
    this.activeSkills = activeSkills;
  }
  
  public void setPassiveSkills(List<Skill> passiveSkills) {
    this.passiveSkills = passiveSkills;
  }
  
  public void setLuckyMinerMap(HashMap<String, String> luckyMinerMap) {
    this.luckyMinerMap = luckyMinerMap;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SkillConfig))
      return false; 
    SkillConfig other = (SkillConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (getMaxActiveSkills() != other.getMaxActiveSkills())
      return false; 
    if (getChangeSkillCooldown() != other.getChangeSkillCooldown())
      return false; 
    Object<Skill> this$activeSkills = (Object<Skill>)getActiveSkills(), other$activeSkills = (Object<Skill>)other.getActiveSkills();
    if ((this$activeSkills == null) ? (other$activeSkills != null) : !this$activeSkills.equals(other$activeSkills))
      return false; 
    Object<Skill> this$passiveSkills = (Object<Skill>)getPassiveSkills(), other$passiveSkills = (Object<Skill>)other.getPassiveSkills();
    if ((this$passiveSkills == null) ? (other$passiveSkills != null) : !this$passiveSkills.equals(other$passiveSkills))
      return false; 
    Object<String, String> this$luckyMinerMap = (Object<String, String>)getLuckyMinerMap(), other$luckyMinerMap = (Object<String, String>)other.getLuckyMinerMap();
    return !((this$luckyMinerMap == null) ? (other$luckyMinerMap != null) : !this$luckyMinerMap.equals(other$luckyMinerMap));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SkillConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getMaxActiveSkills();
    long $changeSkillCooldown = getChangeSkillCooldown();
    result = result * 59 + (int)($changeSkillCooldown >>> 32L ^ $changeSkillCooldown);
    Object<Skill> $activeSkills = (Object<Skill>)getActiveSkills();
    result = result * 59 + (($activeSkills == null) ? 43 : $activeSkills.hashCode());
    Object<Skill> $passiveSkills = (Object<Skill>)getPassiveSkills();
    result = result * 59 + (($passiveSkills == null) ? 43 : $passiveSkills.hashCode());
    Object<String, String> $luckyMinerMap = (Object<String, String>)getLuckyMinerMap();
    return result * 59 + (($luckyMinerMap == null) ? 43 : $luckyMinerMap.hashCode());
  }
  
  public String toString() {
    return "SkillConfig(maxActiveSkills=" + getMaxActiveSkills() + ", changeSkillCooldown=" + getChangeSkillCooldown() + ", activeSkills=" + getActiveSkills() + ", passiveSkills=" + getPassiveSkills() + ", luckyMinerMap=" + getLuckyMinerMap() + ")";
  }
  
  public int getMaxActiveSkills() {
    return this.maxActiveSkills;
  }
  
  public long getChangeSkillCooldown() {
    return this.changeSkillCooldown;
  }
  
  public List<Skill> getActiveSkills() {
    return this.activeSkills;
  }
  
  public List<Skill> getPassiveSkills() {
    return this.passiveSkills;
  }
  
  public HashMap<String, String> getLuckyMinerMap() {
    return this.luckyMinerMap;
  }
  
  public static SkillConfig get() {
    return PetServerProxy.getInstance().getSkillConfig();
  }
  
  public boolean isValid() {
    return true;
  }
  
  public void onLoaded() {
    SkillManager manager = SkillManager.getInstance();
    manager.updateSkills();
    updateSubCommand();
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
  
  private void updateSubCommand() {
    SkillSubCommand sub = SkillSubCommand.getInstance();
    if (sub == null)
      return; 
    String[] args = (String[])getAllSkills().stream().map(Skill::getId).toArray(x$0 -> new String[x$0]);
    sub.updateSkills(args);
  }
  
  public List<Skill> getAllSkills() {
    List<Skill> skills = new ArrayList<>(this.activeSkills);
    skills.addAll(this.passiveSkills);
    return skills;
  }
  
  public HashMap<SkillType, Integer> getSkillCount(PetPlayer pet) {
    HashMap<Integer, SkillData> map = pet.getSkillData().getSkills();
    HashMap<SkillType, Integer> result = new HashMap<>();
    for (SkillData data : map.values()) {
      Optional<Skill> skill = findSkillById(data.getSkillId());
      if (!skill.isPresent())
        continue; 
      SkillType type = ((Skill)skill.get()).getType();
      result.put(type, Integer.valueOf(((Integer)result.getOrDefault(type, Integer.valueOf(0))).intValue() + 1));
    } 
    return result;
  }
  
  public ItemStack getMinerOutput(World world, int x, int y, int z, Block block) {
    try {
      String unlocalizedName = block.func_149739_a();
      String metadata = String.valueOf(world.func_72805_g(x, y, z));
      String key = unlocalizedName + ":" + metadata;
      if (!this.luckyMinerMap.containsKey(key))
        return null; 
      String[] split = ((String)this.luckyMinerMap.get(key)).split(":");
      Block b = Block.func_149684_b(split[0]);
      int meta = Integer.parseInt(split[1]);
      ItemStack stack = new ItemStack(b, meta);
      return stack;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } 
  }
  
  public Skill getPassive(String id) {
    return this.passiveSkills.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
  }
  
  public Skill getActive(String id) {
    return this.activeSkills.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
  }
  
  public Optional<Skill> findSkillById(String id) {
    Optional<Skill> skill = this.activeSkills.stream().filter(s -> s.getId().equals(id)).findFirst();
    if (skill.isPresent())
      return skill; 
    return this.passiveSkills.stream().filter(s -> s.getId().equals(id)).findFirst();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\config\skill\SkillConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */