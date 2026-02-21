package fr.paladium.palajobs.api;

import cpw.mods.fml.common.Loader;
import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.core.jobs.requirement.EntityKillRequirement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillObjective;
import fr.paladium.palajobs.core.quest.types.EntityKillQuest;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public final class PalaJobsAPI {
  public static final String JOBS_MOD_ID = "palajobs";
  
  private static PalaJobsAPI INSTANCE;
  
  @NonNull
  public static PalaJobsAPI inst() {
    if (INSTANCE == null)
      INSTANCE = new PalaJobsAPI(); 
    return INSTANCE;
  }
  
  public boolean isJobsLoaded() {
    return Loader.isModLoaded("palajobs");
  }
  
  @Nullable
  public Optional<IJobsPlayer> getJobsPlayer(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return (Optional)Optional.ofNullable(!isJobsLoaded() ? null : JobsPlayer.get((Entity)player));
  }
  
  public List<IBlackListedItem> getBlackListedItemUsages() {
    if (!isJobsLoaded())
      return new ArrayList<>(); 
    return JobsManager.getInstance().getBlackListedUsages();
  }
  
  public List<IBlackListedItem> getBlackListedCrafts() {
    if (!isJobsLoaded())
      return new ArrayList<>(); 
    return JobsManager.getInstance().getBlackListedCrafts();
  }
  
  public Optional<IBlackListedItem> isCraftBlackListed(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return getBlackListedCrafts().stream().filter(blackList -> blackList.is(itemStack)).findFirst();
  }
  
  public Optional<IBlackListedItem> isUsageBlackListed(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return getBlackListedItemUsages().stream().filter(blackList -> blackList.is(itemStack)).findFirst();
  }
  
  public void performKill(@NonNull EntityPlayer player, Class<? extends Entity> entityClazz) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (!isJobsLoaded())
      return; 
    EntityKillObjective.performCheck(player, entityClazz);
    EntityKillQuest.performCheck(player, entityClazz);
    JobsPlayer.get((Entity)player).getRequirements(EntityKillRequirement.class).forEach(optional -> optional.ifPresent(()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\PalaJobsAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */