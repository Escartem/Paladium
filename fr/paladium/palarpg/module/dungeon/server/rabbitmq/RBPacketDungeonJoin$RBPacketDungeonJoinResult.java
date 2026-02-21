package fr.paladium.palarpg.module.dungeon.server.rabbitmq;

import lombok.NonNull;

public class RBPacketDungeonJoinResult {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\rabbitmq\RBPacketDungeonJoin$RBPacketDungeonJoinResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */