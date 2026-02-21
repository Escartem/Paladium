package fr.paladium.palaforgeutils.lib.sidedaction.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import io.netty.buffer.ByteBuf;

public class CSExecuteClientAction implements IMessage {
  private String uuid;
  
  private Object result;
  
  public CSExecuteClientAction() {}
  
  public CSExecuteClientAction(String uuid, Object result) {
    this.uuid = uuid;
    this.result = result;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuid = PacketSerialUtils.readString(buf);
    if (!buf.isReadable())
      return; 
    if (PacketSerialUtils.readBoolean(buf)) {
      this.result = new RuntimeException(PacketSerialUtils.readString(buf));
      return;
    } 
    String objectClassName = PacketSerialUtils.readString(buf);
    try {
      Class<?> objectClass = Class.forName(objectClassName);
      this.result = PacketSerialUtils.read(buf, new Class[] { objectClass });
    } catch (Exception e) {
      System.err.println("[ClientAction] Unable to read object from packet (" + this.uuid + ")");
      e.printStackTrace();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.uuid);
    if (this.result == null)
      return; 
    if (this.result instanceof Throwable) {
      PacketSerialUtils.writeBoolean(buf, true);
      PacketSerialUtils.writeString(buf, ((Throwable)this.result).getMessage());
      return;
    } 
    PacketSerialUtils.writeString(buf, ClassHelper.of(this.result.getClass()).getName());
    PacketSerialUtils.write(buf, this.result);
  }
  
  public static class Handler implements IMessageHandler<CSExecuteClientAction, IMessage> {
    public IMessage onMessage(CSExecuteClientAction message, MessageContext ctx) {
      SidedActionExecution<Object> execution = ClientActionHook.getAndRemoveExecution(message.uuid);
      if (execution == null) {
        System.err.println("[ClientAction] Unable to find execution with UUID " + message.uuid);
        return null;
      } 
      SidedActionEntry entry = ClientActionHook.register(execution.getClassName(), execution.getMethodName(), execution.getArgs());
      if (entry == null) {
        execution.getFuture().completeExceptionally(new RuntimeException("[ClientAction] Unable to find method " + execution.getMethodName() + " in class " + execution.getClassName()));
        return null;
      } 
      if (entry.hasServerCache() && entry.getValidCacheResult(entry.getServerTimeout()) == null)
        entry.setCacheResult(new SidedActionCacheResult(message.result)); 
      if (message.result instanceof Throwable) {
        execution.getFuture().completeExceptionally((Throwable)message.result);
        return null;
      } 
      execution.getFuture().complete(message.result);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\client\CSExecuteClientAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */