package fr.paladium.palamod.modules.enderchest.rabbitmq;

public enum RabbitQueue {
  SYNC_RAW_GET_REQUEST("sync.raw.get.request"),
  SYNC_RAW_GET_RESULT("sync.raw.get.result."),
  SYNC_RAW_SET("sync.raw.set");
  
  private final String queueName;
  
  RabbitQueue(String queueName) {
    this.queueName = queueName;
  }
  
  public String getQueueName() {
    return this.queueName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\RabbitQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */