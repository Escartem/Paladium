package fr.paladium.palamod.modules.luckyblock.config;

import fr.paladium.palamod.modules.luckyblock.utils.HalloweenTradeConfig;

public class ClientHalloweenTradeConfig {
  private HalloweenTradeConfig trade;
  
  private int tradeAmount;
  
  private String cosmeticName;
  
  private String cosmeticImage;
  
  private int remainingCosmetics;
  
  private int cosmeticCost;
  
  public ClientHalloweenTradeConfig(HalloweenTradeConfig trade, int tradeAmount, String cosmeticName, String cosmeticImage, int remainingCosmetics, int cosmeticCost) {
    this.trade = trade;
    this.tradeAmount = tradeAmount;
    this.cosmeticName = cosmeticName;
    this.cosmeticImage = cosmeticImage;
    this.remainingCosmetics = remainingCosmetics;
    this.cosmeticCost = cosmeticCost;
  }
  
  public HalloweenTradeConfig getTrade() {
    return this.trade;
  }
  
  public int getTradeAmount() {
    return this.tradeAmount;
  }
  
  public String getCosmeticName() {
    return this.cosmeticName;
  }
  
  public String getCosmeticImage() {
    return this.cosmeticImage;
  }
  
  public int getRemainingCosmetics() {
    return this.remainingCosmetics;
  }
  
  public int getCosmeticCost() {
    return this.cosmeticCost;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\config\ClientHalloweenTradeConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */