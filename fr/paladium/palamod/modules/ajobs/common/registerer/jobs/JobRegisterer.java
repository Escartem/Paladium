package fr.paladium.palamod.modules.ajobs.common.registerer.jobs;

import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.IAbstractJob;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palamod.modules.alchimiste.common.enchant.EnchantmentBase;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class JobRegisterer implements IAbstractJob {
  protected AbstractJob job;
  
  public AbstractJob getJob() {
    return this.job;
  }
  
  public JobRegisterer(AbstractJob job) {
    this.job = job;
    job.getObjectives().clear();
    job.getRewards().clear();
  }
  
  public void register() {
    registerObjectives();
    registerRewards();
    registerBlacklistedCrafts();
    registerBlacklistedUsages();
    registerLvlTokensRewards();
    registerRequirements();
    for (Map.Entry<Enchantment, String> entry : (Iterable<Map.Entry<Enchantment, String>>)EnchantMod.enchants.entrySet()) {
      if (entry.getKey() instanceof EnchantmentBase) {
        EnchantmentBase eBase = (EnchantmentBase)entry.getKey();
        ItemStack enchantedBook = new ItemStack((Item)Items.field_151134_bR);
        Items.field_151134_bR.func_92115_a(enchantedBook, new EnchantmentData((Enchantment)eBase, eBase.func_77319_d()));
        if (!eBase.isEnabled())
          continue; 
        if (eBase.farmerLevel > 0 && this.job.getType() == JobType.FARMER) {
          this.job.addBlacklistedCraft(eBase.farmerLevel, enchantedBook);
          continue;
        } 
        if (eBase.hunterLevel > 0 && this.job.getType() == JobType.HUNTER) {
          this.job.addBlacklistedCraft(eBase.hunterLevel, enchantedBook);
          continue;
        } 
        if (eBase.mineurLevel > 0 && this.job.getType() == JobType.MINER) {
          this.job.addBlacklistedCraft(eBase.mineurLevel, enchantedBook);
          continue;
        } 
        if (eBase.alchemistLevel > 0 && this.job.getType() == JobType.ALCHEMIST)
          this.job.addBlacklistedCraft(eBase.alchemistLevel, enchantedBook); 
      } 
    } 
    JobsManager manager = JobsManager.getInstance();
    this.job.importRewardsFromBlackListedItems((List)manager.getBlackListedCrafts().stream().filter(j -> (j.getType() == this.job.getType())).collect(Collectors.toList()), RewardType.CRAFT);
    this.job.importRewardsFromBlackListedItems((List)manager.getBlackListedUsages().stream().filter(j -> (j.getType() == this.job.getType())).collect(Collectors.toList()), RewardType.USAGE);
    init();
  }
  
  public abstract void init();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\jobs\JobRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */