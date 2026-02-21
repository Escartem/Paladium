package fr.paladium.palaforgeutils.lib.rabbitmq.network;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketDataField;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.internal.impl.InternalRabbitCallbackPacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaforgeutils.lib.validator.Validators;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import lombok.NonNull;

public abstract class RabbitPacket implements IBufSerializable {
  private static final Cache<Class<? extends RabbitPacket>, PacketDataField[]> CACHED_FIELDS = CacheBuilder.newBuilder().expireAfterAccess(3L, TimeUnit.MINUTES).build();
  
  private static final Cache<UUID, BiConsumer<String, Object>> CALLBACK_MAP = CacheBuilder.newBuilder().expireAfterAccess(3L, TimeUnit.MINUTES).build();
  
  private RabbitNetwork network;
  
  private UUID callbackUUID;
  
  private String origin;
  
  private String target;
  
  private ByteBuf contextBuffer;
  
  private final Map<Field, Object> cachedFieldValues = new HashMap<>();
  
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
  }
  
  public void write(ByteBuf buf) {
    this.contextBuffer = buf;
    PacketSerialUtils.writeString(buf, RabbitNetwork.getPacketName((Class)getClass()));
    if (this.callbackUUID != null) {
      PacketSerialUtils.writeBoolean(buf, true);
      PacketSerialUtils.writeString(buf, UUIDUtils.toString(this.callbackUUID));
    } else {
      PacketSerialUtils.writeBoolean(buf, false);
    } 
    if (this.target != null) {
      PacketSerialUtils.writeBoolean(buf, true);
      PacketSerialUtils.writeString(buf, this.target);
    } else {
      PacketSerialUtils.writeBoolean(buf, false);
    } 
    PacketSerialUtils.writeString(buf, getNetwork().getRabbitService().getOrigin());
    Class<? extends RabbitPacket> clazz = (Class)getClass();
    if (!CACHED_FIELDS.asMap().containsKey(clazz)) {
      List<PacketDataField> fields = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent((Class)PacketData.class)) {
          if (!field.isAccessible())
            field.setAccessible(true); 
          fields.add(new PacketDataField(field, field.<PacketData>getAnnotation(PacketData.class)));
        } 
      } 
      Collections.sort(fields);
      CACHED_FIELDS.put(clazz, fields.toArray(new PacketDataField[0]));
    } 
    try {
      for (PacketDataField field : (PacketDataField[])CACHED_FIELDS.getIfPresent(clazz)) {
        Object value = null;
        if (this.cachedFieldValues.containsKey(field.getField())) {
          value = this.cachedFieldValues.get(field.getField());
        } else {
          this.cachedFieldValues.put(field.getField(), value = field.getField().get(this));
        } 
        PacketSerialUtils.write(buf, value);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void read(ByteBuf buf) {
    this.contextBuffer = buf;
    Class<? extends RabbitPacket> clazz = (Class)getClass();
    if (!CACHED_FIELDS.asMap().containsKey(clazz)) {
      List<PacketDataField> fields = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent((Class)PacketData.class)) {
          if (!field.isAccessible())
            field.setAccessible(true); 
          fields.add(new PacketDataField(field, field.<PacketData>getAnnotation(PacketData.class)));
        } 
      } 
      Collections.sort(fields);
      CACHED_FIELDS.put(clazz, fields.toArray(new PacketDataField[0]));
    } 
    ValidatorException exception = null;
    try {
      for (PacketDataField field : (PacketDataField[])CACHED_FIELDS.getIfPresent(clazz)) {
        Class<?>[] typeArray = null;
        Type genericType = field.getField().getGenericType();
        if (genericType instanceof ParameterizedType) {
          ParameterizedType parameterizedType = (ParameterizedType)genericType;
          Type[] parameterizedTypeArguments = parameterizedType.getActualTypeArguments();
          typeArray = new Class[parameterizedTypeArguments.length];
          for (int i = 0; i < parameterizedTypeArguments.length; i++)
            typeArray[i] = (Class)parameterizedTypeArguments[i]; 
        } else {
          typeArray = new Class[] { field.getField().getType() };
        } 
        Object value = PacketSerialUtils.read(buf, typeArray);
        field.getField().set(this, value);
        if (exception == null)
          exception = Validators.validate(field.getField(), value, false); 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    if (exception != null)
      throw exception; 
  }
  
  public void setNetwork(@NonNull RabbitNetwork network) {
    if (network == null)
      throw new NullPointerException("network is marked non-null but is null"); 
    this.network = network;
  }
  
  @NonNull
  public RabbitNetwork getNetwork() {
    if (this.network == null)
      this.network = RabbitNetwork.getNetwork((Class)getClass()); 
    return this.network;
  }
  
  public void send() {
    getNetwork().send(this);
  }
  
  public void send(@NonNull String target) {
    if (target == null)
      throw new NullPointerException("target is marked non-null but is null"); 
    setTarget(target);
    getNetwork().broadcast(this);
  }
  
  public void broadcast() {
    getNetwork().broadcast(this);
  }
  
  @NonNull
  public <T> RabbitPacket subscribe(@NonNull BiConsumer<String, T> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    CALLBACK_MAP.put(this.callbackUUID = UUID.randomUUID(), callback);
    return this;
  }
  
  public void reply(Object reply) {
    if (reply instanceof RabbitPacket) {
      RabbitPacket packet = (RabbitPacket)reply;
      packet.setCallbackUUID(this.callbackUUID);
      packet.send(this.origin);
      return;
    } 
    if (this.callbackUUID == null)
      throw new IllegalStateException("Cannot reply to a packet without a callback UUID."); 
    InternalRabbitCallbackPacket callbackPacket = new InternalRabbitCallbackPacket(reply);
    callbackPacket.setCallbackUUID(this.callbackUUID);
    callbackPacket.setNetwork(getNetwork());
    callbackPacket.send(this.origin);
  }
  
  public BiConsumer<String, Object> getCallback() {
    return getCallback(this.callbackUUID);
  }
  
  public static BiConsumer<String, Object> getCallback(UUID uuid) {
    if (uuid == null)
      return null; 
    return (BiConsumer<String, Object>)CALLBACK_MAP.getIfPresent(uuid);
  }
  
  public void setCallbackUUID(UUID callbackUUID) {
    this.callbackUUID = callbackUUID;
  }
  
  public void setOrigin(@NonNull String origin) {
    if (origin == null)
      throw new NullPointerException("origin is marked non-null but is null"); 
    this.origin = origin;
  }
  
  private void setTarget(String target) {
    this.target = target;
  }
  
  public ByteBuf getContextBuffer() {
    return this.contextBuffer;
  }
  
  public String getOrigin() {
    return this.origin;
  }
  
  public UUID getCallbackUUID() {
    return this.callbackUUID;
  }
  
  public boolean isSelf() {
    return this.origin.equals(getNetwork().getRabbitService().getOrigin());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\network\RabbitPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */