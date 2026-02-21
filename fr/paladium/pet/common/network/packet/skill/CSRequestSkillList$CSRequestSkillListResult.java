package fr.paladium.pet.common.network.packet.skill;

import fr.paladium.pet.server.skill.skill.Skill;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSRequestSkillListResult {
  private final Map<String, Skill> skillMap;
  
  public Map<String, Skill> getSkillMap() {
    return this.skillMap;
  }
  
  public CSRequestSkillListResult(List<Skill> passiveSkills, List<Skill> activeSkills) {
    this.skillMap = new HashMap<>();
    passiveSkills.forEach(skill -> (Skill)this.skillMap.put(skill.getId(), skill));
    activeSkills.forEach(skill -> (Skill)this.skillMap.put(skill.getId(), skill));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\CSRequestSkillList$CSRequestSkillListResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */