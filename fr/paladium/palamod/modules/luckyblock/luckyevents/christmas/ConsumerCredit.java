package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ConsumerCredit extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "De combien d'argent avez-vous besoin pour acheter les cadeaux:" });
    UsersManager.getConsumerCredit().add(player);
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(60000L);
              if (UsersManager.getConsumerCredit().contains(player))
                UsersManager.getConsumerCredit().remove(player); 
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        })).start();
  }
  
  public String getName() {
    return "Crédit à la consommation";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "consumer_credit";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ConsumerCredit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */