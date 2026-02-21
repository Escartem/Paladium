package fr.paladium.palaforgeutils.lib.command.annotated.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.TabCompleteEntry;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;

public class SCPacketCommandTabComplete implements IMessage {
  private String uuid;
  
  private String methodRaw;
  
  private String methodName;
  
  private String methodClass;
  
  private String command;
  
  private String[] args;
  
  public SCPacketCommandTabComplete() {}
  
  public SCPacketCommandTabComplete(String uuid, String methodRaw, String methodName, String methodClass, String command, String[] args) {
    this.uuid = uuid;
    this.methodRaw = methodRaw;
    this.methodName = methodName;
    this.methodClass = methodClass;
    this.command = command;
    this.args = args;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuid = PacketSerialUtils.readString(buf);
    this.methodRaw = PacketSerialUtils.readString(buf);
    this.methodName = PacketSerialUtils.readString(buf);
    this.methodClass = PacketSerialUtils.readString(buf);
    this.command = PacketSerialUtils.readString(buf);
    this.args = new String[PacketSerialUtils.readInt(buf)];
    for (int i = 0; i < this.args.length; i++)
      this.args[i] = PacketSerialUtils.readString(buf); 
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.uuid);
    PacketSerialUtils.writeString(buf, this.methodRaw);
    PacketSerialUtils.writeString(buf, this.methodName);
    PacketSerialUtils.writeString(buf, this.methodClass);
    PacketSerialUtils.writeString(buf, this.command);
    PacketSerialUtils.writeInt(buf, this.args.length);
    for (String arg : this.args)
      PacketSerialUtils.writeString(buf, arg); 
  }
  
  public static class Handler implements IMessageHandler<SCPacketCommandTabComplete, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketCommandTabComplete message, MessageContext ctx) {
      try {
        Method method = TabCompleteEntry.getMethod(message.methodRaw, message.methodName, message.methodClass);
        if (method != null) {
          Class<?> clazz = Class.forName(message.methodClass);
          if (clazz != null) {
            Object instance = clazz.newInstance();
            Object result = method.invoke(instance, new Object[] { CommandContext.create((ICommandSender)(Minecraft.func_71410_x()).field_71439_g, SCPacketCommandTabComplete.access$300(message), SCPacketCommandTabComplete.access$400(message)) });
            if (result instanceof CompletableFuture) {
              ((CompletableFuture)result).thenAccept(options -> PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(message.uuid, (String[])options.toArray((Object[])new String[0]))));
            } else if (result instanceof String[]) {
              PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(message.uuid, (String[])result));
            } else if (result instanceof List) {
              PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(message.uuid, (String[])((List)result).toArray((Object[])new String[0])));
            } 
          } 
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\packet\SCPacketCommandTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */