package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class NotTooFar extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.sendMessage((EntityPlayer)player, "§cPas si vite !!");
    (new Thread(() -> {
          try {
            Thread.sleep(1000L);
          } catch (Exception e) {
            e.printStackTrace();
          } 
          player.func_70606_j(1.0F);
          PlayerUtils.sendMessage((EntityPlayer)player, "§eC'est pas passé loin...");
        })).start();
  }
  
  public String getName() {
    return "C'est pas passé loin";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "c_est_pas_passe_loin";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NotTooFar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */