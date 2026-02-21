package fr.paladium.palawarzoneevent.module.warzone.server.phase;

import com.google.gson.annotations.SerializedName;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import java.util.List;

public class WarzonePhase {
  private String displayName;
  
  @SerializedName("start")
  private List<String> startCommands;
  
  @SerializedName("stop")
  private List<String> stopCommands;
  
  public String getDisplayName() {
    return this.displayName;
  }
  
  public List<String> getStartCommands() {
    return this.startCommands;
  }
  
  public List<String> getStopCommands() {
    return this.stopCommands;
  }
  
  public void executeStartCommands() {
    if (this.startCommands != null && !this.startCommands.isEmpty())
      this.startCommands.forEach(command -> ConsoleUtils.executeCommand(command)); 
  }
  
  public void executeStopCommands() {
    if (this.stopCommands != null && !this.stopCommands.isEmpty())
      this.stopCommands.forEach(command -> ConsoleUtils.executeCommand(command)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\phase\WarzonePhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */