package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public class GrandmaMix extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Arrays.<PotionType>asList(PotionType.values()).stream().filter(potion -> (potion.getEffectType() != null))
      .forEach(potion -> player.func_70690_d(new PotionEffect(potion.getEffectType().getId(), 1200)));
  }
  
  public String getName() {
    return "Mélange de grand mère";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "grandmamix";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\GrandmaMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */