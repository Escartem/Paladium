package fr.paladium.palaforgeutils.lib.sidedaction.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sidedaction.ClientActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;

public class Handler implements IMessageHandler<SCExecuteClientAction, IMessage> {
  public IMessage onMessage(SCExecuteClientAction message, MessageContext ctx) {
    SidedActionEntry entry = ClientActionHook.register(SCExecuteClientAction.access$000(message), SCExecuteClientAction.access$100(message), SCExecuteClientAction.access$200(message));
    if (entry == null)
      return null; 
    try {
      Object result = entry.getMethod().invoke(null, SCExecuteClientAction.access$200(message));
      if (entry.isVoidType()) {
        PalaForgeUtilsMod.proxy.getNetwork().sendToServer(new CSExecuteClientAction(SCExecuteClientAction.access$300(message), result));
      } else {
        ClientActionResult<Object> future = (ClientActionResult<Object>)result;
        future.getFuture().whenComplete((object, throwable) -> FMLClientScheduler.getInstance().add(new Runnable[] { () }));
      } 
    } catch (Exception e) {
      String argsStr = "no args";
      if ((SCExecuteClientAction.access$200(message)).length > 0) {
        argsStr = "";
        for (Object arg : SCExecuteClientAction.access$200(message))
          argsStr = argsStr + arg.toString() + " (" + arg.getClass().getName() + "), "; 
        argsStr = argsStr.substring(0, argsStr.length() - 2);
      } 
      System.err.println("[ClientAction] Unable to execute requested client action (" + SCExecuteClientAction.access$300(message) + ", " + SCExecuteClientAction.access$000(message) + ":" + SCExecuteClientAction.access$100(message) + ", " + argsStr + ")");
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\network\client\SCExecuteClientAction$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */