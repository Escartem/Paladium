package fr.paladium.palaclicker.server.config.shop.dto;

public enum ClickerShopType {
  WEEKLY(8, 168),
  DAILY(4, 24),
  JOBS(4, 168);
  
  private final int size;
  
  private final int hours;
  
  ClickerShopType(int size, int hours) {
    this.size = size;
    this.hours = hours;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public int getDuration() {
    return this.hours;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\config\shop\dto\ClickerShopType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */