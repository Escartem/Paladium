package fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework;

import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AprilDigicodeStructure;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AprilChestEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Coffre d'avril";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 130;
  
  private static final String TEXTURE_PATH = "easter/chest";
  
  public static final String STRUCTURE_NAME = "april";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            AprilDigicodeStructure structure = new AprilDigicodeStructure((EntityPlayer)player);
            if (!structure.canSpawn()) {
              MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("april"));
              StructureCommand.addWaitingPlayer((EntityPlayer)player, "april");
              return;
            } 
            structure.spawn();
          } });
  }
  
  public String getName() {
    return "Coffre d'avril";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "easter/chest";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\rework\AprilChestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */