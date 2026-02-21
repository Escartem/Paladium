package fr.paladium.palamod.modules.homefinder.common.data;

import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeFinderData {
  public static final String DATA_NAME_TAG = "HomeFinderData";
  
  private static final String DISCONNECTIONS_TAG = "disconnections";
  
  public static final String FINDERS_TAG = "finders";
  
  public static final String PLAYER_NAME_TAG = "playerName";
  
  public static final String UNIQUE_ID_TAG = "uniqueId";
  
  public static final String LOCATION_X_TAG = "locationX";
  
  public static final String LOCATION_Y_TAG = "locationY";
  
  public static final String LOCATION_Z_TAG = "locationZ";
  
  public static final String CANCELLED_TAG = "cancelled";
  
  @Deprecated
  public void setDisconnections(Map<UUID, DisconnectionData> disconnections) {
    this.disconnections = disconnections;
  }
  
  public void setFinders(ArrayList<Location> finders) {
    this.finders = finders;
  }
  
  @Deprecated
  public Map<UUID, DisconnectionData> getDisconnections() {
    return this.disconnections;
  }
  
  public ArrayList<Location> getFinders() {
    return this.finders;
  }
  
  @Deprecated
  private Map<UUID, DisconnectionData> disconnections = new HashMap<>();
  
  private ArrayList<Location> finders = new ArrayList<>();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\data\HomeFinderData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */