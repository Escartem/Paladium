package fr.paladium.palamod.modules.luckyblock.utils;

public class HalloweenTradeItemConfig {
  private int id;
  
  private byte meta;
  
  private int amount;
  
  public HalloweenTradeItemConfig(int id, byte meta, int amount) {
    this.id = id;
    this.meta = meta;
    this.amount = amount;
  }
  
  public HalloweenTradeItemConfig() {}
  
  public int getId() {
    return this.id;
  }
  
  public byte getMeta() {
    return this.meta;
  }
  
  public int getAmount() {
    return this.amount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\HalloweenTradeItemConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */