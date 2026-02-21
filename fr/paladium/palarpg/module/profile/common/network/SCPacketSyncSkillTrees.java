package fr.paladium.palarpg.module.profile.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import java.util.Map;
import lombok.NonNull;

public class SCPacketSyncSkillTrees extends ForgePacket {
  @PacketData
  private final PacketSkillTreesData packetData = new PacketSkillTreesData(RPGSkillTreeManager.getSkillTrees());
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    RPGSkillTreeManager.setSkillTrees(this.packetData.getData());
  }
  
  private class PacketSkillTreesData {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\common\network\SCPacketSyncSkillTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */