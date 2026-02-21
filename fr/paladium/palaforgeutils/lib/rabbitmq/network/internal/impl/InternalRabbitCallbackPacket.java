package fr.paladium.palaforgeutils.lib.rabbitmq.network.internal.impl;

import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import io.netty.buffer.ByteBuf;
import java.util.function.BiConsumer;
import lombok.NonNull;

public final class InternalRabbitCallbackPacket extends RabbitPacket {
  public InternalRabbitCallbackPacket() {}
  
  public InternalRabbitCallbackPacket(Object data) {
    this.data = data;
  }
  
  private static final String CLASS_NAME = InternalRabbitCallbackPacket.class.getSimpleName();
  
  private Object data;
  
  public void write(ByteBuf buf) {
    super.write(buf);
    if (this.data == null) {
      PacketSerialUtils.writeString(buf, "null");
      return;
    } 
    PacketSerialUtils.writeString(buf, ClassHelper.of(this.data.getClass()).getName());
    PacketSerialUtils.write(buf, this.data);
  }
  
  public void read(ByteBuf buf) {
    super.read(buf);
    try {
      String className = PacketSerialUtils.readString(buf);
      if ("null".equals(className)) {
        this.data = null;
        return;
      } 
      this.data = PacketSerialUtils.read(buf, new Class[] { Class.forName(className) });
    } catch (Exception|NoClassDefFoundError e) {
      e.printStackTrace();
    } 
  }
  
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    if (getCallbackUUID() == null)
      return; 
    BiConsumer<String, Object> callback = RabbitPacket.getCallback(getCallbackUUID());
    if (callback == null)
      return; 
    callback.accept(getOrigin(), this.data);
  }
  
  public static boolean isCallbackPacket(@NonNull String packetName) {
    if (packetName == null)
      throw new NullPointerException("packetName is marked non-null but is null"); 
    return packetName.equals(CLASS_NAME);
  }
  
  @NonNull
  public static String getPacketName() {
    return CLASS_NAME;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\network\internal\impl\InternalRabbitCallbackPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */