package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DontTellAnyone extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    String prefix = "§e<§2Wilderness§e> §cLuckyMan §7: ";
    PlayerUtils.sendMessage((EntityPlayer)player, prefix + "Psss, j'ai un dossier qui a fuité, tien je te le partage gratuitement mais en parle a personne ;)");
    PlayerUtils.sendMessage((EntityPlayer)player, prefix);
  }
  
  public String getName() {
    return "N’en parle à personne";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "dontellanyone";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontTellAnyone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */