package fr.paladium.palashop.provider.cosmetic.client.ui.selector;

public class UICosmeticSelectorOverlayCache {
  private final int lastIndex;
  
  private final long cooldown;
  
  private final long lastUse;
  
  public int getLastIndex() {
    return this.lastIndex;
  }
  
  public long getCooldown() {
    return this.cooldown;
  }
  
  public long getLastUse() {
    return this.lastUse;
  }
  
  public UICosmeticSelectorOverlayCache(int lastIndex, long cooldown) {
    this.lastIndex = lastIndex;
    this.cooldown = cooldown;
    this.lastUse = System.currentTimeMillis();
  }
  
  public long getRemainingCooldown() {
    if (this.cooldown <= 0L)
      return 0L; 
    return Math.max(0L, this.lastUse + this.cooldown - System.currentTimeMillis());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\selector\UICosmeticSelectorOverlay$UICosmeticSelectorOverlayCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */