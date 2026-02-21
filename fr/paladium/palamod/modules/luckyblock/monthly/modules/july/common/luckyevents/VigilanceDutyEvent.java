package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.VigilanceStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.VigilanceRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class VigilanceDutyEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final String STRUCTURE_NAME = "vigie";
  
  private static final String EVENT_NAME = "Devoir de vigie";
  
  private static final String TEXTURE_PATH = "july/vigilance_duty";
  
  private static final int RARITY = 350;
  
  private static final boolean IS_BAD = false;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    VigilanceStructure structure = new VigilanceStructure((EntityPlayer)player);
    if (!structure.canSpawn()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("vigie"));
      StructureCommand.addWaitingPlayer((EntityPlayer)player, "vigie");
      return;
    } 
    structure.spawn();
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), VigilanceRunnable.DURATION_MILLIS, 
        System.currentTimeMillis());
  }
  
  public String getName() {
    return "Devoir de vigie";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "july/vigilance_duty";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Quelqu'un viendra bientôt vous relayer !" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\VigilanceDutyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */