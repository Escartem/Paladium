package fr.paladium.palawarzoneevent.module.warzone.server.phase;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned planned) {
    WarzonePhaseManager.access$002(WarzonePhaseManager.this, phaseName);
    WarzonePhaseManager.access$102(WarzonePhaseManager.this, phase);
    phase.executeStartCommands();
  }
  
  public void onFail(Planned planned, Throwable throwable) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\phase\WarzonePhaseManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */