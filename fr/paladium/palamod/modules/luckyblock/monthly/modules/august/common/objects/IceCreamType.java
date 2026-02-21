package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public enum IceCreamType {
  STRAWBERRY("Fraise", new PotionEffect(Potion.field_76428_l.field_76415_H, MonthlyUtils.translateSecondsToTicks(15), 2)),
  BANANA("Banane", new PotionEffect(Potion.field_76424_c.field_76415_H, MonthlyUtils.translateSecondsToTicks(15), 3)),
  CHOCOLATE("Chocolat", new PotionEffect(Potion.field_76420_g.field_76415_H, MonthlyUtils.translateSecondsToTicks(15), 1)),
  PISTACHIO("Pistache", new PotionEffect(Potion.field_76430_j.field_76415_H, MonthlyUtils.translateSecondsToTicks(15), 3)),
  COFFEE("Café", new PotionEffect(Potion.field_76426_n.field_76415_H, MonthlyUtils.translateSecondsToTicks(900), 0)),
  VANILLA("Vanille", new PotionEffect(Potion.field_76427_o.field_76415_H, MonthlyUtils.translateSecondsToTicks(900), 2)),
  COCONUT("Coco", new PotionEffect(Potion.field_76439_r.field_76415_H, MonthlyUtils.translateSecondsToTicks(900), 0));
  
  private String name;
  
  private PotionEffect effect;
  
  public String getName() {
    return this.name;
  }
  
  public PotionEffect getEffect() {
    return this.effect;
  }
  
  public static PotionEffect getPotionEffect(String name) {
    for (IceCreamType type : values()) {
      if (type.name.equalsIgnoreCase(name))
        return getPotionEffect(type); 
    } 
    return null;
  }
  
  public static PotionEffect getPotionEffect(IceCreamType type) {
    PotionEffect effect = new PotionEffect(type.effect.func_76456_a(), type.effect.func_76459_b(), type.effect.func_76458_c());
    return effect;
  }
  
  IceCreamType(String name, PotionEffect effect) {
    this.name = name;
    this.effect = effect;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\IceCreamType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */