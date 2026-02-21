package fr.paladium.palamod.modules.factions.rabbit.packets;

import fr.paladium.palamod.modules.factions.rabbit.RabbitPacketBuilder;
import fr.paladium.palamod.modules.factions.rabbit.RabbitPacketType;
import fr.paladium.palamod.modules.factions.rabbit.RabbitQueue;
import java.util.UUID;

public class PlayerAddEloPacket extends RabbitPacketBuilder {
  private final UUID playerUuid;
  
  private final long amount;
  
  public PlayerAddEloPacket(UUID playerUuid, long amount) {
    this.playerUuid = playerUuid;
    this.amount = amount;
  }
  
  public UUID getPlayerUuid() {
    return this.playerUuid;
  }
  
  public long getAmount() {
    return this.amount;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.PLAYER_ADD_ELO;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\rabbit\packets\PlayerAddEloPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */