package fr.paladium.palaclicker.server.manager;

public class ClickerManager {
  private static ClickerManager instance;
  
  public static ClickerManager getInstance() {
    if (instance == null)
      instance = new ClickerManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\manager\ClickerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */