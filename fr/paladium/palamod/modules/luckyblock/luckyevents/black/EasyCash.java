package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.boutique.BoutiqueManager;
import java.math.BigDecimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class EasyCash extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    BoutiqueManager.addmoneyToPlayer(player.func_110124_au(), new BigDecimal(25), "LUCKYBLOCK").thenAccept(result -> {
          if (result.booleanValue())
            player.func_146105_b((IChatComponent)new ChatComponentText("§cVous avez gagné 25 PBs !")); 
        });
  }
  
  public String getName() {
    return "Easy Cash";
  }
  
  public int getRarity() {
    return 2000;
  }
  
  public String getTexture() {
    return "easy_cash";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\EasyCash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */