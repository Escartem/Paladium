package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class ClearXP extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_71106_cc = 0.0F;
    player.field_71068_ca = 0;
    player.field_71067_cb = 0;
    PlayerUtils.sendActionBar(player, "§cTon exp tu perdras dans l'instant qui suivra");
  }
  
  public String getName() {
    return "0 + 0 = La tête à Toto";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 40;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "clearxp";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ClearXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */