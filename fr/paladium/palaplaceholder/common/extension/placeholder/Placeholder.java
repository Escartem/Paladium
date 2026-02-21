package fr.paladium.palaplaceholder.common.extension.placeholder;

import java.util.function.Function;
import net.minecraft.entity.player.EntityPlayer;

public class Placeholder {
  private String key;
  
  private Function<EntityPlayer, String> consumer;
  
  public String getKey() {
    return this.key;
  }
  
  public Function<EntityPlayer, String> getConsumer() {
    return this.consumer;
  }
  
  public static Placeholder create() {
    return new Placeholder();
  }
  
  public String apply(EntityPlayer player) {
    return this.consumer.apply(player);
  }
  
  public Placeholder key(String key) {
    this.key = key.trim().toLowerCase().replace(" ", "_");
    return this;
  }
  
  public Placeholder consumer(Function<EntityPlayer, String> consumer) {
    this.consumer = consumer;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\extension\placeholder\Placeholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */