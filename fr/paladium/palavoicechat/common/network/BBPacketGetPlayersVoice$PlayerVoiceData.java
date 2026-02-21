package fr.paladium.palavoicechat.common.network;

import java.util.List;

public class PlayerVoiceData {
  private final List<String> playerNames;
  
  public PlayerVoiceData(List<String> playerNames) {
    this.playerNames = playerNames;
  }
  
  public List<String> getPlayerNames() {
    return this.playerNames;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\network\BBPacketGetPlayersVoice$PlayerVoiceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */