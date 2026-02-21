package fr.paladium.palawarzoneevent.module.warzone.server.phase;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawarzoneevent.module.warzone.WarzoneModule;
import java.util.List;
import java.util.UUID;

public class WarzonePhaseManager {
  private static WarzonePhaseManager INSTANCE;
  
  private String currentPhaseId;
  
  private WarzonePhase currentWZPhase;
  
  public void setCurrentPhaseId(String currentPhaseId) {
    this.currentPhaseId = currentPhaseId;
  }
  
  public void setCurrentWZPhase(WarzonePhase currentWZPhase) {
    this.currentWZPhase = currentWZPhase;
  }
  
  protected WarzonePhaseManager() {
    this.currentPhaseId = "";
    this.currentWZPhase = null;
  }
  
  public String getCurrentPhaseId() {
    return this.currentPhaseId;
  }
  
  public WarzonePhase getCurrentWZPhase() {
    return this.currentWZPhase;
  }
  
  public static WarzonePhaseManager inst() {
    if (INSTANCE == null)
      INSTANCE = new WarzonePhaseManager(); 
    return INSTANCE;
  }
  
  public void startPhase(final String phaseName, final WarzonePhase phase, UUID uuid) {
    if (phaseName == null || phaseName.isEmpty())
      throw new IllegalArgumentException("Phase name cannot be null or empty"); 
    if (phase == null)
      throw new IllegalArgumentException("Phase cannot be null"); 
    if (ForgeEnv.isIDE() || uuid == null) {
      this.currentPhaseId = phaseName;
      this.currentWZPhase = phase;
      phase.executeStartCommands();
      return;
    } 
    ChronosManager.getInstance().startEvent(UUIDUtils.toString(uuid), new ChronosCallback<Planned>() {
          public void onSuccess(Planned planned) {
            WarzonePhaseManager.this.currentPhaseId = phaseName;
            WarzonePhaseManager.this.currentWZPhase = phase;
            phase.executeStartCommands();
          }
          
          public void onFail(Planned planned, Throwable throwable) {}
        });
  }
  
  public void stopPhase(final WarzonePhase phase, UUID uuid) {
    if (phase == null)
      throw new IllegalArgumentException("Phase cannot be null"); 
    if (ForgeEnv.isIDE() || uuid == null) {
      this.currentPhaseId = "";
      this.currentWZPhase = null;
      executeDefaultCommands();
      phase.executeStopCommands();
      return;
    } 
    ChronosManager.getInstance().stopEventAsync(UUIDUtils.toString(uuid), new ChronosCallback<Planned>() {
          public void onSuccess(Planned planned) {
            WarzonePhaseManager.this.currentPhaseId = "";
            WarzonePhaseManager.this.currentWZPhase = null;
            WarzonePhaseManager.this.executeDefaultCommands();
            phase.executeStopCommands();
          }
          
          public void onFail(Planned planned, Throwable throwable) {}
        });
  }
  
  public void executeDefaultCommands() {
    List<String> defaults = WarzoneModule.getServer().getConfig().getDefaultCommands();
    if (defaults != null && !defaults.isEmpty())
      defaults.forEach(command -> ConsoleUtils.executeCommand(command)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\phase\WarzonePhaseManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */