package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class ObsiTrap extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnStructure(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 5, 5, 5, Blocks.field_150343_Z, player);
    PlayerUtils.sendActionBar(player, "§cPuisse le sort vous être favorable");
  }
  
  public String getName() {
    return "ObsiTrap";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ObsiTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */