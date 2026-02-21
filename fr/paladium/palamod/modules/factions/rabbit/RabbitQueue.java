package fr.paladium.palamod.modules.factions.rabbit;

public enum RabbitQueue {
  PLAYER_ADD_ELO("player.add.elo"),
  PLAYER_ADD_XP("player.add.xp");
  
  private final String queueName;
  
  RabbitQueue(String queueName) {
    this.queueName = queueName;
  }
  
  public String getQueueName() {
    return this.queueName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\rabbit\RabbitQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */