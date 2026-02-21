package fr.paladium.palamod.modules.luckyblock.utils;

import java.util.List;

public class HalloweenConfig {
  private long start;
  
  private long end;
  
  private List<HalloweenTradeConfig> trades;
  
  private int cosmeticCost;
  
  private String cosmeticImage;
  
  private String cosmeticName;
  
  private String cosmeticCommand;
  
  private int cosmeticRemainings;
  
  public long getStart() {
    return this.start;
  }
  
  public long getEnd() {
    return this.end;
  }
  
  public List<HalloweenTradeConfig> getTrades() {
    return this.trades;
  }
  
  public int getCosmeticCost() {
    return this.cosmeticCost;
  }
  
  public String getCosmeticImage() {
    return this.cosmeticImage;
  }
  
  public String getCosmeticName() {
    return this.cosmeticName;
  }
  
  public String getCosmeticCommand() {
    return this.cosmeticCommand;
  }
  
  public void setCosmeticRemainings(int cosmeticRemainings) {
    this.cosmeticRemainings = cosmeticRemainings;
  }
  
  public int getCosmeticRemainings() {
    return this.cosmeticRemainings;
  }
  
  public HalloweenTradeConfig getCurrentTrade() {
    long now = System.currentTimeMillis();
    for (HalloweenTradeConfig trade : this.trades) {
      if (now < trade.getStart() || now >= trade.getEnd())
        continue; 
      return trade;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\HalloweenConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */