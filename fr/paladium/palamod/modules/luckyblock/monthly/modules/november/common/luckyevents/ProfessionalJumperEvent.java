package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class ProfessionalJumperEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Sauteur professionnel";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 200;
  
  private static final String TEXTURE_PATH = "november/professional_jumper";
  
  private static final String PERFORM_MESSAGE = "§eAttention à ta tête quand tu sautes.";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eAttention à ta tête quand tu sautes." });
    player.func_70690_d(new PotionEffect(8, 1200, 40));
  }
  
  public String getName() {
    return "Sauteur professionnel";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "november/professional_jumper";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\ProfessionalJumperEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */