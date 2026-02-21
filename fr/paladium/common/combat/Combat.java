package fr.paladium.common.combat;

import java.util.List;

public class Combat {
  private long timeOffset;
  
  private long end;
  
  private List<String> opponents;
  
  public Combat(long timeOffset, long end, List<String> opponents) {
    this.timeOffset = timeOffset;
    this.end = end;
    this.opponents = opponents;
  }
  
  public void setTimeOffset(long timeOffset) {
    this.timeOffset = timeOffset;
  }
  
  public void setEnd(long end) {
    this.end = end;
  }
  
  public void setOpponents(List<String> opponents) {
    this.opponents = opponents;
  }
  
  public long getTimeOffset() {
    return this.timeOffset;
  }
  
  public long getEnd() {
    return this.end;
  }
  
  public List<String> getOpponents() {
    return this.opponents;
  }
  
  public boolean inFight() {
    return (this.opponents != null && !this.opponents.isEmpty() && this.end > System.currentTimeMillis());
  }
  
  public boolean inFightWith(String player) {
    if (!inFight())
      return false; 
    return this.opponents.contains(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\combat\Combat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */