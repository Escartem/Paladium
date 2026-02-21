package fr.paladium.palawarzoneevent.module.capture.common.manager;

import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import java.util.List;

public class CapturePointsData {
  private final List<CapturePoint> points;
  
  public static CapturePointsData of(List<CapturePoint> points) {
    return new CapturePointsData(points);
  }
  
  private CapturePointsData(List<CapturePoint> points) {
    this.points = points;
  }
  
  public List<CapturePoint> getPoints() {
    return this.points;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\common\manager\CaptureEventManager$CapturePointsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */