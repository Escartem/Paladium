package fr.paladium.palawarzoneevent.module.capture.common.manager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.common.action.ClientCaptureEventManagerAction;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.util.AxisAlignedBB;

public class CaptureEventManager {
  private static CaptureEventManager INSTANCE;
  
  private final List<CapturePoint> points = new ArrayList<>();
  
  public List<CapturePoint> getPoints() {
    return this.points;
  }
  
  public static CaptureEventManager inst() {
    if (INSTANCE == null)
      INSTANCE = new CaptureEventManager(); 
    return INSTANCE;
  }
  
  @SideOnly(Side.SERVER)
  public void syncPlayerAround() {
    CaptureModule.getServer().getConfig().getCapturePoints().forEach(capturePoint -> {
          DoubleLocation cLoc = capturePoint.getLocation();
          double radius = capturePoint.getRadius();
          AxisAlignedBB captureZone = AxisAlignedBB.func_72330_a(cLoc.getX() - radius - 20.0D, cLoc.getY() - 100.0D, cLoc.getZ() - radius - 20.0D, cLoc.getX() + radius + 20.0D, cLoc.getY() + 100.0D, cLoc.getZ() + radius + 20.0D);
          ClientCaptureEventManagerAction.syncCapturePoints(CapturePointsData.of(CaptureModule.getServer().getConfig().getCapturePoints())).execute(PlayerSelector.AREA(captureZone));
        });
  }
  
  @SideOnly(Side.SERVER)
  public void updateCapture() {
    CaptureModule.getServer().getConfig().getCapturePoints().forEach(capturePoint -> {
          if (!capturePoint.isEnabled() || capturePoint.isInterrupted())
            return; 
          capturePoint.updateProgress();
        });
  }
  
  @SideOnly(Side.CLIENT)
  public Optional<CapturePoint> getCapturePointByName(String name) {
    return this.points.stream().filter(capPoint -> capPoint.getName().equals(name)).findFirst();
  }
  
  @SideOnly(Side.SERVER)
  public void sync() {
    List<CapturePoint> capPoints = CaptureModule.getServer().getConfig().getCapturePoints();
    ClientCaptureEventManagerAction.syncCapturePoints(CapturePointsData.of(capPoints)).execute(PlayerSelector.ALL());
  }
  
  @SideOnly(Side.CLIENT)
  public void sync(CapturePointsData cpData) {
    this.points.clear();
    this.points.addAll(cpData.getPoints());
  }
  
  public static class CapturePointsData {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\common\manager\CaptureEventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */