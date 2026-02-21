package fr.paladium.palaforgeutils.lib.rabbitmq.network;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.internal.InternalRabbitService;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.internal.impl.InternalRabbitCallbackPacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;

public class RabbitNetwork {
  private static Map<String, Class<? extends RabbitPacket>> packetByName = new ConcurrentHashMap<>();
  
  private static Map<Class<? extends RabbitPacket>, String> packetByClass = new ConcurrentHashMap<>();
  
  private static Map<Class<? extends RabbitPacket>, RabbitNetwork> networkByPacket = new ConcurrentHashMap<>();
  
  private final String queueName;
  
  private final InternalRabbitService rabbitService;
  
  public String getQueueName() {
    return this.queueName;
  }
  
  public InternalRabbitService getRabbitService() {
    return this.rabbitService;
  }
  
  private RabbitNetwork(@NonNull String queueName, @NonNull InternalRabbitService rabbitService) {
    if (queueName == null)
      throw new NullPointerException("queueName is marked non-null but is null"); 
    if (rabbitService == null)
      throw new NullPointerException("rabbitService is marked non-null but is null"); 
    this.queueName = queueName;
    this.rabbitService = rabbitService;
    if (!this.rabbitService.isConnected()) {
      System.err.println("[PalaForgeUtils][RabbitMQ] Unable to start RabbitMQ network for queue '" + queueName + "' : RabbitMQ service is not connected.");
      return;
    } 
    try {
      startListener();
    } catch (IOException e) {
      System.err.println("[PalaForgeUtils][RabbitMQ] Error while starting listener for queue '" + queueName + "' : " + e.getMessage());
      e.printStackTrace();
    } 
  }
  
  @NonNull
  public static RabbitNetwork createNetwork(@NonNull String queueName) {
    if (queueName == null)
      throw new NullPointerException("queueName is marked non-null but is null"); 
    return new RabbitNetwork(queueName, PalaForgeUtilsMod.getServer().getRabbitService());
  }
  
  @NonNull
  public static RabbitNetwork createNetwork(@NonNull String queueName, @NonNull InternalRabbitService rabbitService) {
    if (queueName == null)
      throw new NullPointerException("queueName is marked non-null but is null"); 
    if (rabbitService == null)
      throw new NullPointerException("rabbitService is marked non-null but is null"); 
    return new RabbitNetwork(queueName, rabbitService);
  }
  
  public static RabbitPacket getPacket(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    try {
      Class<? extends RabbitPacket> clazz = packetByName.get(name);
      if (clazz == null) {
        System.err.println("[PalaForgeUtils][RabbitMQ] Packet not found by name '" + name + "'");
        return null;
      } 
      RabbitPacket packet = clazz.newInstance();
      packet.setNetwork(networkByPacket.get(clazz));
      return packet;
    } catch (Exception e) {
      System.err.println("[PalaForgeUtils][RabbitMQ] Error while creating packet by name '" + name + "'");
      e.printStackTrace();
      return null;
    } 
  }
  
  public static RabbitNetwork getNetwork(@NonNull Class<? extends RabbitPacket> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    return networkByPacket.get(clazz);
  }
  
  public static String getPacketName(@NonNull Class<? extends RabbitPacket> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (clazz.equals(InternalRabbitCallbackPacket.class))
      return InternalRabbitCallbackPacket.getPacketName(); 
    return packetByClass.get(clazz);
  }
  
  @NonNull
  public RabbitNetwork registerPacket(@NonNull Class<? extends RabbitPacket> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    String packetName = clazz.getName();
    packetByName.put(packetName, clazz);
    packetByClass.put(clazz, packetName);
    networkByPacket.put(clazz, this);
    return this;
  }
  
  @NonNull
  public RabbitNetwork send(@NonNull RabbitPacket packet) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    this.rabbitService.sendPacket(packet, false);
    return this;
  }
  
  @NonNull
  public RabbitNetwork broadcast(@NonNull RabbitPacket packet) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    this.rabbitService.sendPacket(packet, true);
    return this;
  }
  
  private void startListener() throws IOException {
    String directQueue = this.queueName + ".direct";
    String broadcastQueue = this.queueName + ".broadcast";
    DeliverCallback deliverCallback = (consumerTag, delivery) -> onReceive(delivery);
    Map<String, Object> args = new HashMap<>();
    args.put("x-message-ttl", Long.valueOf(this.rabbitService.getTimeout()));
    this.rabbitService.getChannel().queueDeclare(directQueue, false, false, false, args);
    this.rabbitService.getChannel().basicConsume(directQueue, true, deliverCallback, consumerTag -> {
        
        });
    String internalQueueName = this.rabbitService.getChannel().queueDeclare().getQueue();
    this.rabbitService.getChannel().exchangeDeclare(broadcastQueue, "fanout");
    this.rabbitService.getChannel().queueBind(internalQueueName, broadcastQueue, "");
    this.rabbitService.getChannel().basicConsume(internalQueueName, true, deliverCallback, consumerTag -> {
        
        });
  }
  
  private void onReceive(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    ByteBuf buffer = Unpooled.wrappedBuffer(delivery.getBody());
    String packetName = PacketSerialUtils.readString(buffer);
    UUID callbackUUID = PacketSerialUtils.readBoolean(buffer) ? UUIDUtils.from(PacketSerialUtils.readString(buffer)) : null;
    String target = PacketSerialUtils.readBoolean(buffer) ? PacketSerialUtils.readString(buffer) : null;
    String origin = PacketSerialUtils.readString(buffer);
    if (target != null && !target.equals(this.rabbitService.getOrigin()))
      return; 
    boolean isCallback = (callbackUUID != null && InternalRabbitCallbackPacket.isCallbackPacket(packetName));
    RabbitPacket packet = isCallback ? (RabbitPacket)new InternalRabbitCallbackPacket() : getPacket(packetName);
    if (packet == null)
      return; 
    packet.setNetwork(this);
    packet.setCallbackUUID(callbackUUID);
    packet.setOrigin(origin);
    packet.read(buffer);
    packet.process(delivery);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\network\RabbitNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */