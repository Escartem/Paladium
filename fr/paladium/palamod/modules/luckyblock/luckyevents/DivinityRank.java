package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DivinityRank extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (player instanceof EntityPlayer) {
      ConsoleUtils.executeCommand("lp user " + player.func_70005_c_() + " parent addtemp divinity 6mo accumulate");
      PlayerUtils.sendMessage((EntityPlayer)player, "§6§l[§e§lLucky§6§l] §aVous avez obtenu le grade §e§lDivinity §apour 6 mois !");
    } 
  }
  
  public String getName() {
    return "Divinity Grade";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DivinityRank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */