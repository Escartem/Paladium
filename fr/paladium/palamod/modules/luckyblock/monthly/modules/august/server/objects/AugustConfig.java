package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.ServerLocation;
import java.util.Arrays;
import java.util.List;

public class AugustConfig {
  private static final DoubleLocation DEFAULT_LOCATION = new DoubleLocation(0.0D, 100.0D, 0.0D);
  
  public DoubleLocation getPetShopLocation() {
    return this.petShopLocation;
  }
  
  public List<ServerLocation> getSummerIslandLocations() {
    return this.summerIslandLocations;
  }
  
  private final DoubleLocation petShopLocation = new DoubleLocation(-10117.0D, 57.0D, -10035.0D);
  
  private final List<ServerLocation> summerIslandLocations = Arrays.asList(new ServerLocation[] { new ServerLocation("Event", new DoubleLocation(9009.0D, 37.0D, -1777.0D)) });
  
  private DoubleLocation augustChestLocation = new DoubleLocation(8928.0D, 57.0D, -1731.0D);
  
  public DoubleLocation getAugustChestLocation() {
    if (this.augustChestLocation == null)
      return DEFAULT_LOCATION; 
    return this.augustChestLocation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\objects\AugustConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */