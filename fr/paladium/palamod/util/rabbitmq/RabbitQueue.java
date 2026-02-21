package fr.paladium.palamod.util.rabbitmq;

public enum RabbitQueue {
  MAILBOX_EVERYONE("mailbox.everyone"),
  MARKET_SELL("market.sell"),
  MAILBOX_PERSONNAL("mailbox.personnal"),
  LB_SPEAKER_MESSAGE("lb.speaker.message"),
  LB_FOGHORN_USE("lb.foghorn.use");
  
  private final String queueName;
  
  RabbitQueue(String queueName) {
    this.queueName = queueName;
  }
  
  public String getQueueName() {
    return this.queueName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\rabbitmq\RabbitQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */