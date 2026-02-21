package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SeptemberChestEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Coffre de septembre";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 450;
  
  private static final String TEXTURE_PATH = "september/september_chest";
  
  public static final String STRUCTURE_NAME = "september";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("september"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "september");
  }
  
  public String getName() {
    return "Coffre de septembre";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 450;
  }
  
  public String getTexture() {
    return "september/september_chest";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\SeptemberChestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */