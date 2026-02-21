package fr.paladium.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.ServerType;
import fr.paladium.common.utils.ServerStatus;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.resources.I18n;

public class CommonConfig {
  public void setServerType(ServerType serverType) {
    this.serverType = serverType;
  }
  
  public void setServerName(String serverName) {
    this.serverName = serverName;
  }
  
  public void setServers(Map<String, ServerStatus> servers) {
    this.servers = servers;
  }
  
  private ServerType serverType = ServerType.LOBBY;
  
  private String serverName = "N/A";
  
  private Map<String, ServerStatus> servers = new HashMap<>();
  
  public ServerType getServerType() {
    return (FMLCommonHandler.instance().getSide() == Side.CLIENT) ? this.serverType : ServerType.valueOf(CommonModule.getInstance().getConfigFile().getConfig().getString("server_type", "common", "LOBBY", "Current server type"));
  }
  
  public ServerType getServerType(Side side) {
    return (side == Side.CLIENT) ? this.serverType : ServerType.valueOf(CommonModule.getInstance().getConfigFile().getConfig().getString("server_type", "common", "LOBBY", "Current server type"));
  }
  
  public String getServerName() {
    return (FMLCommonHandler.instance().getSide() == Side.CLIENT) ? I18n.func_135052_a(this.serverName, new Object[0]) : CommonModule.getInstance().getConfigFile().getConfig().getString("server_name", "common", "LOBBY", "Current server name");
  }
  
  public String getServerName(Side side) {
    return (side == Side.CLIENT) ? I18n.func_135052_a(this.serverName, new Object[0]) : CommonModule.getInstance().getConfigFile().getConfig().getString("server_name", "common", "LOBBY", "Current server name");
  }
  
  public Map<String, ServerStatus> getServers() {
    return this.servers;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\CommonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */