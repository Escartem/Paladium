package fr.paladium.palamod.modules.luckyblock.network.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;

public class Handler implements IMessageHandler<PacketEasterTintamarre, IMessage> {
  public IMessage onMessage(PacketEasterTintamarre message, MessageContext ctx) {
    if (!ClientProxy.isBellRinging) {
      ClientProxy.isBellRinging = true;
      final LuckyTask task = new LuckyTask();
      task
        
        .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
            public void run() {
              try {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 120000L) {
                  ClientProxy.playSound("bell");
                  Thread.sleep(5000L);
                } 
              } catch (InterruptedException interruptedException) {}
              ClientProxy.isBellRinging = false;
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
            }
          },  0L).getTaskId();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\easter\PacketEasterTintamarre$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */