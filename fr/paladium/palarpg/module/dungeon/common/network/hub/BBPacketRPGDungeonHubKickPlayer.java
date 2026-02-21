package fr.paladium.palarpg.module.dungeon.common.network.hub;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonHubKickPlayer extends ForgePacket {
  @PacketData
  private String target;
  
  public BBPacketRPGDungeonHubKickPlayer() {}
  
  public BBPacketRPGDungeonHubKickPlayer(String target) {
    this.target = target;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player.field_70170_p);
    if (!optionalDungeonWorld.isPresent()) {
      reply(BBPacketDungeonKickResult.NOT_DUNGEON);
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
      reply(BBPacketDungeonKickResult.NOT_WAITING);
      return;
    } 
    if (!dungeonWorld.isLeader((Entity)player)) {
      reply(BBPacketDungeonKickResult.NOT_LEADER);
      return;
    } 
    OfflinePlayer.load(this.target).thenAccept(target -> {
          if (!dungeonWorld.isMember(target.getUuidString())) {
            reply(BBPacketDungeonKickResult.NOT_MEMBER);
            return;
          } 
          dungeonWorld.removePlayer(target.getUuidString(), true);
          reply(BBPacketDungeonKickResult.SUCCESS);
        }).exceptionally(e -> {
          e.printStackTrace();
          reply(BBPacketDungeonKickResult.ERROR);
          return null;
        });
  }
  
  public enum BBPacketDungeonKickResult {
    NOT_DUNGEON, NOT_WAITING, NOT_LEADER, NOT_MEMBER, SUCCESS, ERROR;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubKickPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */