package fr.paladium.palaforgeutils.lib.rabbitmq.service;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitCredentials {
  private final String host;
  
  private final int port;
  
  private final String username;
  
  private final String password;
  
  private final String virtualHost;
  
  private final int workers;
  
  private final int requestHeartbeat;
  
  private final int connectionTimeout;
  
  private final int handshakeTimeout;
  
  private final int workPoolTimeout;
  
  private final boolean automaticRecoveryEnabled;
  
  public RabbitCredentials(String host, int port, String username, String password, String virtualHost, int workers, int requestHeartbeat, int connectionTimeout, int handshakeTimeout, int workPoolTimeout, boolean automaticRecoveryEnabled) {
    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
    this.virtualHost = virtualHost;
    this.workers = workers;
    this.requestHeartbeat = requestHeartbeat;
    this.connectionTimeout = connectionTimeout;
    this.handshakeTimeout = handshakeTimeout;
    this.workPoolTimeout = workPoolTimeout;
    this.automaticRecoveryEnabled = automaticRecoveryEnabled;
  }
  
  public String getHost() {
    return this.host;
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
  
  public int getRequestHeartbeat() {
    return this.requestHeartbeat;
  }
  
  public int getConnectionTimeout() {
    return this.connectionTimeout;
  }
  
  public int getHandshakeTimeout() {
    return this.handshakeTimeout;
  }
  
  public int getWorkPoolTimeout() {
    return this.workPoolTimeout;
  }
  
  public boolean isAutomaticRecoveryEnabled() {
    return this.automaticRecoveryEnabled;
  }
  
  public RabbitCredentials() {
    this.host = "127.0.0.1";
    this.port = 5672;
    this.username = "guest";
    this.password = "guest";
    this.virtualHost = "/";
    this.workers = 16;
    this.requestHeartbeat = 60;
    this.connectionTimeout = 30000;
    this.handshakeTimeout = 30000;
    this.workPoolTimeout = 30000;
    this.automaticRecoveryEnabled = true;
  }
  
  public ConnectionFactory toConnectionFactory() {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(this.host);
    factory.setPort(this.port);
    factory.setUsername(this.username);
    factory.setPassword(this.password);
    factory.setRequestedHeartbeat(this.requestHeartbeat);
    factory.setConnectionTimeout(this.connectionTimeout);
    factory.setHandshakeTimeout(this.handshakeTimeout);
    factory.setWorkPoolTimeout(this.workPoolTimeout);
    factory.setAutomaticRecoveryEnabled(this.automaticRecoveryEnabled);
    return factory;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\service\RabbitCredentials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */