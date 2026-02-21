package fr.paladium.palaforgeutils.lib.sidedaction.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<SCExecuteServerAction, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCExecuteServerAction message, MessageContext ctx) {
    if (!UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g).equals(SCExecuteServerAction.access$000(message)))
      return null; 
    SidedActionExecution<Object> execution = ServerActionHook.getAndRemoveExecution(SCExecuteServerAction.access$100(message));
    if (execution == null) {
      System.err.println("[ServerAction] Unable to find execution with UUID " + SCExecuteServerAction.access$100(message));
      return null;
    } 
    SidedActionEntry entry = ServerActionHook.register(execution.getClassName(), execution.getMethodName(), execution.getArgs());
    if (entry == null) {
      execution.getFuture().completeExceptionally(new RuntimeException("[ServerAction] Unable to find method " + execution.getMethodName() + " in class " + execution.getClassName()));
      return null;
    } 
    if (entry.hasClientCache() && entry.getValidCacheResult(entry.getClientTimeout()) == null)
      entry.setCacheResult(new SidedActionCacheResult(SCExecuteServerAction.access$200(message))); 
    if (SCExecuteServerAction.access$200(message) instanceof Throwable) {
      execution.getFuture().completeExceptionally((Throwable)SCExecuteServerAction.access$200(message));
      return null;
    } 
    execution.getFuture().complete(SCExecuteServerAction.access$200(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\server\SCExecuteServerAction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */