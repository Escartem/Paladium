package fr.paladium.palamod.modules.factions.dto.leveling;

public enum FactionXpChangeReason {
  QUEST_ACHIEVED(true, -1L, -1L, -1L),
  ADMIN(true, -1L, -1L, -1L),
  OTHER(true, -1L, -1L, -1L),
  BUY_FRONTCLAIM(true, 5L, -1L, -1L),
  RENT_FACTION_HOUSE(true, -1L, -1L, -1L),
  PLACED_BCC(true, -1L, 1L, 1209600000L),
  TERRITORY_CLAIM(true, -1L, 2000L, 2592000000L),
  PLACED_TOWN_HALL(true, 1L, -1L, -1L),
  PLACED_TOWN_MARKET(true, -1L, 5L, 2592000000L),
  PLACED_TOWN_TOURISM(true, -1L, 1L, 2592000000L),
  LOGTIME(false, -1L, 1L, 1209600000L),
  BANK_GAP(true, -1L, -1L, -1L),
  KILL_BOSS(true, -1L, -1L, -1L),
  KOTH(true, -1L, -1L, -1L),
  JOB_LEVELUP(true, -1L, -1L, -1L);
  
  private boolean notify;
  
  private long allLimit;
  
  private long timeLimitTimes;
  
  private long timeLimitMilliseconds;
  
  public boolean isNotify() {
    return this.notify;
  }
  
  public long getAllLimit() {
    return this.allLimit;
  }
  
  public long getTimeLimitTimes() {
    return this.timeLimitTimes;
  }
  
  public long getTimeLimitMilliseconds() {
    return this.timeLimitMilliseconds;
  }
  
  FactionXpChangeReason(boolean notify, long allLimit, long timeLimitTimes, long timeLimitMilliseconds) {
    this.notify = notify;
    this.allLimit = allLimit;
    this.timeLimitTimes = timeLimitTimes;
    this.timeLimitMilliseconds = timeLimitMilliseconds;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\dto\leveling\FactionXpChangeReason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */