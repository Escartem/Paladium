package fr.paladium.palaconfiguration.server.rabbitmq;

import fr.paladium.palaconfiguration.server.ServerProxy;
import fr.paladium.palaconfiguration.server.dto.Environment;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacketBuilder;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacketType;

public class RefreshConfigRabbitPacket extends RabbitPacketBuilder {
  private final String configName;
  
  private final Environment environment;
  
  public String getConfigName() {
    return this.configName;
  }
  
  public Environment getEnvironment() {
    return this.environment;
  }
  
  public RefreshConfigRabbitPacket(String configName) {
    this.configName = configName;
    this.environment = ServerProxy.getInstance().getConfig().getEnvironment();
  }
  
  public RabbitPacketType getPacketType() {
    return RabbitPacketType.PUBLISH;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\rabbitmq\RefreshConfigRabbitPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */