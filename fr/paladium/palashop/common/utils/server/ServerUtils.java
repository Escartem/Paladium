package fr.paladium.palashop.common.utils.server;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.NonNull;

public class ServerUtils {
  @NonNull
  public static ServerType getServerType() {
    return CommonModule.getInstance().getConfig().getServerType();
  }
  
  @NonNull
  public static String getServerName() {
    return CommonModule.getInstance().getConfig().getServerName();
  }
  
  @NonNull
  public static String getServerFullName() {
    try {
      return getServerName() + " (" + InetAddress.getLocalHost().getHostName() + ")";
    } catch (UnknownHostException e) {
      return getServerName();
    } 
  }
  
  public static boolean isServer(@NonNull ServerType serverType) {
    if (serverType == null)
      throw new NullPointerException("serverType is marked non-null but is null"); 
    return (getServerType() == serverType);
  }
  
  public static boolean isServer(@NonNull String serverName) {
    if (serverName == null)
      throw new NullPointerException("serverName is marked non-null but is null"); 
    return getServerName().equalsIgnoreCase(serverName);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\commo\\utils\server\ServerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */