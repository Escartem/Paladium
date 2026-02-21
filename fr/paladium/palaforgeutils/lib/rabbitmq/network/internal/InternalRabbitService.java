package fr.paladium.palaforgeutils.lib.rabbitmq.network.internal;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitCredentials;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import lombok.NonNull;

public class InternalRabbitService {
  private final RabbitCredentials credentials;
  
  private final List<InternalRabbitThread> poll;
  
  private final String origin;
  
  private final long timeout;
  
  private Channel channel;
  
  private Connection connection;
  
  public RabbitCredentials getCredentials() {
    return this.credentials;
  }
  
  public List<InternalRabbitThread> getPoll() {
    return this.poll;
  }
  
  public String getOrigin() {
    return this.origin;
  }
  
  public long getTimeout() {
    return this.timeout;
  }
  
  public Channel getChannel() {
    return this.channel;
  }
  
  public Connection getConnection() {
    return this.connection;
  }
  
  public InternalRabbitService(RabbitCredentials credentials) {
    this(credentials, 60000L);
  }
  
  public InternalRabbitService(RabbitCredentials credentials, long timeout) {
    this.credentials = credentials;
    this.poll = new ArrayList<>();
    String origin = null;
    try {
      origin = InetAddress.getLocalHost().getHostName();
    } catch (Exception exception) {}
    this.origin = origin;
    this.timeout = timeout;
    if (this.credentials == null) {
      System.err.println("[InternalRabbitService] RabbitMQ credentials are null, cannot start the service. Check palaforge-utils.json configuration.");
      return;
    } 
    if (this.origin == null) {
      System.err.println("[InternalRabbitService] Unable to get local host name.");
      return;
    } 
    if (this.credentials.getUsername() == null || this.credentials.getUsername().isEmpty()) {
      System.err.println("[InternalRabbitService] RabbitMQ username is null, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.credentials.getPassword() == null || this.credentials.getPassword().isEmpty()) {
      System.err.println("[InternalRabbitService] RabbitMQ password is null, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.credentials.getHost() == null || this.credentials.getHost().isEmpty()) {
      System.err.println("[InternalRabbitService] RabbitMQ host is null, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.credentials.getPort() <= 0) {
      System.err.println("[InternalRabbitService] RabbitMQ port is invalid, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.credentials.getVirtualHost() == null || this.credentials.getVirtualHost().isEmpty()) {
      System.err.println("[InternalRabbitService] RabbitMQ virtual host is null, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.credentials.getWorkers() <= 0) {
      System.err.println("[InternalRabbitService] RabbitMQ workers count is invalid, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (this.timeout <= 0L) {
      System.err.println("[InternalRabbitService] RabbitMQ timeout is invalid, cannot start the service. Check palaforge-utils.json configuration.");
      debug();
      return;
    } 
    if (!connect()) {
      System.err.println("[InternalRabbitService] RabbitMQ connection failed, please check your configuration.");
      debug();
      return;
    } 
    initPoll();
    System.out.println("[InternalRabbitService] RabbitMQ connection established, starting workers...");
  }
  
  private void initPoll() {
    for (int i = 0; i < this.credentials.getWorkers(); i++)
      this.poll.add(new InternalRabbitThread(i)); 
  }
  
  private boolean connect() {
    ConnectionFactory factory = getCredentials().toConnectionFactory();
    return connect(factory);
  }
  
  private boolean connect(@NonNull ConnectionFactory factory) {
    if (factory == null)
      throw new NullPointerException("factory is marked non-null but is null"); 
    try {
      this.connection = factory.newConnection();
      this.channel = this.connection.createChannel();
      return true;
    } catch (TimeoutException|java.io.IOException e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  public boolean isConnected() {
    if (this.connection == null || this.channel == null || !this.connection.isOpen())
      return false; 
    return this.channel.isOpen();
  }
  
  public boolean sendBlockingPacket(@NonNull RabbitPacket packet, boolean broadcast) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    if (!isConnected() && !connect())
      return false; 
    try {
      ByteBuf buffer = Unpooled.buffer();
      String queueName = packet.getNetwork().getQueueName() + (broadcast ? ".broadcast" : ".direct");
      packet.write(buffer);
      byte[] data = new byte[buffer.readableBytes()];
      buffer.getBytes(buffer.readerIndex(), data);
      if (!broadcast) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", Long.valueOf(this.timeout));
        getChannel().queueDeclare(queueName, false, false, false, args);
        getChannel().basicPublish("", queueName, null, data);
        return true;
      } 
      AMQP.BasicProperties properties = (new AMQP.BasicProperties.Builder()).expiration(String.valueOf(this.timeout)).build();
      getChannel().exchangeDeclare(queueName, "fanout");
      getChannel().basicPublish(queueName, "", properties, data);
      return true;
    } catch (Exception error) {
      error.printStackTrace();
      return false;
    } 
  }
  
  public boolean sendPacket(@NonNull RabbitPacket packet, boolean broadcast) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    Optional<InternalRabbitThread> optThread = getPoll().stream().filter(InternalRabbitThread::isAvailable).findFirst();
    Runnable runnable = () -> sendBlockingPacket(packet, broadcast);
    if (optThread.isPresent()) {
      InternalRabbitThread thread = optThread.get();
      thread.addQueuedPacket(runnable);
      synchronized (thread) {
        thread.notify();
      } 
      return true;
    } 
    InternalRabbitThread unusedThread = null;
    for (InternalRabbitThread thread : getPoll()) {
      if (unusedThread == null || thread.getQueueSize() < unusedThread.getQueueSize())
        unusedThread = thread; 
    } 
    if (unusedThread == null)
      return false; 
    unusedThread.addQueuedPacket(runnable);
    synchronized (unusedThread) {
      unusedThread.notify();
    } 
    return true;
  }
  
  private void debug() {
    System.err.println("[InternalRabbitService] RabbitMQ credentials:");
    System.err.println("  - Host: " + this.credentials.getHost());
    System.err.println("  - Port: " + this.credentials.getPort());
    System.err.println("  - Virtual Host: " + this.credentials.getVirtualHost());
    System.err.println("  - Username: " + this.credentials.getUsername());
    System.err.println("  - Password: " + this.credentials.getPassword().substring(0, 3) + "*********");
    System.err.println("  - Workers: " + this.credentials.getWorkers());
    System.err.println("  - Timeout: " + this.timeout);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\network\internal\InternalRabbitService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */