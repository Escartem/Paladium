package fr.paladium.palarpg.module.dungeon.common.network.global;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonGlobalGetDungeons extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    reply(new BBPacketRPGDungeonGlobalGetDungeonsReply(DungeonManager.getDungeons()));
  }
  
  public class BBPacketRPGDungeonGlobalGetDungeonsReply {
    private final List<DungeonConfig> dungeons;
    
    public BBPacketRPGDungeonGlobalGetDungeonsReply(List<DungeonConfig> dungeons) {
      this.dungeons = dungeons;
    }
    
    public List<DungeonConfig> getDungeons() {
      return this.dungeons;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\global\BBPacketRPGDungeonGlobalGetDungeons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */