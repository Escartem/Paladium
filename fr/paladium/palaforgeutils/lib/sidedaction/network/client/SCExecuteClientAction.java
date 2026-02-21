package fr.paladium.palaforgeutils.lib.sidedaction.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import io.netty.buffer.ByteBuf;

public class SCExecuteClientAction implements IMessage {
  private String uuid;
  
  private String className;
  
  private String methodName;
  
  private Object[] args;
  
  public SCExecuteClientAction() {}
  
  public SCExecuteClientAction(String uuid, String className, String methodName, Object[] args) {
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
        System.err.println("[ClientAction] Unable to read object from packet (" + this.uuid + ", " + this.className + ":" + this.methodName + ")");
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
  
  public static class Handler implements IMessageHandler<SCExecuteClientAction, IMessage> {
    public IMessage onMessage(SCExecuteClientAction message, MessageContext ctx) {
      SidedActionEntry entry = ClientActionHook.register(message.className, message.methodName, message.args);
      if (entry == null)
        return null; 
      try {
        Object result = entry.getMethod().invoke(null, message.args);
        if (entry.isVoidType()) {
          PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSExecuteClientAction(message.uuid, result));
        } else {
          ClientActionResult<Object> future = (ClientActionResult<Object>)result;
          future.getFuture().whenComplete((object, throwable) -> FMLClientScheduler.getInstance().add(new Runnable[] { () }));
        } 
      } catch (Exception e) {
        String argsStr = "no args";
        if (message.args.length > 0) {
          argsStr = "";
          for (Object arg : message.args)
            argsStr = argsStr + arg.toString() + " (" + arg.getClass().getName() + "), "; 
          argsStr = argsStr.substring(0, argsStr.length() - 2);
        } 
        System.err.println("[ClientAction] Unable to execute requested client action (" + message.uuid + ", " + message.className + ":" + message.methodName + ", " + argsStr + ")");
        e.printStackTrace();
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\client\SCExecuteClientAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */