package fr.paladium.palamod.util.rabbitmq;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class RabbitListener<T> {
  private final RabbitService service;
  
  private final RabbitPacketType type;
  
  private final String queue;
  
  private final Class<T> clazz;
  
  private String tag;
  
  protected RabbitListener(RabbitService service, RabbitPacketType type, String string, Class<T> clazz) {
    this.service = service;
    this.type = type;
    this.queue = string;
    this.clazz = clazz;
    try {
      startQueue();
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  private void startQueue() throws IOException {
    if (this.type == RabbitPacketType.PUBLISH) {
      this.service.getChannel().exchangeDeclare(this.queue, "fanout");
      String queueName = this.service.getChannel().queueDeclare().getQueue();
      this.service.getChannel().queueBind(queueName, this.queue, "");
      DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
          String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
          try {
            onReceive((T)RabbitService.getInstance().getGson().fromJson(message, this.clazz));
          } catch (Exception error) {
            error.printStackTrace();
          } 
        };
      this.tag = this.service.getChannel().basicConsume(queueName, true, deliverCallback1, consumerTag -> {
          
          });
      return;
    } 
    Map<String, Object> args = new HashMap<>();
    args.put("x-message-ttl", Integer.valueOf(60000));
    this.service.getChannel().queueDeclare(this.queue, false, false, false, args);
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        try {
          onReceive((T)RabbitService.getInstance().getGson().fromJson(message, this.clazz));
        } catch (Exception error) {
          error.printStackTrace();
        } 
      };
    this.tag = this.service.getChannel().basicConsume(this.queue, true, deliverCallback, consumerTag -> {
        
        });
  }
  
  public void stop() {
    try {
      this.service.getChannel().basicCancel(this.tag);
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  public abstract void onReceive(T paramT);
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\rabbitmq\RabbitListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */