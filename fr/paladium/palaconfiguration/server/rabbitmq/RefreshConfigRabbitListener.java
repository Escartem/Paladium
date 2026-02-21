package fr.paladium.palaconfiguration.server.rabbitmq;

import fr.paladium.palaconfiguration.server.ServerProxy;
import fr.paladium.palaconfiguration.server.dto.Environment;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import fr.paladium.palaforgeutils.lib.rabbitmq.listener.RabbitListener;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacketType;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitService;

public class RefreshConfigRabbitListener extends RabbitListener<RefreshConfigRabbitPacket> {
  private final ConfigurationManager manager;
  
  public RefreshConfigRabbitListener(RabbitService service) {
    super(service, RabbitPacketType.PUBLISH, service.getQueueName(), RefreshConfigRabbitPacket.class);
    this.manager = ServerProxy.getInstance().getManager();
  }
  
  public void onReceive(RefreshConfigRabbitPacket packet) {
    if (packet == null)
      return; 
    Environment currentEnvironment = ServerProxy.getInstance().getConfig().getEnvironment();
    if (!packet.getEnvironment().equals(currentEnvironment))
      return; 
    IConfig config = (IConfig)this.manager.getConfigs().get(packet.getConfigName());
    if (config == null)
      return; 
    ConfigFile configFile = config.getClass().<ConfigFile>getAnnotation(ConfigFile.class);
    if (configFile == null)
      return; 
    if (configFile.local()) {
      this.manager.reloadLocalConfig(config, configFile, false, null);
    } else {
      this.manager.reloadApiConfig(config, configFile, false, false, null);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\rabbitmq\RefreshConfigRabbitListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */