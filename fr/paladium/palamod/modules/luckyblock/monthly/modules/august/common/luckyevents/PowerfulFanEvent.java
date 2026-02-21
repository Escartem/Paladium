package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PowerfulFanEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Un peu puissant ce ventilateur";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 700;
  
  private static final String TEXTURE_PATH = "august/powerful_fan";
  
  public static final String STRUCTURE_NAME = "ventilator";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, AbstractStructure.getAlertMessage("ventilator"));
    StructureCommand.addWaitingPlayer((EntityPlayer)player, "ventilator");
  }
  
  public String getName() {
    return "Un peu puissant ce ventilateur";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "august/powerful_fan";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\PowerfulFanEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */