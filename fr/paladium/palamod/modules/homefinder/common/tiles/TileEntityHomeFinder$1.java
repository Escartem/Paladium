package fr.paladium.palamod.modules.homefinder.common.tiles;

import fr.paladium.homemod.common.dto.Home;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationSaver;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationType;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

class null implements IRetrofitCallback<List<Home>> {
  public void onSuccess(List<Home> homes) {
    List<LocationSaver> tmpLocations = new ArrayList<>();
    for (Home home : homes) {
      OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(home.getUserUniqueId());
      if (offlinePlayer == null || offlinePlayer.getName().equals(TileEntityHomeFinder.access$000(TileEntityHomeFinder.this)))
        continue; 
      LocationSaver locationSaver = new LocationSaver(offlinePlayer.getName(), offlinePlayer.getUniqueId(), TileEntityHomeFinder.this, LocationType.HOME, new Location(home.getX(), home.getY(), home.getZ()), TileEntityHomeFinder.access$100(TileEntityHomeFinder.this), home.getHomeName());
      tmpLocations.add(locationSaver);
    } 
    consumer.accept(tmpLocations);
  }
  
  public void onFail(List<Home> homes, Throwable throwable) {
    throwable.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\tiles\TileEntityHomeFinder$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */