package fr.paladium.palajobs.core.item;

public enum FishRarity {
  COMMON("§a"),
  RARE("§3"),
  LEGENDARY("§c"),
  MYTHIC("§d");
  
  private final String prefix;
  
  FishRarity(String prefix) {
    this.prefix = prefix;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\item\ItemFish$FishRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */