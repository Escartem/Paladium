package fr.paladium.palamod.modules.factions.rabbit.packets;

import fr.paladium.palamod.modules.factions.dto.leveling.FactionXpChangeReason;
import fr.paladium.palamod.modules.factions.rabbit.RabbitPacketBuilder;
import fr.paladium.palamod.modules.factions.rabbit.RabbitPacketType;
import fr.paladium.palamod.modules.factions.rabbit.RabbitQueue;
import java.util.UUID;

public class PlayerAddXpPacket extends RabbitPacketBuilder {
  private final UUID playerUuid;
  
  private final long amount;
  
  private final FactionXpChangeReason reason;
  
  public PlayerAddXpPacket(UUID playerUuid, long amount, FactionXpChangeReason reason) {
    this.playerUuid = playerUuid;
    this.amount = amount;
    this.reason = reason;
  }
  
  public UUID getPlayerUuid() {
    return this.playerUuid;
  }
  
  public long getAmount() {
    return this.amount;
  }
  
  public FactionXpChangeReason getReason() {
    return this.reason;
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
  
  public RabbitQueue getPacketQueue() {
    return RabbitQueue.PLAYER_ADD_XP;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\rabbit\packets\PlayerAddXpPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */