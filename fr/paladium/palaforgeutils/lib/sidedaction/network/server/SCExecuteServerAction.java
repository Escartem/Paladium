package fr.paladium.palaforgeutils.lib.sidedaction.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SCExecuteServerAction implements IMessage {
  private String target;
  
  private String uuid;
  
  private Object result;
  
  public SCExecuteServerAction() {}
  
  public SCExecuteServerAction(String target, String uuid, Object result) {
    this.target = target;
    this.uuid = uuid;
    this.result = result;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.target = PacketSerialUtils.readString(buf);
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
      System.err.println("[ServerAction] Unable to read object from packet (" + this.uuid + ")");
      e.printStackTrace();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.target);
    PacketSerialUtils.writeString(buf, this.uuid);
    if (this.result == null)
      return; 
    if (this.result instanceof Throwable) {
      PacketSerialUtils.writeBoolean(buf, true);
      PacketSerialUtils.writeString(buf, ((Throwable)this.result).getMessage());
      return;
    } 
    PacketSerialUtils.writeBoolean(buf, false);
    PacketSerialUtils.writeString(buf, ClassHelper.of(this.result.getClass()).getName());
    PacketSerialUtils.write(buf, this.result);
  }
  
  public static class Handler implements IMessageHandler<SCExecuteServerAction, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCExecuteServerAction message, MessageContext ctx) {
      if (!UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g).equals(message.target))
        return null; 
      SidedActionExecution<Object> execution = ServerActionHook.getAndRemoveExecution(message.uuid);
      if (execution == null) {
        System.err.println("[ServerAction] Unable to find execution with UUID " + message.uuid);
        return null;
      } 
      SidedActionEntry entry = ServerActionHook.register(execution.getClassName(), execution.getMethodName(), execution.getArgs());
      if (entry == null) {
        execution.getFuture().completeExceptionally(new RuntimeException("[ServerAction] Unable to find method " + execution.getMethodName() + " in class " + execution.getClassName()));
        return null;
      } 
      if (entry.hasClientCache() && entry.getValidCacheResult(entry.getClientTimeout()) == null)
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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\server\SCExecuteServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */