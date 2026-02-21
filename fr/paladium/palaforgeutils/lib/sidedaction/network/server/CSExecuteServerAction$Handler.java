package fr.paladium.palaforgeutils.lib.sidedaction.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<CSExecuteServerAction, IMessage> {
  public IMessage onMessage(CSExecuteServerAction message, MessageContext ctx) {
    if (CSExecuteServerAction.access$000(message) == null || CSExecuteServerAction.access$100(message) == null || CSExecuteServerAction.access$200(message) == null)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    SidedActionEntry entry = ServerActionHook.register(CSExecuteServerAction.access$000(message), CSExecuteServerAction.access$100(message), CSExecuteServerAction.access$200(message));
    if (entry == null)
      return null; 
    try {
      ServerActionHook.getContext().set(ctx.side, player, ctx.getServerHandler());
      Object result = entry.getMethod().invoke(null, CSExecuteServerAction.access$200(message));
      ServerActionHook.getContext().clear();
      if (entry.isVoidType()) {
        PalaForgeUtilsMod.proxy.getNetwork().sendTo(new SCExecuteServerAction(UUIDUtils.toString((Entity)player), CSExecuteServerAction.access$300(message), result), player);
      } else {
        CompletableFuture<Object> future = (CompletableFuture<Object>)result;
        future.whenComplete((object, throwable) -> FMLServerScheduler.getInstance().add(new Runnable[] { () }));
      } 
    } catch (Exception e) {
      String[] paramValues = new String[(CSExecuteServerAction.access$200(message)).length];
      for (int i = 0; i < (CSExecuteServerAction.access$200(message)).length; i++)
        paramValues[i] = CSExecuteServerAction.access$200(message)[i].getClass().getSimpleName(); 
      System.err.println("[ServerAction] Unable to execute requested server action by " + player.func_70005_c_() + " (" + CSExecuteServerAction.access$000(message) + ":" + CSExecuteServerAction.access$100(message) + ") with args " + String.join(", ", (CharSequence[])paramValues));
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\server\CSExecuteServerAction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */