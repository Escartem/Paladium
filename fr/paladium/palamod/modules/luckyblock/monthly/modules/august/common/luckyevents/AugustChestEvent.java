package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AugustChestEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Coffre d'août";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 450;
  
  private static final String TEXTURE_PATH = "august/august_chest";
  
  public static final String STRUCTURE_NAME = "august";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("august"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "august");
  }
  
  public String getName() {
    return "Coffre d'août";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 450;
  }
  
  public String getTexture() {
    return "august/august_chest";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\AugustChestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */