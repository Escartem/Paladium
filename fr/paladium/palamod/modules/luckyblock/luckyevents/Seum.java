package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Seum extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    JobType job = JobType.values()[player.field_70170_p.field_73012_v.nextInt((JobType.values()).length)];
    JobsPlayer.get((Entity)player).setExperience(job, JobExpUtils.getNeededXpForLvl(JobsPlayer.get((Entity)player).getLevel(job) - 1));
    PlayerUtils.sendActionBar(player, "§cHo non :(, vous avez perdu un niveau en §b" + job.getName());
  }
  
  public String getName() {
    return "Le seum...";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 10000;
  }
  
  public String getTexture() {
    return "seum";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Seum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */