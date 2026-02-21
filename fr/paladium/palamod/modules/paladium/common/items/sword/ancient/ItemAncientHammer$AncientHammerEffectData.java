package fr.paladium.palamod.modules.paladium.common.items.sword.ancient;

public class AncientHammerEffectData {
  public final long cooldown;
  
  public final String description;
  
  private AncientHammerEffectData(long cooldown, String description) {
    this.cooldown = cooldown;
    this.description = description;
  }
  
  public static AncientHammerEffectData of(long cooldown, String description) {
    return new AncientHammerEffectData(cooldown, description);
  }
  
  public long getCooldown() {
    return this.cooldown;
  }
  
  public String getDescription() {
    return this.description;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\ItemAncientHammer$AncientHammerEffectData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */