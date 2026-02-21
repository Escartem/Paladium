package fr.paladium.palamod.metrics.prometheus;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.TPSTracker;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.common.TextFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.Future;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.WorldServer;

public class MetricsHandler implements HttpHandler {
  public static MetricsHandler instance;
  
  private final Gauge players;
  
  private final Gauge loadedChunksPerWorld;
  
  private final Gauge playersPerWorld;
  
  private final Gauge entitiesPerWorld;
  
  private final Gauge livingEntitiesPerWorld;
  
  private final Gauge tileEntitiesPerWorld;
  
  private final Gauge memory;
  
  private final Gauge tps;
  
  public static MetricsHandler getInstance() {
    return instance;
  }
  
  public MetricsHandler() {
    instance = this;
    CollectorRegistry.defaultRegistry.clear();
    this
      .players = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_players_total")).help("Online and offline players")).labelNames(new String[] { "state" })).create().register();
    this
      .loadedChunksPerWorld = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_loaded_chunks_total")).help("Chunks loaded per world")).labelNames(new String[] { "world" })).create().register();
    this
      .playersPerWorld = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_players_online_total")).help("Players currently online per world")).labelNames(new String[] { "world" })).create().register();
    this
      .entitiesPerWorld = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_entities_total")).help("Entities loaded per world")).labelNames(new String[] { "world" })).create().register();
    this
      .livingEntitiesPerWorld = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_living_entities_total")).help("Living entities loaded per world")).labelNames(new String[] { "world" })).create().register();
    this
      .tileEntitiesPerWorld = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_tile_entities_total")).help("Living entities loaded per world")).labelNames(new String[] { "world" })).create().register();
    this
      .memory = (Gauge)((Gauge.Builder)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_jvm_memory")).help("JVM memory usage")).labelNames(new String[] { "type" })).create().register();
    this.tps = (Gauge)((Gauge.Builder)((Gauge.Builder)Gauge.build().name("mc_tps")).help("Server TPS (ticks per second)")).create().register();
  }
  
  public void handle(HttpExchange exchange) throws IOException {
    if (PrometheusController.INSTANCE.isStopped())
      return; 
    if (!exchange.getRequestURI().getPath().equals("/metrics")) {
      Constants.logger.debug("Invalid URL requested: " + exchange.getRequestURI().getPath());
      exchange.sendResponseHeaders(404, -1L);
      return;
    } 
    Constants.logger.debug("Metrics requested.");
    this.tps.set(TPSTracker.INSTANCE.getTPS());
    Future<Object> future = PaladiumScheduler.INSTANCE.callSyncMethod(() -> {
          ((Gauge.Child)this.players.labels(new String[] { "online" })).set(FMLCommonHandler.instance().getMinecraftServerInstance().func_71233_x());
          for (WorldServer world : (FMLCommonHandler.instance().getMinecraftServerInstance()).field_71305_c) {
            String worldName = getWorldName(world);
            ((Gauge.Child)this.loadedChunksPerWorld.labels(new String[] { worldName })).set(world.func_72863_F().func_73152_e());
            ((Gauge.Child)this.playersPerWorld.labels(new String[] { worldName })).set(world.field_73010_i.size());
            ((Gauge.Child)this.entitiesPerWorld.labels(new String[] { worldName })).set((world.field_72996_f.size() + world.field_147482_g.size()));
            ((Gauge.Child)this.livingEntitiesPerWorld.labels(new String[] { worldName })).set(world.field_72996_f.stream().filter(EntityLiving.class::isInstance).count());
            ((Gauge.Child)this.tileEntitiesPerWorld.labels(new String[] { worldName })).set(world.field_147482_g.size());
          } 
          long allocatedMemory = Runtime.getRuntime().totalMemory();
          long freeMemory = Runtime.getRuntime().freeMemory();
          ((Gauge.Child)this.memory.labels(new String[] { "max" })).set(Runtime.getRuntime().maxMemory());
          ((Gauge.Child)this.memory.labels(new String[] { "free" })).set(freeMemory);
          ((Gauge.Child)this.memory.labels(new String[] { "allocated" })).set(allocatedMemory);
          ((Gauge.Child)this.memory.labels(new String[] { "used" })).set((allocatedMemory - freeMemory));
          return null;
        });
    try {
      future.get();
      exchange.getResponseHeaders().set("Content-Type", "text/plain; version=0.0.4; charset=utf-8");
      exchange.sendResponseHeaders(200, 0L);
      try(OutputStream os = exchange.getResponseBody(); 
          OutputStreamWriter writer = new OutputStreamWriter(os)) {
        TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
      } 
    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
      Constants.logger.warn("Failed to read server statistics");
      e.printStackTrace();
      exchange.sendResponseHeaders(500, -1L);
    } 
  }
  
  private String getWorldName(WorldServer world) {
    switch (world.field_73011_w.func_80007_l()) {
      case "The End":
        return "The End";
      case "Nether":
        return "The Nether";
      case "Overworld":
        return "World";
    } 
    return world.func_72912_H().func_76065_j();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\metrics\prometheus\MetricsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */