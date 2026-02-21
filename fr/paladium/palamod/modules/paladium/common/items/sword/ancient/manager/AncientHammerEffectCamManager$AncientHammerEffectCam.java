package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;

public class AncientHammerEffectCam {
  private static final long DURATION = 2050L;
  
  private final DoubleLocation location;
  
  private long start;
  
  public void setStart(long start) {
    this.start = start;
  }
  
  private AncientHammerEffectCam(DoubleLocation location) {
    this.location = location;
  }
  
  public static AncientHammerEffectCam of(DoubleLocation location) {
    return new AncientHammerEffectCam(location);
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public long getStart() {
    return this.start;
  }
  
  public long getRemaining() {
    if (!isStarted())
      return 2050L; 
    return this.start + 2050L - UniversalTimeUtils.now();
  }
  
  public boolean isStarted() {
    return (this.start > 0L);
  }
  
  public boolean isFinished() {
    return (getRemaining() <= 0L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\manager\AncientHammerEffectCamManager$AncientHammerEffectCam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */