package fr.paladium.palarpg.module.dungeon.common.network.hub;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonInvite;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonHubInvitePlayer extends ForgePacket {
  @PacketData
  private String target;
  
  public BBPacketRPGDungeonHubInvitePlayer() {}
  
  public BBPacketRPGDungeonHubInvitePlayer(String target) {
    this.target = target;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player.field_70170_p);
    if (!optionalDungeonWorld.isPresent()) {
      reply(BBPacketDungeonInvitePlayerResult.NOT_DUNGEON);
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.isLeader((Entity)player)) {
      reply(BBPacketDungeonInvitePlayerResult.NOT_LEADER);
      return;
    } 
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
      reply(BBPacketDungeonInvitePlayerResult.NOT_WAITING);
      return;
    } 
    if (dungeonWorld.getPlayers().size() >= 4) {
      reply(BBPacketDungeonInvitePlayerResult.DUNGEON_FULL);
      return;
    } 
    OfflinePlayer.load(this.target).thenAccept(target -> {
          if (dungeonWorld.isMember(target.getUuidString())) {
            reply(BBPacketDungeonInvitePlayerResult.ALREADY_MEMBER);
            return;
          } 
          dungeonWorld.addPlayer(target);
          (new RBPacketDungeonInvite(dungeonWorld.getUniqueId(), OfflinePlayer.of((EntityPlayer)player), target.getUuid())).broadcast();
          reply(BBPacketDungeonInvitePlayerResult.SUCCESS);
        }).exceptionally(e -> {
          e.printStackTrace();
          reply(BBPacketDungeonInvitePlayerResult.ERROR);
          return null;
        });
  }
  
  public enum BBPacketDungeonInvitePlayerResult {
    NOT_DUNGEON, NOT_LEADER, NOT_WAITING, DUNGEON_FULL, ALREADY_MEMBER, SUCCESS, ERROR;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubInvitePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */