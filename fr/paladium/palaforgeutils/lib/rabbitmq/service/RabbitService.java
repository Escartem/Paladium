package fr.paladium.palaforgeutils.lib.rabbitmq.service;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import fr.paladium.palaforgeutils.lib.rabbitmq.listener.RabbitListener;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacket;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacketBuilder;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacketType;
import fr.paladium.palaforgeutils.lib.rabbitmq.thread.RabbitThread;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class RabbitService {
  private static RabbitService instance;
  
  private final RabbitCredentials credentials;
  
  private final String queueName;
  
  private Connection connection;
  
  private Channel channel;
  
  private final Gson gson;
  
  private final List<RabbitThread> poll;
  
  private final List<RabbitListener<?>> listeners;
  
  public static RabbitService getInstance() {
    return instance;
  }
  
  public RabbitCredentials getCredentials() {
    return this.credentials;
  }
  
  public String getQueueName() {
    return this.queueName;
  }
  
  public Connection getConnection() {
    return this.connection;
  }
  
  public Channel getChannel() {
    return this.channel;
  }
  
  public Gson getGson() {
    return this.gson;
  }
  
  public List<RabbitThread> getPoll() {
    return this.poll;
  }
  
  public List<RabbitListener<?>> getListeners() {
    return this.listeners;
  }
  
  public RabbitService(RabbitCredentials credentials, String queueName) {
    instance = this;
    this.poll = new ArrayList<>();
    this.listeners = new ArrayList<>();
    this.credentials = credentials;
    this.queueName = queueName;
    this.gson = new Gson();
    initPoll();
    connect();
  }
  
  private void initPoll() {
    for (int i = 0; i < this.credentials.getWorkers(); i++)
      this.poll.add(new RabbitThread(this, i)); 
  }
  
  private boolean connect(ConnectionFactory factory) {
    try {
      this.connection = factory.newConnection();
      this.channel = this.connection.createChannel();
      return true;
    } catch (TimeoutException|java.io.IOException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  private boolean connect() {
    ConnectionFactory factory = getCredentials().toConnectionFactory();
    return connect(factory);
  }
  
  public boolean isConnected() {
    if (this.connection == null || this.channel == null || !this.connection.isOpen())
      return false; 
    return this.channel.isOpen();
  }
  
  public void registerListener(RabbitListener<?> listener) {
    this.listeners.add(listener);
  }
  
  public boolean sendBlockingPacket(RabbitPacket packet) {
    if (!isConnected() && !connect())
      return false; 
    try {
      String queueName = packet.getQueueName();
      byte[] message = packet.getMessage().getBytes(StandardCharsets.UTF_8);
      if (packet.getType() == RabbitPacketType.SIMPLE) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", Integer.valueOf(60000));
        getChannel().queueDeclare(queueName, false, false, false, args);
        getChannel().basicPublish("", queueName, null, message);
        return true;
      } 
      AMQP.BasicProperties properties = (new AMQP.BasicProperties.Builder()).expiration("60000").build();
      getChannel().exchangeDeclare(queueName, "fanout");
      getChannel().basicPublish(queueName, "", properties, message);
      return true;
    } catch (Exception error) {
      return false;
    } 
  }
  
  public boolean sendPacket(RabbitPacketType type, String queue, RabbitPacketBuilder obj) {
    String message = this.gson.toJson(obj);
    RabbitPacket packet = new RabbitPacket(queue, type, message);
    Optional<RabbitThread> optThread = getPoll().stream().filter(RabbitThread::isAvailable).findFirst();
    if (optThread.isPresent()) {
      RabbitThread thread = optThread.get();
      thread.addQueuedPacket(packet);
      synchronized (thread) {
        thread.notify();
      } 
      return true;
    } 
    RabbitThread unusedThread = null;
    for (RabbitThread thread : getPoll()) {
      if (unusedThread == null || thread.getQueueSize() < unusedThread.getQueueSize())
        unusedThread = thread; 
    } 
    if (unusedThread == null)
      return false; 
    unusedThread.addQueuedPacket(packet);
    synchronized (unusedThread) {
      unusedThread.notify();
    } 
    return true;
  }
  
  public boolean sendPacket(RabbitPacketType type, RabbitPacketBuilder obj) {
    return sendPacket(type, this.queueName, obj);
  }
  
  public void deleteListeners() {
    this.listeners.forEach(RabbitListener::stop);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\service\RabbitService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */