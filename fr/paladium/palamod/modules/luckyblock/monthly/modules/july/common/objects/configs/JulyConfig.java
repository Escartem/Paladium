package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.configs;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import java.util.Arrays;
import java.util.List;

public class JulyConfig {
  public List<ServerLocation> getLandInSightLocations() {
    return this.landInSightLocations;
  }
  
  public DoubleLocation getPirateIslandLocation() {
    return this.pirateIslandLocation;
  }
  
  public DoubleLocation getPirateChestLocation() {
    return this.pirateChestLocation;
  }
  
  public List<ServerLocation> getAquariumTeleportationLocation() {
    return this.aquariumTeleportationLocation;
  }
  
  public List<ServerLocation> getFlintLocations() {
    return this.flintLocations;
  }
  
  public List<ServerLocation> getFragmentLocations() {
    return this.fragmentLocations;
  }
  
  private final DoubleLocation pirateIslandLocation = new DoubleLocation(7540.0D, 54.0D, -553.0D);
  
  private final DoubleLocation pirateChestLocation = new DoubleLocation(7540.0D, 56.0D, -589.0D);
  
  private final List<ServerLocation> landInSightLocations = Arrays.asList(new ServerLocation[] { new ServerLocation("Egopolis", new DoubleLocation(-856.0D, 53.0D, -2954.0D)), new ServerLocation("Xanoth", new DoubleLocation(-1529.0D, 53.0D, 264.0D)), new ServerLocation("Kilmordra", new DoubleLocation(-1574.0D, 53.0D, 1904.0D)), new ServerLocation("Runegard", new DoubleLocation(-2448.0D, 53.0D, 4019.0D)), new ServerLocation("Aeloria", new DoubleLocation(-2892.0D, 53.0D, 3296.0D)), new ServerLocation("FactionSM", new DoubleLocation(0.0D, 53.0D, 0.0D)) });
  
  private final List<ServerLocation> aquariumTeleportationLocation = Arrays.asList(new ServerLocation[] { new ServerLocation("Egopolis", new DoubleLocation(-857.0D, 16.0D, -2952.0D)), new ServerLocation("Xanoth", new DoubleLocation(-1529.0D, 8.0D, 264.0D)), new ServerLocation("Kilmordra", new DoubleLocation(-1574.0D, 8.0D, 1904.0D)), new ServerLocation("Runegard", new DoubleLocation(-2448.0D, 16.0D, 4019.0D)), new ServerLocation("Aeloria", new DoubleLocation(-2892.0D, 10.0D, 3296.0D)), new ServerLocation("FactionSM", new DoubleLocation(0.0D, 100.0D, 0.0D)) });
  
  private final List<ServerLocation> flintLocations = Arrays.asList(new ServerLocation[] { new ServerLocation("Egopolis", new DoubleLocation(-825.0D, 55.0D, -2725.0D)), new ServerLocation("Xanoth", new DoubleLocation(-1945.0D, 55.0D, 767.0D)), new ServerLocation("Kilmordra", new DoubleLocation(-1836.0D, 53.0D, 2737.0D)), new ServerLocation("Runegard", new DoubleLocation(-2437.0D, 56.0D, 3845.0D)), new ServerLocation("Aeloria", new DoubleLocation(-2788.0D, 57.0D, 3334.0D)), new ServerLocation("FactionSM", new DoubleLocation(0.0D, 100.0D, 0.0D)) });
  
  private final List<ServerLocation> fragmentLocations = Arrays.asList(new ServerLocation[] { new ServerLocation("Egopolis", new DoubleLocation(-824.0D, 55.0D, -2718.0D)), new ServerLocation("Xanoth", new DoubleLocation(-1944.0D, 55.0D, 774.0D)), new ServerLocation("Kilmordra", new DoubleLocation(-1835.0D, 53.0D, 2744.0D)), new ServerLocation("Runegard", new DoubleLocation(-2436.0D, 56.0D, 3852.0D)), new ServerLocation("Aeloria", new DoubleLocation(-2787.0D, 57.0D, 3341.0D)), new ServerLocation("FactionSM", new DoubleLocation(0.0D, 100.0D, 0.0D)) });
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\objects\configs\JulyConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */