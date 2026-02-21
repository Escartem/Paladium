package fr.paladium.palamod.metrics.bigbrother;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.bigbrother.lib.BigBrotherAPI;
import fr.paladium.bigbrother.lib.BigBrotherFactory;
import fr.paladium.bigbrother.lib.rabbitmq.RabbitCredentials;
import fr.paladium.bigbrother.lib.records.sessions.ISessionManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import java.io.File;
import java.net.InetAddress;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

public class BigBrotherImpl {
  public static BigBrotherImpl instance;
  
  private BigBrotherAPI api;
  
  private String initiator;
  
  private ISessionManager playerSession;
  
  private ISessionManager serverSession;
  
  private BigBrotherTask task;
  
  public BigBrotherAPI getApi() {
    return this.api;
  }
  
  public ISessionManager getPlayerSession() {
    return this.playerSession;
  }
  
  public ISessionManager getServerSession() {
    return this.serverSession;
  }
  
  public BigBrotherTask getTask() {
    return this.task;
  }
  
  public BigBrotherImpl(File location) {
    instance = this;
    load(location);
  }
  
  public void stop() {
    if (this.task != null)
      this.task.stop(); 
    if (this.serverSession != null && this.initiator != null)
      this.serverSession.stopSession(this.initiator); 
  }
  
  private void load(File location) {
    if (ForgeEnv.isDev()) {
      System.out.println("[BigBrotherImpl] BB is disabled in devmode.");
      return;
    } 
    System.out.println("[BigBrother] Starting BigBrother implementation...");
    try {
      setInitiator();
      RabbitCredentials rabbit = loadConfiguration(location);
      BigBrotherFactory factory = new BigBrotherFactory(this.initiator, rabbit);
      this.api = new BigBrotherAPI(factory);
      if (!this.api.connect()) {
        System.out.println("[BigBrother] Connection to BigBrother failed.");
        return;
      } 
      loadSessionManagers();
      this.task = new BigBrotherTask(this);
      FMLCommonHandler.instance().bus().register(new BigBrotherEventManager());
      MinecraftForge.EVENT_BUS.register(new BigBrotherEventManager());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private void loadSessionManagers() {
    try {
      this.playerSession = this.api.registerSessionManager("player");
      this.serverSession = this.api.registerSessionManager("mcserver");
      this.serverSession.startSession(this.initiator);
    } catch (Exception error) {
      System.out.println("[BigBrother] Cannot register session managers in BigBrother:");
      error.printStackTrace();
    } 
  }
  
  private void setInitiator() {
    try {
      this.initiator = InetAddress.getLocalHost().getHostName();
    } catch (Exception error) {
      System.out.println("[BigBrother] Cannot set initiator in BigBrother:");
      error.printStackTrace();
    } 
  }
  
  private RabbitCredentials loadConfiguration(File location) {
    File configFile = new File(location + "/bigbrother.cfg");
    boolean exists = configFile.exists();
    Configuration config = new Configuration(configFile);
    String[] hosts = config.getStringList("hosts", "rabbit", new String[] { "localhost:5672" }, "RabbitMQ hosts");
    String username = config.getString("username", "rabbit", "guest", "RabbitMQ username");
    String password = config.getString("password", "rabbit", "guest", "RabbitMQ password");
    String virtualHost = config.getString("virtualhost", "rabbit", "/", "RabbitMQ virtual host");
    if (!exists)
      config.save(); 
    System.out.println("[BigBrother] Using RabbitMQ server " + hosts[0] + " - " + username);
    return new RabbitCredentials(hosts, username, password, virtualHost);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\metrics\bigbrother\BigBrotherImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */