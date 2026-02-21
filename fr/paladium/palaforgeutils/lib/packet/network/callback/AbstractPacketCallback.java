package fr.paladium.palaforgeutils.lib.packet.network.callback;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class AbstractPacketCallback implements IMessage {
  private UUID callbackUUID;
  
  private Object data;
  
  public AbstractPacketCallback() {}
  
  public AbstractPacketCallback(UUID callbackUUID, Object data) {
    this.callbackUUID = callbackUUID;
    this.data = data;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, UUIDUtils.toString(this.callbackUUID));
    if (this.data == null) {
      PacketSerialUtils.writeString(buf, "null");
      return;
    } 
    PacketSerialUtils.writeString(buf, ClassHelper.of(this.data.getClass()).getName());
    PacketSerialUtils.write(buf, this.data);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.callbackUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
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
  
  public UUID getCallbackUUID() {
    return this.callbackUUID;
  }
  
  public Object getData() {
    return this.data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\network\callback\AbstractPacketCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */