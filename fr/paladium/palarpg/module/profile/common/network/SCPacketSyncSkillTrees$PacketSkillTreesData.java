package fr.paladium.palarpg.module.profile.common.network;

import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import java.util.Map;
import lombok.NonNull;

class PacketSkillTreesData {
  @NonNull
  private final Map<String, SkillTree> data;
  
  public PacketSkillTreesData(Map<String, SkillTree> data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
  }
  
  @NonNull
  public Map<String, SkillTree> getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\network\SCPacketSyncSkillTrees$PacketSkillTreesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */