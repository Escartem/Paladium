package fr.paladium.palawarzoneevent.module.warzone.common.data;

import java.util.List;

public class CaptureLeaderboardData {
  private final List<LeaderboardEntry> captureLeaderboardData;
  
  public CaptureLeaderboardData(List<LeaderboardEntry> captureLeaderboardData) {
    this.captureLeaderboardData = captureLeaderboardData;
  }
  
  public List<LeaderboardEntry> getCaptureLeaderboardData() {
    return this.captureLeaderboardData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\data\CaptureLeaderboardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */