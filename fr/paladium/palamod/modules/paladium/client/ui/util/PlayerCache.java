package fr.paladium.palamod.modules.paladium.client.ui.util;

public class PlayerCache {
  private final String playerName;
  
  private final String skinUrl;
  
  public PlayerCache(String playerName, String skinUrl) {
    this.playerName = playerName;
    this.skinUrl = skinUrl;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PlayerCache))
      return false; 
    PlayerCache other = (PlayerCache)o;
    if (!other.canEqual(this))
      return false; 
    Object this$playerName = getPlayerName(), other$playerName = other.getPlayerName();
    if ((this$playerName == null) ? (other$playerName != null) : !this$playerName.equals(other$playerName))
      return false; 
    Object this$skinUrl = getSkinUrl(), other$skinUrl = other.getSkinUrl();
    return !((this$skinUrl == null) ? (other$skinUrl != null) : !this$skinUrl.equals(other$skinUrl));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PlayerCache;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $playerName = getPlayerName();
    result = result * 59 + (($playerName == null) ? 43 : $playerName.hashCode());
    Object $skinUrl = getSkinUrl();
    return result * 59 + (($skinUrl == null) ? 43 : $skinUrl.hashCode());
  }
  
  public String toString() {
    return "PlayerCache(playerName=" + getPlayerName() + ", skinUrl=" + getSkinUrl() + ")";
  }
  
  public String getPlayerName() {
    return this.playerName;
  }
  
  public String getSkinUrl() {
    return this.skinUrl;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\PlayerCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */