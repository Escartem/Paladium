package fr.paladium.pet.common.network.packet.skill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.PalaPetMod;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import fr.paladium.pet.server.PetServerProxy;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSRequestSkillList extends ForgePacket {
  @PacketData
  private List<SkillRollSlotData> data;
  
  public CSRequestSkillList() {}
  
  public CSRequestSkillList(List<SkillRollSlotData> data) {
    this.data = data;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    List<Skill> passiveSkills = ((PetServerProxy)PalaPetMod.proxy).getSkillConfig().getPassiveSkills();
    List<Skill> activeSkills = ((PetServerProxy)PalaPetMod.proxy).getSkillConfig().getActiveSkills();
    reply(new CSRequestSkillListResult(passiveSkills, activeSkills));
  }
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\CSRequestSkillList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */