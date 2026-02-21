package fr.paladium.palawarzoneevent.module.capture.server.config;

import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class CaptureConfig {
  public static final String PATH = "palawarzoneevent/capture.json";
  
  private final List<CapturePoint> capturePoints = new ArrayList<>();
  
  public List<CapturePoint> getCapturePoints() {
    return this.capturePoints;
  }
  
  public void save() {
    try {
      JsonConfigLoader.saveConfig(CaptureModule.getServer().getConfigFilePath(), this);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public CapturePoint getCapturePoint(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return this.capturePoints.stream().filter(capturePoint -> capturePoint.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }
  
  public boolean removeCapturePoint(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return this.capturePoints.removeIf(cp -> cp.getName().equalsIgnoreCase(name));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\server\config\CaptureConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */