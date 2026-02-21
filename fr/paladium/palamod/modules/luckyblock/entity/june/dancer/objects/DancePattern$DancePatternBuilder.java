package fr.paladium.palamod.modules.luckyblock.entity.june.dancer.objects;

import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import java.util.List;

public class DancePatternBuilder {
  private String username;
  
  private String skinPath;
  
  private List<DanceDirection> directions;
  
  public DancePatternBuilder username(String username) {
    this.username = username;
    return this;
  }
  
  public DancePatternBuilder skinPath(String skinPath) {
    this.skinPath = skinPath;
    return this;
  }
  
  public DancePatternBuilder directions(List<DanceDirection> directions) {
    this.directions = directions;
    return this;
  }
  
  public DancePattern build() {
    return new DancePattern(this.username, this.skinPath, this.directions);
  }
  
  public String toString() {
    return "DancePattern.DancePatternBuilder(username=" + this.username + ", skinPath=" + this.skinPath + ", directions=" + this.directions + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\dancer\objects\DancePattern$DancePatternBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */