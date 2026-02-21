package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class StarFish extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntitySilverfish starfish = new EntitySilverfish(player.field_70170_p);
    starfish.func_70634_a(x, y, z);
    starfish.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 50000, 9));
    starfish.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 50000, 4));
    starfish.func_94058_c("StarFish");
    player.field_70170_p.func_72838_d((Entity)starfish);
  }
  
  public String getName() {
    return "StarFish";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\StarFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */