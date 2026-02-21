package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayerMP;

public class BodyGuard extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 2; i++) {
      EntityIronGolem golem = new EntityIronGolem(player.func_130014_f_());
      golem.func_70634_a(x, y, z);
      golem.func_94058_c(player.func_70005_c_() + "'s BodyGuard");
      player.func_130014_f_().func_72838_d((Entity)golem);
      UsersManager.addBodyGuard(player, golem);
      PlayerUtils.sendActionBar(player, "§bPour vous servir !");
    } 
  }
  
  public String getName() {
    return "Body Guard";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\BodyGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */