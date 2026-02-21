package fr.paladium.palamod.modules.luckyblock.structures.sound.objects;

import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;

public class WaveStats {
  private LocatedBlock botLocation;
  
  private LocatedBlock playerLocation;
  
  public void setBotLocation(LocatedBlock botLocation) {
    this.botLocation = botLocation;
  }
  
  public void setPlayerLocation(LocatedBlock playerLocation) {
    this.playerLocation = playerLocation;
  }
  
  public WaveStats() {}
  
  public WaveStats(LocatedBlock botLocation, LocatedBlock playerLocation) {
    this.botLocation = botLocation;
    this.playerLocation = playerLocation;
  }
  
  public LocatedBlock getBotLocation() {
    return this.botLocation;
  }
  
  public LocatedBlock getPlayerLocation() {
    return this.playerLocation;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\objects\WaveStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */