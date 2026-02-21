package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class Ban extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {}
  
  public String getName() {
    return "Good Bye have a great time!";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 2000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "bye_bye";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Ban.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */