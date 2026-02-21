package fr.paladium.palaforgeutils.lib.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import lombok.NonNull;

public class Server {
  private static String hostName;
  
  private static String serverType;
  
  private static String serverName;
  
  public static String getHostName() {
    if (hostName == null && FMLCommonHandler.instance().getSide() == Side.SERVER)
      try {
        hostName = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
        System.err.println("Unable to get the server address");
        e.printStackTrace();
        hostName = "localhost";
      }  
    return hostName;
  }
  
  @NonNull
  public static ServerType getServerType() {
    return ServerType.get(getRawServerType());
  }
  
  @NonNull
  public static String getRawServerType() {
    if (serverType == null && PalaForgeConfigManager.getInstance() != null)
      serverType = PalaForgeConfigManager.getInstance().getServerType(); 
    return serverType;
  }
  
  public static String getServerName() {
    if (serverName == null && PalaForgeConfigManager.getInstance() != null)
      serverName = PalaForgeConfigManager.getInstance().getServerName(); 
    return serverName;
  }
  
  public static boolean is(@NonNull ServerType... types) {
    if (types == null)
      throw new NullPointerException("types is marked non-null but is null"); 
    return getServerType().is(types);
  }
  
  public static boolean is(@NonNull String... types) {
    if (types == null)
      throw new NullPointerException("types is marked non-null but is null"); 
    return getServerType().is(types);
  }
  
  public static boolean matches(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return getServerName().equalsIgnoreCase(name);
  }
  
  public static boolean matches(@NonNull Pattern pattern) {
    if (pattern == null)
      throw new NullPointerException("pattern is marked non-null but is null"); 
    return pattern.matcher(getServerName()).matches();
  }
  
  public static void set(@NonNull String hostName, @NonNull String serverType, @NonNull String serverName) {
    if (hostName == null)
      throw new NullPointerException("hostName is marked non-null but is null"); 
    if (serverType == null)
      throw new NullPointerException("serverType is marked non-null but is null"); 
    if (serverName == null)
      throw new NullPointerException("serverName is marked non-null but is null"); 
    Server.hostName = hostName;
    Server.serverType = serverType;
    Server.serverName = serverName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */