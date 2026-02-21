package fr.paladium.palamod.modules.luckyblock.entity.june.dancer.objects;

import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import java.util.List;

public class DancePattern {
  private String username;
  
  private String skinPath;
  
  private List<DanceDirection> directions;
  
  DancePattern(String username, String skinPath, List<DanceDirection> directions) {
    this.username = username;
    this.skinPath = skinPath;
    this.directions = directions;
  }
  
  public static DancePatternBuilder builder() {
    return new DancePatternBuilder();
  }
  
  public static class DancePatternBuilder {
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
  
  public String getUsername() {
    return this.username;
  }
  
  public String getSkinPath() {
    return this.skinPath;
  }
  
  public List<DanceDirection> getDirections() {
    return this.directions;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\dancer\objects\DancePattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */