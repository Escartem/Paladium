package fr.paladium.palaforgeutils.lib.sidedaction.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;

public class Handler implements IMessageHandler<CSExecuteClientAction, IMessage> {
  public IMessage onMessage(CSExecuteClientAction message, MessageContext ctx) {
    SidedActionExecution<Object> execution = ClientActionHook.getAndRemoveExecution(CSExecuteClientAction.access$000(message));
    if (execution == null) {
      System.err.println("[ClientAction] Unable to find execution with UUID " + CSExecuteClientAction.access$000(message));
      return null;
    } 
    SidedActionEntry entry = ClientActionHook.register(execution.getClassName(), execution.getMethodName(), execution.getArgs());
    if (entry == null) {
      execution.getFuture().completeExceptionally(new RuntimeException("[ClientAction] Unable to find method " + execution.getMethodName() + " in class " + execution.getClassName()));
      return null;
    } 
    if (entry.hasServerCache() && entry.getValidCacheResult(entry.getServerTimeout()) == null)
      entry.setCacheResult(new SidedActionCacheResult(CSExecuteClientAction.access$100(message))); 
    if (CSExecuteClientAction.access$100(message) instanceof Throwable) {
      execution.getFuture().completeExceptionally((Throwable)CSExecuteClientAction.access$100(message));
      return null;
    } 
    execution.getFuture().complete(CSExecuteClientAction.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\client\CSExecuteClientAction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */