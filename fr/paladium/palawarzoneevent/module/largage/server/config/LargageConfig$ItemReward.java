package fr.paladium.palawarzoneevent.module.largage.server.config;

public class ItemReward {
  private final String item;
  
  private final int weight;
  
  public String getItem() {
    return this.item;
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public ItemReward(String item, int amount) {
    this.item = item;
    this.weight = amount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\config\LargageConfig$ItemReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */