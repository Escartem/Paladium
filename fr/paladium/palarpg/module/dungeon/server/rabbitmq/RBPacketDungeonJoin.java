package fr.paladium.palarpg.module.dungeon.server.rabbitmq;

import com.rabbitmq.client.Delivery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import lombok.NonNull;

public class RBPacketDungeonJoin extends RabbitPacket {
  @PacketData
  private String dungeonId;
  
  @PacketData
  private String playerUUID;
  
  public RBPacketDungeonJoin() {}
  
  public RBPacketDungeonJoin(String dungeonId, String playerUUID) {
    this.dungeonId = dungeonId;
    this.playerUUID = playerUUID;
  }
  
  @SideOnly(Side.SERVER)
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    String server = Server.getServerName();
    for (DungeonWorld dungeonWorld : DungeonWorld.getAll()) {
      if (!dungeonWorld.getUniqueId().equals(this.dungeonId))
        continue; 
      if (!dungeonWorld.isMember(this.playerUUID)) {
        reply(new RBPacketDungeonJoinResult(server, RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_INVITED));
        return;
      } 
      if (dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
        reply(new RBPacketDungeonJoinResult(server, RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_WAITING));
        return;
      } 
      reply(new RBPacketDungeonJoinResult(server, RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.SUCCESS));
      return;
    } 
    reply(new RBPacketDungeonJoinResult(server, RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_FOUND));
  }
  
  public static class RBPacketDungeonJoinResult {
    private final String server;
    
    private final RBPacketDungeonJoinResultState state;
    
    public RBPacketDungeonJoinResult(@NonNull String server, @NonNull RBPacketDungeonJoinResultState state) {
      if (server == null)
        throw new NullPointerException("server is marked non-null but is null"); 
      if (state == null)
        throw new NullPointerException("state is marked non-null but is null"); 
      this.server = server;
      this.state = state;
    }
    
    public String getServer() {
      return this.server;
    }
    
    public RBPacketDungeonJoinResultState getState() {
      return this.state;
    }
    
    public enum RBPacketDungeonJoinResultState {
      NOT_FOUND, NOT_INVITED, NOT_WAITING, SUCCESS;
    }
  }
  
  public enum RBPacketDungeonJoinResultState {
    NOT_FOUND, NOT_INVITED, NOT_WAITING, SUCCESS;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\rabbitmq\RBPacketDungeonJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */