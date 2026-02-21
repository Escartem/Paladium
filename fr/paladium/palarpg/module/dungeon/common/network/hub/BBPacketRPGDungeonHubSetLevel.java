package fr.paladium.palarpg.module.dungeon.common.network.hub;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonHubSetLevel extends ForgePacket {
  @PacketData
  private String dungeon;
  
  @PacketData
  private int level;
  
  public BBPacketRPGDungeonHubSetLevel() {}
  
  public BBPacketRPGDungeonHubSetLevel(String dungeon, int level) {
    this.dungeon = dungeon;
    this.level = level;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player.field_70170_p);
    if (!optionalDungeonWorld.isPresent()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.isLeader((Entity)player) || dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
      reply(Boolean.valueOf(false));
      return;
    } 
    Optional<DungeonConfig> optionalDungeon = DungeonManager.getDungeon(this.dungeon);
    if (!optionalDungeon.isPresent()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    reply(Boolean.valueOf(true));
    dungeonWorld.setDungeon(optionalDungeon.get());
    dungeonWorld.setLevel(this.level);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubSetLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */