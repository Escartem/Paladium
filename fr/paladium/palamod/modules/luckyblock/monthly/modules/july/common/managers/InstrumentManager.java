package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundedLocation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstrumentManager {
  private static final int MAX_RADIUS_BETWEEN_LOCATIONS = 20;
  
  private static InstrumentManager instance;
  
  private final List<SoundedLocation> soundedLocations;
  
  public InstrumentManager() {
    instance = this;
    this.soundedLocations = new ArrayList<>();
  }
  
  public static InstrumentManager getInstance() {
    if (instance == null)
      instance = new InstrumentManager(); 
    return instance;
  }
  
  public List<SoundedLocation> getSoundedLocations() {
    this.soundedLocations.removeIf(sounded -> (sounded.isExpired() || sounded.isSpawned()));
    return this.soundedLocations;
  }
  
  public boolean canSpawnPirate(List<SoundedLocation> locations) {
    int drumCount = 0, accordionCount = 0, guitarCount = 0;
    List<SoundedLocation> updatedLocations = new ArrayList<>();
    for (SoundedLocation sounded : locations) {
      switch (sounded.getType()) {
        case DRUM:
          drumCount++;
          updatedLocations.add(sounded);
        case ACCORDION:
          accordionCount++;
          updatedLocations.add(sounded);
        case GUITAR:
          guitarCount++;
          updatedLocations.add(sounded);
      } 
    } 
    if (drumCount > 0 && accordionCount > 0 && guitarCount > 0) {
      updatedLocations.forEach(sounded -> sounded.setSpawned(true));
      return true;
    } 
    return false;
  }
  
  public List<SoundedLocation> getLocationInRadius(Location location) {
    return (List<SoundedLocation>)getSoundedLocations().stream()
      .filter(soundedLocationLocation -> (soundedLocationLocation.getLocation().getDistance(location) <= 20.0D))
      .collect(Collectors.toList());
  }
  
  public void addSoundedLocation(SoundedLocation sounded) {
    if (!this.soundedLocations.contains(sounded))
      this.soundedLocations.add(sounded); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\managers\InstrumentManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */