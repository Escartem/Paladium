package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MoneyPoolEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Piscine de billets";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 400;
  
  private static final String TEXTURE_PATH = "august/money_pool";
  
  public static final String STRUCTURE_NAME = "pool";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("pool"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "pool");
  }
  
  public String getName() {
    return "Piscine de billets";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "august/money_pool";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\MoneyPoolEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */