package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class MegaFastLearner extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (JobType job : JobType.values())
      JobsPlayer.get((Entity)player).setExperience(job, JobExpUtils.getNeededXpForLvl(JobsPlayer.get((Entity)player).getLevel(job) + 1)); 
    PlayerUtils.sendActionBar(player, "§aWaw ! Tu as gagné 5 niveau dans tout les métiers");
  }
  
  public String getName() {
    return "Mega Fast Learner";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 2400;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MegaFastLearner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */