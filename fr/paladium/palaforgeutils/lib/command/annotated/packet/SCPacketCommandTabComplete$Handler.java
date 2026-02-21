package fr.paladium.palaforgeutils.lib.command.annotated.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.TabCompleteEntry;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;

public class Handler implements IMessageHandler<SCPacketCommandTabComplete, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketCommandTabComplete message, MessageContext ctx) {
    try {
      Method method = TabCompleteEntry.getMethod(SCPacketCommandTabComplete.access$000(message), SCPacketCommandTabComplete.access$100(message), SCPacketCommandTabComplete.access$200(message));
      if (method != null) {
        Class<?> clazz = Class.forName(SCPacketCommandTabComplete.access$200(message));
        if (clazz != null) {
          Object instance = clazz.newInstance();
          Object result = method.invoke(instance, new Object[] { CommandContext.create((ICommandSender)(Minecraft.func_71410_x()).field_71439_g, SCPacketCommandTabComplete.access$300(message), SCPacketCommandTabComplete.access$400(message)) });
          if (result instanceof CompletableFuture) {
            ((CompletableFuture)result).thenAccept(options -> PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(SCPacketCommandTabComplete.access$500(message), (String[])options.toArray((Object[])new String[0]))));
          } else if (result instanceof String[]) {
            PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(SCPacketCommandTabComplete.access$500(message), (String[])result));
          } else if (result instanceof List) {
            PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSPacketCommandTabCompleteCallback(SCPacketCommandTabComplete.access$500(message), (String[])((List)result).toArray((Object[])new String[0])));
          } 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\packet\SCPacketCommandTabComplete$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */