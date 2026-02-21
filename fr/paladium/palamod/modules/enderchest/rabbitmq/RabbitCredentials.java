package fr.paladium.palamod.modules.enderchest.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitCredentials {
  private final String hostname;
  
  private final int port;
  
  private final String username;
  
  private final String password;
  
  private final String virtualHost;
  
  private final int workers;
  
  public RabbitCredentials(String hostname, int port, String username, String password, String virtualHost, int workers) {
    this.hostname = hostname;
    this.port = port;
    this.username = username;
    this.password = password;
    this.virtualHost = virtualHost;
    this.workers = workers;
  }
  
  public String getHostname() {
    return this.hostname;
  }
  
  public int getPort() {
    return this.port;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getVirtualHost() {
    return this.virtualHost;
  }
  
  public int getWorkers() {
    return this.workers;
  }
  
  public RabbitCredentials(String hostname, String username, String password, String virtualHost, int workers) {
    this(hostname, 5672, username, password, virtualHost, workers);
  }
  
  public ConnectionFactory toConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(this.hostname);
    factory.setPort(this.port);
    factory.setUsername(this.username);
    factory.setPassword(this.password);
    factory.setRequestedHeartbeat(60);
    factory.setConnectionTimeout(30000);
    factory.setHandshakeTimeout(30000);
    factory.setWorkPoolTimeout(30000);
    factory.setAutomaticRecoveryEnabled(true);
    return factory;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\RabbitCredentials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */