package fr.paladium.palamod.modules.luckyblock.structures.sound.objects;

import fr.paladium.palamod.modules.luckyblock.structures.sound.enums.SoundTurn;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import java.util.ArrayList;
import java.util.List;

public class SoundWave {
  private int waveTimer;
  
  private int soundAmount;
  
  private int botIndex;
  
  private int playerIndex;
  
  private List<WaveStats> stats;
  
  public void setWaveTimer(int waveTimer) {
    this.waveTimer = waveTimer;
  }
  
  public void setSoundAmount(int soundAmount) {
    this.soundAmount = soundAmount;
  }
  
  public void setBotIndex(int botIndex) {
    this.botIndex = botIndex;
  }
  
  public void setPlayerIndex(int playerIndex) {
    this.playerIndex = playerIndex;
  }
  
  public void setStats(List<WaveStats> stats) {
    this.stats = stats;
  }
  
  public int getWaveTimer() {
    return this.waveTimer;
  }
  
  public int getSoundAmount() {
    return this.soundAmount;
  }
  
  public int getBotIndex() {
    return this.botIndex;
  }
  
  public int getPlayerIndex() {
    return this.playerIndex;
  }
  
  public List<WaveStats> getStats() {
    return this.stats;
  }
  
  public SoundWave(int soundAmount, int waveTimer) {
    this.soundAmount = soundAmount;
    this.botIndex = 0;
    this.playerIndex = 0;
    this.waveTimer = waveTimer;
    this.stats = new ArrayList<>();
    for (int i = 0; i < soundAmount; i++)
      this.stats.add(new WaveStats()); 
  }
  
  public boolean put(LocatedBlock locatedBlock, SoundTurn turn) {
    if (turn == SoundTurn.BOT) {
      ((WaveStats)this.stats.get(this.botIndex)).setBotLocation(locatedBlock);
      this.botIndex++;
    } else {
      ((WaveStats)this.stats.get(this.playerIndex)).setPlayerLocation(locatedBlock);
      if (!compare(this.playerIndex))
        return false; 
      this.playerIndex++;
    } 
    return true;
  }
  
  public boolean compare(int index) {
    WaveStats botStat = this.stats.get(index);
    WaveStats playerStat = this.stats.get(index);
    if (botStat == null || playerStat == null)
      return false; 
    return botStat.getBotLocation().equals(playerStat.getPlayerLocation());
  }
  
  public boolean isSoundFinished(int i, SoundTurn turn) {
    WaveStats stat = this.stats.get(i);
    if (stat == null)
      return false; 
    if (turn == SoundTurn.BOT)
      return (stat.getBotLocation() != null); 
    return (stat.getPlayerLocation() != null);
  }
  
  public void decrementTimer() {
    this.waveTimer--;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\objects\SoundWave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */