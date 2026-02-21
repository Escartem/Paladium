package fr.paladium.palamod.metrics.bigbrother;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.bigbrother.lib.metrics.MetricCounter;
import fr.paladium.bigbrother.lib.records.IRecordCounter;
import fr.paladium.palamod.util.TPSTracker;

public class BigBrotherTask extends Thread {
  private final long refreshRate = 60000L;
  
  private BigBrotherImpl impl;
  
  private boolean running;
  
  private IRecordCounter counterTps;
  
  private IRecordCounter counterPlayers;
  
  public BigBrotherTask(BigBrotherImpl impl) {
    super("BigBrother/MCServer");
    this.impl = impl;
    try {
      registerMetrics();
      this.running = true;
      start();
    } catch (NullPointerException e) {
      e.printStackTrace();
      this.running = false;
    } 
  }
  
  private void registerMetrics() {
    MetricCounter metricTps = (new MetricCounter()).setName("tps").register(this.impl.getApi());
    MetricCounter metricPlayers = (new MetricCounter()).setName("playercount_mcserver").register(this.impl.getApi());
    this.counterTps = metricTps.newInstance(TPSTracker.INSTANCE.getTPS());
    this.counterPlayers = metricPlayers.newInstance(
        FMLCommonHandler.instance().getMinecraftServerInstance().func_71233_x());
  }
  
  public void runServerMetrics() {
    this.counterTps.set(TPSTracker.INSTANCE.getTPS());
    this.counterPlayers
      .set(FMLCommonHandler.instance().getMinecraftServerInstance().func_71233_x());
  }
  
  public void run() {
    while (this.running) {
      runServerMetrics();
      try {
        Thread.sleep(60000L);
      } catch (Exception error) {
        error.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\metrics\bigbrother\BigBrotherTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */