package fr.paladium.palaforgeutils.lib.sidedaction.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSExecuteServerAction implements IMessage {
  private String uuid;
  
  private String className;
  
  private String methodName;
  
  private Object[] args;
  
  public CSExecuteServerAction() {}
  
  public CSExecuteServerAction(String uuid, String className, String methodName, Object[] args) {
    this.uuid = uuid;
    this.className = className;
    this.methodName = methodName;
    this.args = args;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuid = PacketSerialUtils.readString(buf);
    this.className = PacketSerialUtils.readString(buf);
    this.methodName = PacketSerialUtils.readString(buf);
    int size = PacketSerialUtils.readInt(buf);
    this.args = new Object[size];
    for (int i = 0; i < size; i++) {
      String objectClassName = PacketSerialUtils.readString(buf);
      try {
        Class<?> objectClass = Class.forName(objectClassName);
        this.args[i] = PacketSerialUtils.read(buf, new Class[] { objectClass });
      } catch (Exception e) {
        System.err.println("[ServerAction] Unable to read argument " + i + " for packet (" + this.uuid + ", " + this.className + ":" + this.methodName + ")");
        e.printStackTrace();
      } 
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.uuid);
    PacketSerialUtils.writeString(buf, this.className);
    PacketSerialUtils.writeString(buf, this.methodName);
    PacketSerialUtils.writeInt(buf, this.args.length);
    for (Object arg : this.args) {
      PacketSerialUtils.writeString(buf, ClassHelper.of(arg.getClass()).getName());
      PacketSerialUtils.write(buf, arg);
    } 
  }
  
  public static class Handler implements IMessageHandler<CSExecuteServerAction, IMessage> {
    public IMessage onMessage(CSExecuteServerAction message, MessageContext ctx) {
      if (message.className == null || message.methodName == null || message.args == null)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      SidedActionEntry entry = ServerActionHook.register(message.className, message.methodName, message.args);
      if (entry == null)
        return null; 
      try {
        ServerActionHook.getContext().set(ctx.side, player, ctx.getServerHandler());
        Object result = entry.getMethod().invoke(null, message.args);
        ServerActionHook.getContext().clear();
        if (entry.isVoidType()) {
          PalaForgeUtilsMod.proxy.getNetwork().sendTo(new SCExecuteServerAction(UUIDUtils.toString((Entity)player), message.uuid, result), player);
        } else {
          CompletableFuture<Object> future = (CompletableFuture<Object>)result;
          future.whenComplete((object, throwable) -> FMLServerScheduler.getInstance().add(new Runnable[] { () }));
        } 
      } catch (Exception e) {
        String[] paramValues = new String[message.args.length];
        for (int i = 0; i < message.args.length; i++)
          paramValues[i] = message.args[i].getClass().getSimpleName(); 
        System.err.println("[ServerAction] Unable to execute requested server action by " + player.func_70005_c_() + " (" + message.className + ":" + message.methodName + ") with args " + String.join(", ", (CharSequence[])paramValues));
        e.printStackTrace();
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\server\CSExecuteServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */