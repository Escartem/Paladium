package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class QuicksandEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Sable mouvant";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 400;
  
  private static final String TEXTURE_PATH = "august/quicksand";
  
  public static final String STRUCTURE_NAME = "quicksand";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("quicksand"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "quicksand");
  }
  
  public String getName() {
    return "Sable mouvant";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "august/quicksand";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\QuicksandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */