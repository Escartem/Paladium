package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.structure.MarchDigicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class MarchChestEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Coffre de Mars";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 140;
  
  private static final String TEXTURE_PATH = "march/chest";
  
  public static final String STRUCTURE_NAME = "march";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            MarchDigicodeStructure structure = new MarchDigicodeStructure((EntityPlayer)player);
            if (!structure.canSpawn()) {
              MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("march"));
              StructureCommand.addWaitingPlayer((EntityPlayer)player, "march");
              return;
            } 
            structure.spawn();
          } });
  }
  
  public String getName() {
    return "Coffre de Mars";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 140;
  }
  
  public String getTexture() {
    return "march/chest";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\MarchChestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */