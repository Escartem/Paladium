package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ExploreJobOpeningsEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Explorons les offres d'emploi";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "september/explore_job_openings";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.JOB_OFFER));
  }
  
  public String getName() {
    return "Explorons les offres d'emploi";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "september/explore_job_openings";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\ExploreJobOpeningsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */