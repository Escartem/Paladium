package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityJackLantern;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class JackLantern extends ALuckyEvent {
  public static final long DURATION = TimeUnit.MINUTES.toMillis(20L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityJackLantern king = new EntityJackLantern((EntityPlayer)player, x, y, z, DURATION);
    king.spawn(player.field_70170_p);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aOffre-moi une torche pour éclairer mon trajet s'il te plaît. Je t'offrirai quelque chose en échange." });
  }
  
  public String getName() {
    return "Jack-o-lantern";
  }
  
  public String getTexture() {
    return "small_candies";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 5000;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\JackLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */