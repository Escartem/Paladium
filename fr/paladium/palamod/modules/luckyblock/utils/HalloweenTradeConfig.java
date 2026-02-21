package fr.paladium.palamod.modules.luckyblock.utils;

public class HalloweenTradeConfig {
  private String uuid;
  
  private long start;
  
  private long end;
  
  private int maxTrade;
  
  private HalloweenTradeItemConfig input;
  
  public HalloweenTradeConfig(String uuid, long start, long end, int maxTrade, HalloweenTradeItemConfig input) {
    this.uuid = uuid;
    this.start = start;
    this.end = end;
    this.maxTrade = maxTrade;
    this.input = input;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public long getStart() {
    return this.start;
  }
  
  public long getEnd() {
    return this.end;
  }
  
  public int getMaxTrade() {
    return this.maxTrade;
  }
  
  public HalloweenTradeItemConfig getInput() {
    return this.input;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\HalloweenTradeConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */