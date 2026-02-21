package fr.paladium.palamod.metrics.prometheus;

import com.sun.net.httpserver.HttpServer;
import fr.paladium.palamod.Constants;
import java.io.IOException;
import java.net.InetSocketAddress;

public class PrometheusController {
  public static final PrometheusController INSTANCE = new PrometheusController();
  
  private HttpServer server;
  
  public void start() {
    try {
      this.server = HttpServer.create(new InetSocketAddress(9225), 0);
      this.server.createContext("/metrics", new MetricsHandler());
      this.server.setExecutor(null);
      this.server.start();
      Constants.logger.info("Started Prometheus metrics endpoint on port 9225");
    } catch (IOException e) {
      Constants.logger.error("Could not start HttpServer");
      e.printStackTrace();
    } 
  }
  
  public void stop() {
    if (this.server != null) {
      this.server.stop(0);
      this.server = null;
      Constants.logger.info("Stopped Prometheus metrics endpoint");
    } 
  }
  
  public void restart() {
    stop();
    start();
  }
  
  public boolean isStopped() {
    return (this.server == null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\metrics\prometheus\PrometheusController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */